#include <ctype.h>
#include <limits.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct polyNode {
    int coef;
    int expon;
    struct polyNode* link;
} polyNode;

void printAllPolys();
void padd();
void psub();

void freeall(polyNode* now) {
    polyNode* tmp;
    while (now != NULL) {
        tmp = now;
        now = now->link;
        free(tmp);
    }
}

void pread(char c, polyNode** arr, int* cnt) {
    char str[10000];
    // int precoef = INT_MAX;
    polyNode* now = (polyNode*)malloc(sizeof(polyNode));
    polyNode* head = now;
    int num = 0;
    int coef;
    int expon = INT_MAX;
    int Neg = 1;
    int isfirst = 0;
    bool isX = false, isExpon = false;
    fgets(str, sizeof(str), stdin);
    // printf("%s\n",str);
    for (int i = 0; i < strlen(str); i++) {
        // printf("str[i]: %c num: %d coef: %d\n", str[i], num, coef);
        if (str[i] == ' ' || str[i] == '\t' || str[i] == '=') {
            continue;
        }
        isfirst++;
        if (str[i] == '-') {
            Neg = -1;
        }
        if (str[i] == '^') {
            isExpon = true;
        }
        if (isdigit(str[i])) {
            num *= 10;
            num += str[i] - '0';
        }
        if (str[i] == 'x' || str[i] == 'X') {
            if (num == 0) num = 1;
            num *= Neg;
            Neg = 1;
            now->coef = num;
            num = 0;
            isX = true;
        }
        if ((str[i] == '+' || str[i] == '-' || str[i] == '\n') && isfirst != 1) {
            if (isX && isExpon) {
                if (num > expon) {
                    freeall(head);
                    return;
                }
                expon = num;
                num = 0;
            } else if (isX && !isExpon) {
                if (1 > expon) {
                    freeall(head);
                    return;
                }
                expon = 1;
                num = 0;
            } else if (!isX && !isExpon) {
                if (0 > expon) {
                    freeall(head);
                    return;
                }
                now->coef = num;
                expon = 0;
                num = 0;
            }
            isX = false;
            isExpon = false;
            now->expon = expon;
            if (str[i] != '\n') {
                now->link = (polyNode*)malloc(sizeof(polyNode));
                now = now->link;
            } else {
                now->link = NULL;
            }
        }
    }
    if (arr[c] == NULL) {
        *cnt = *cnt + 1;
    }
    free(arr[c]);
    arr[c] = head;
}

void printPoly(char c, polyNode** arr) {
    polyNode* now = arr[c];

    if (now != NULL) {
        printf("%c\n", c);
        while (now != NULL) {
            printf("%d %d\n", now->coef, now->expon);
            now = now->link;
        }
    } else {
        printf("ERROR\n");
    }
}

int main() {
    // a.link=(polyNode *)malloc(sizeof(polyNode));
    polyNode* arr[1000] = {NULL};
    bool isSave[1000] = {0};
    char c, e;
    int cnt = 0;
    while (true) {
        scanf("%c", &c);
        if (c == ' ' || c == '\t') continue;
        /*
        if(arr[c]!=NULL){
            char str[1000];
            fgets(str,sizeof(str),stdin);
            printf("ERROR\n");
            continue;
        }*/
        // printf("%c%c",c,e);
        
        if (c == '0') {
            printf("quit");
            break;
        }
        // printf("%d", cnt);
        if (cnt < 10 || arr[c] != NULL) {
            // printf("c:%c\n",c);
            pread(c, arr, &cnt);
            printPoly(c, arr);
            // printf("is arr[c] null:%s\n",arr[c]==NULL?"yes":"no");
        } else {
            char str[1000];
            fgets(str, sizeof(str), stdin);
            printf("FULL\n");
        }
    }

    return 0;
}

/*
Sample 1:
A=x^2+2x+1
Output:
A
1 2
2 1
1 0
Input:
0
Output:
quit

A=x^2+2x+1
B=-3x^13-2x^5+3X^2+1
C=-3x^2-2x^5+3X^2+1
0

Sample 2:
Input:
B=-3x^13-2x^5+3X^2+1
Output:
B
-3 13
-2 5
3 2
1 0
Input:
0
Output:
quit


*/