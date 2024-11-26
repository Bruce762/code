#include <stdio.h> /* printf */
#include <sys/types.h> /* pid_t */
#include <unistd.h> /* fork(), getpid() */
#include <stdlib.h> /* exit() */
#include <sys/wait.h> /* wait() */

int main() {
    pid_t pid;

    for(int i = 0; i < 4; i++)  // 讓迴圈執行4次
    {
        pid = fork();  /* 創建子進程 */
        
        if(pid < 0)
        {
            perror("fork failed");
            exit(1);
        }
        else if(pid == 0)
        {
            /* 子進程執行 ProgramA */
            if(execlp("./ProgramA", "ProgramA", NULL) == -1) 
            //execlp(const char *file, const char *arg, ..., NULL);第一個arg建議是程式的名稱
            {
                perror("execlp failed");
                exit(1);
                /*
                exit(0) 正常返回
                exit(1) 一般錯誤
                exit(2) 命令行參數錯誤
                exit(3) 資料未找到或初始化失敗
                */
            }
            printf("execlp success\n");  // 子進程成功執行後輸出
            exit(0); // 確保子進程執行完後立即結束
        }
    }

    /* 父進程等待所有子進程結束 */
    while(wait(NULL) > 0); 
    /*
    wait() 的功能：
    讓父進程等待一個子進程結束。
    如果有子進程結束，wait() 會返回該子進程的進程 ID（PID）。
    如果沒有子進程，或者所有子進程都已結束，wait() 會返回 -1，並設置 errno 為 ECHILD。
    */

    /* 父進程一次性輸出 "I'm parent" */
    for(int i = 0; i < 4; i++) {
        printf("I'm parent\n");
    }
    
    return 0;
}
