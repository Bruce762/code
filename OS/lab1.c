#include <stdio.h>     /* printf */
#include <sys/types.h> /* pid_t */
#include <sys/wait.h>  /* wait() */
#include <unistd.h>    /* fork(), getpid() */

int main() {
    pid_t pid;
    int a = 1;
    /* Fork to create processes */
    for (int i = 0; i < 7; i++) {  // Fork three times to create 8 processes
        pid = fork();
        printf("[%d] [%d]\n", getpid(), i);
        printf("%d", a);
        if (pid < 0) {  // If fork fails
            perror("Fork failed");
            return 1;
        } else if (pid == 0) {  // Child process
            printf("i am child  [%d] [%d]\n", getpid(), i);
            continue;  // Go to next iteration of fork loop
        } else {       // Parent process
            printf("i am father [%d] [%d]\n", getpid(), i);
            break;  // Parent process does not continue forking
        }
    }

    /* Wait for a child process to stop or terminate */
    wait(NULL);
    printf("[%d] Hello world!\n", getpid());

    return 0;
}
