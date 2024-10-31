#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
int m, n, o;
void topological(int **dpdgraph, int *isCyclic, int jobCount) {
    int bjcnt[jobCount];
    int totalJob = 0;

    for (m = 0; m < jobCount; ++m) {
        bjcnt[m] = 0;
    }
    for (m = 0; m < jobCount; ++m) {
        for (n = 0; n < jobCount; ++n) {
            if (dpdgraph[m][n] == 1) {
                ++bjcnt[n];
            }
        }
    }
    for (m = 0; m < jobCount; ++m) {
        *isCyclic = 0;

        for (n = 0; n < jobCount; ++n) {
            if (bjcnt[n] == 0) {
                *isCyclic = 1;

                bjcnt[n] = -1;
                ++totalJob;
                for (o = 0; o < jobCount; ++o) {
                    // 刪除與節點相連的線
                    if (dpdgraph[n][o] == 1) {
                        --bjcnt[o];
                    }
                }
            }
        }
        if (totalJob == jobCount) {
            return;
        }
        // isCyclic = 0代表有環
        if (*isCyclic == 0) {
            return;
        }
    }
}

// row代表要先做的工作，col代表先做的工作做完才能做的工作
void inputData(int **dpdgraph) {
    int job, beforeJob;
    char input[2000];
    char *p = input;
    fgets(input, sizeof(input), stdin);
    while (*p != '\0') {
        if (isalpha(*p)) {
            job = *p - 'a';
            p += 2;
        } else if (isdigit(*p)) {
            job = strtod(p, &p);
            ++p;
        }
        if (isalpha(*p)) {
            beforeJob = *p - 'a';
            p += 2;
        } else if (isdigit(*p)) {
            beforeJob = strtod(p, &p);
            ++p;
        }
        dpdgraph[beforeJob][job] = 1;
    }
}
int main() {
    int jobCount;
    int isCyclic = 0;
    while (scanf("%d", &jobCount) != EOF) {
        getchar();
        if (jobCount == 0) {
            printf("quit\n");
            return 0;
        }
        int **dpdgraph = (int **)calloc(jobCount, sizeof(int *));
        for (m = 0; m < jobCount; ++m) {
            dpdgraph[m] = (int *)calloc(jobCount, sizeof(int));
        }
        inputData(dpdgraph);
        topological(dpdgraph, &isCyclic, jobCount);
        if (isCyclic == 0) {
            printf("false\n");
        } else {
            printf("true\n");
        }
    }
}
