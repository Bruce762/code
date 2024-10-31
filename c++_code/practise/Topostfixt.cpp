#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <ctype.h>

void setWeight(int* weight) {
    weight['+'] = 2;
    weight['-'] = 2;
    weight['/'] = 3;
    weight['*'] = 3;
    weight['^'] = 4;
}

int isAlphaOrDigit(char c) {
    return isalpha(c) || isdigit(c);
}

void intoPostFix(char* input, char* output) {
    char stack[100000];
    int top = 0, leftParent = 0, rightParant = 0, n = 0, lastalpha = '\0';
    int weight[1000] = {0};
    setWeight(weight);
    for (int i = 0; i < input[i]; i++) {
        if (input[i] == ' ' || input[i] == '\t' || input[i] == '\n') continue;
        if (isAlphaOrDigit(input[i])) {
            output[n++] = input[i];
            lastalpha = '\0';
        } else if (input[i] == '(') {
            stack[top++] = '(';
            leftParent++;
            lastalpha = '(';
        } else if (input[i] == ')') {
            rightParant++;
            if (lastalpha == '+' || lastalpha == '-' || lastalpha == '*' || lastalpha == '/' || lastalpha == '^') {
                strcpy(output, "ERROR");
                return;
            }
            while (top!=0) {
                if (top == 1 && stack[top - 1] != '(') {
                    strcpy(output, "ERROR");
                    return;
                } else if (stack[top - 1] == '(') {
                    top--;
                    break;
                }
                output[n++] = stack[--top];
            }
            lastalpha = ')';
        } else if (input[i] == '+' || input[i] == '-' || input[i] == '*' || input[i] == '/' || input[i] == '^') {
            if (input[i] == lastalpha || ((lastalpha != '\0') && lastalpha != ')' && !isAlphaOrDigit(lastalpha) && !(lastalpha == '(' && (input[i] == '+' || input[i] == '-')))) {
                strcpy(output, "ERROR");
                return;
            }
            while (top!=0 && weight[input[i]] <= weight[stack[top - 1]]) {
                output[n++] = stack[--top];
            }
            stack[top++] = input[i];
            lastalpha = input[i];
        } else {
            strcpy(output, "ERROR");
            return;
        }
    }
    if (leftParent != rightParant) {
        strcpy(output, "ERROR");
        return;
    }
    while (top != 0) {
        output[n++] = stack[--top];
    }
}

int main() {
    char str[100] = {0};  // 增加一個固定大小的字符數組以存儲輸入
    char trans[100] = {0};
    int i = 0;
    while(fgets(str, sizeof(str), stdin) != NULL) {
        str[strcspn(str, "\n")] = 0;
        intoPostFix(str, trans);
        if (strcmp(trans ,"ERROR") != 0) {
            printf("Postfix expression:");
        }
        printf("%s\n\n", trans);
        memset(str, 0, sizeof(str));
        memset(trans, 0, sizeof(trans));
    }
    return 0;
}


/*
input
(-a+b)^2+(b+3)^c-3
3
5
4
(f+(t*d))+(g/d)
1
2
2
4

output
eval:4097.0
eval:7.0


input
(-q+a)^2+(a+3)^z-3
3
5
4
(s+r)**r-k)
(-a+b)^2+(b+3)^c-3
1
5
2

output
Postfix expression:q-a+2^a3+z^+3-
eval:4097.0
ERROR
Postfix expression:a-b+2^b3+c^+3-
eval:77.0
*/