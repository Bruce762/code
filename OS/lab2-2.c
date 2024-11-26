#include <stdio.h> /* printf */
#include <sys/types.h> /* pid_t */
#include <unistd.h> /* fork(), getpid() */
#include <stdlib.h> /* exit() */
#include <sys/wait.h> /* wait() */
int main() {
    pid_t pid;
    
    for(int i = 0; i < 3; i++)
    {
        pid = fork(); 
        wiat(NULL);
        printf("i:%d pid:%d\n",i,getpid());
    }
    while(wait(NULL) > 0); 
    if(pid > 0)
    {
        printf("[%d]I'm parent\n",getpid());
    }
    else if(pid == 0)
    {
        if(execlp("./b_A" , "b_A", NULL) == -1)  
        {
            printf("[%d]execlp failed\n",getpid()); 
            exit(1);   
        }
    }
    else
    {
        printf("fork failed\n");
        exit(1);
    }
    return 0;
}
