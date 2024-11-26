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
    int serversocket; // sockfd => socket()
    struct sockaddr_in serverAddr;
   
    int newSocket; // sockfd, accept()
    struct sockaddr_in newAddr;

    socklen_t addr_size; // accept()
    int checkbind, checklisten, checkRecv; // bind(), listen(), recv()

    char buffer[1024]; // recv(), send()
    pid_t childpid; // fork()

    // Create server socket
    serversocket = socket(AF_INET, SOCK_STREAM, 0);
    if (serversocket < 0) {
        perror("[-]Error in creating Server Socket.");
        exit(1);
    }
    printf("[+]Server Socket is created.\n");

    // Prepare the sockaddr_in structure
    memset(&serverAddr, '\0', sizeof(serverAddr));
    serverAddr.sin_family = AF_INET;
    serverAddr.sin_port = htons(PORT); // host to net short
    serverAddr.sin_addr.s_addr = inet_addr(IP_ADDRESS);

    // Bind the server socket
    checkbind = bind(serversocket, (struct sockaddr*)&serverAddr, sizeof(serverAddr));
    if (checkbind < 0) {
        perror("[-]Error in binding.");
        exit(1);
    }
    printf("[+]Bind to port %d\n", PORT);

    // Listen for incoming connections
    checklisten = listen(serversocket, 10); // backlog of 10 clients
    if (checklisten == 0) {
        printf("[+]Listening....\n");
    } else {
        perror("[-]Error in listen.");
        exit(1);
    }

    while (1)
    {
        addr_size = sizeof(newAddr);
        // Accept an incoming connection
        newSocket = accept(serversocket, (struct sockaddr*)&newAddr, &addr_size);
        if (newSocket < 0) {
            perror("[-]Error in accepting connection.");
            exit(1);
        }
        printf("Connection accepted from %s:%d\n", inet_ntoa(newAddr.sin_addr), ntohs(newAddr.sin_port));

        // Fork a child process to handle the client
        childpid = fork();
        if (childpid == 0) {
            // Close server socket in the child process
            close(serversocket);

            while (1)
            {
                // Receive message from client
                memset(buffer, 0, sizeof(buffer)); // Clear the buffer
                int checkRecv = recv(newSocket, buffer, sizeof(buffer), 0);
                if (checkRecv <= 0) {
                    break;
                }

                if (strcmp(buffer, ":exit") == 0) {
                    printf("Disconnected from %s:%d\n", inet_ntoa(newAddr.sin_addr), ntohs(newAddr.sin_port));
                    break;
                } else {
                    printf("Client : %s\n", buffer);
                    // Send message back to client
                    send(newSocket, buffer, strlen(buffer), 0);
                }
            }
            // Close the client socket
            close(newSocket);
            exit(0);
        }
    }

    // Close the server socket (this point is never reached unless the server is terminated)
    close(serversocket);

    return 0;
}
