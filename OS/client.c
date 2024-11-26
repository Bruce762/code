#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <arpa/inet.h>

#define PORT 4444
#define IP_ADDRESS "127.0.0.1"

int main()
{
    int clientSocket;
    struct sockaddr_in serverAddr;
    int checkConnect, checkRecv;
    char buffer[1024];

    // Create socket
    clientSocket = socket(AF_INET, SOCK_STREAM, 0);
    if (clientSocket < 0)
    {
        perror("[-]Error in creating Client Socket.");
        exit(1);
    }
    printf("[+]Client Socket is created.\n");

    memset(&serverAddr, '\0', sizeof(serverAddr));
    serverAddr.sin_family = AF_INET;
    serverAddr.sin_port = htons(PORT); // Server port
    serverAddr.sin_addr.s_addr = inet_addr(IP_ADDRESS); // Server IP address

    // Connect to server
    checkConnect = connect(clientSocket, (struct sockaddr*)&serverAddr, sizeof(serverAddr));
    if (checkConnect < 0)
    {
        perror("[-]Error in connection.");
        exit(1);
    }
    printf("[+]Connected to Server.\n");

    while (1)
    {
        // Get message from user
        printf("Client : ");
        fgets(buffer, sizeof(buffer), stdin); // Use fgets to handle spaces
        buffer[strcspn(buffer, "\n")] = 0; // Remove trailing newline character

        // Send message to server
        int checkSend = send(clientSocket, buffer, strlen(buffer), 0);
        if (checkSend < 0)
        {
            perror("[-]Error in sending data.");
            break;
        }

        // Check for exit condition
        if (strcmp(buffer, ":exit") == 0)
        {
            close(clientSocket);
            printf("[-]Disconnected from server.\n");
            exit(1);
        }

        // Receive message from server
        memset(buffer, 0, sizeof(buffer)); // Clear buffer before receiving
        checkRecv = recv(clientSocket, buffer, sizeof(buffer), 0);
        if (checkRecv < 0)
        {
            printf("[-]Error in receiving data.\n");
        }
        else
        {
            printf("Server : %s\n", buffer); // Display received message from server
        }
    }

    return 0;
}