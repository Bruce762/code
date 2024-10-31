#include <ctype.h>
#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAX_EXPRESSION_LENGTH 100

// Stack structure for operators
typedef struct {
    char stack[MAX_EXPRESSION_LENGTH];
    double DoubleStack[MAX_EXPRESSION_LENGTH];
    int top;
} OperatorStack;

// Function to check if a character is an operator
int isOperator(char c) {
    return (c == '+' || c == '-' || c == '*' || c == '/' || c == '^');
}

// Function to check if a character is an alphabet letter
int isVariable(char c) {
    return isalpha(c);
}

// Function to get the precedence of an operator
int getPrecedence(char c) {
    if (c == '^') return 3;
    if (c == '*' || c == '/') return 2;
    if (c == '+' || c == '-') return 1;
    return 0;
}

void getValue(char *infix, int values[]) {
    int variables[26] = {0};  // Assuming variables are lowercase letters a to z
    int variableIndex;

    for (int i = 0; infix[i] != '\0'; i++) {
        char token = infix[i];

        if (isVariable(token)) {
            variableIndex = token - 'a';  // Map 'a' to 0, 'b' to 1, and so on
            if (variables[variableIndex] == 0) {
                // printf("%c=", token);
                scanf("%d", &variables[variableIndex]);
            }
            values[variableIndex] = variables[variableIndex];
        }
    }
}
// Function to convert infix expression to postfix expression
void inToPostfix(char *infix, char *postfix) {
    OperatorStack opStack;
    opStack.top = -1;
    int postfixIndex = 0;
    int error = 0;

    for (int i = 0; infix[i] != '\0'; i++) {
        char token = infix[i];

        if (isalnum(token)) {
            while (isalnum(infix[i]) || infix[i] == '.') {
                postfix[postfixIndex++] = infix[i];
                // printf("----------infix = %c\n",infix[i]);

                i++;
            }
            i--;
        } else if (token == '(') {
            opStack.stack[++opStack.top] = token;
        } else if (token == ')') {
            while (opStack.top >= 0 && opStack.stack[opStack.top] != '(') {
                postfix[postfixIndex++] = opStack.stack[opStack.top];
                opStack.top--;
            }
            if (opStack.top >= 0 && opStack.stack[opStack.top] == '(') {
                opStack.top--;  // Pop the '('
            } else {
                error = 1;
                break;
            }
        } else if (isOperator(token)) {
            if (i == 0 || (i > 0 && isOperator(infix[i - 1]))) {
                error = 1;
                break;  // Error for consecutive operators
            }
            while (opStack.top >= 0 && getPrecedence(opStack.stack[opStack.top]) >= getPrecedence(token)) {
                postfix[postfixIndex++] = opStack.stack[opStack.top];
                opStack.top--;
            }
            opStack.stack[++opStack.top] = token;
        } else {
            error = 1;
            break;
        }
    }

    while (opStack.top >= 0) {
        if (opStack.stack[opStack.top] == '(') {
            error = 1;
            break;
        }
        postfix[postfixIndex++] = opStack.stack[opStack.top];
        opStack.top--;
    }

    postfix[postfixIndex] = '\0';

    if (error) {
        strcpy(postfix, "ERROR");
    }
}
double eval(char postfix[], int values[]) {
    OperatorStack Stack;
    Stack.top = -1;
    char str[10] = {};
    int variableIndex;
    for (int i = 0; postfix[i] != '\0'; i++) {
        // printf("YES\n");
        if (isalnum(postfix[i])) {
            if (isVariable(postfix[i])) {
                variableIndex = postfix[i] - 'a';  // Map 'a' to 0, 'b' to 1, and so on

                Stack.DoubleStack[++Stack.top] = values[variableIndex];

            } else {
                if (isalnum(postfix[i + 1]) != 1) {
                    str[0] = postfix[i];
                    Stack.DoubleStack[++Stack.top] = atof(str);
                    // printf("__%.1f\n",Stack.DoubleStack[Stack.top-1]);
                }
            }
        } else {
            double operand2 = Stack.DoubleStack[Stack.top];
            // printf("operand2 = %.1f\n",Stack.DoubleStack[Stack.top]);
            Stack.top--;

            double operand1 = Stack.DoubleStack[Stack.top];
            // printf("operand1 = %.1f\n",Stack.DoubleStack[Stack.top]);
            Stack.top--;

            switch (postfix[i]) {
                case '+':
                    Stack.DoubleStack[++Stack.top] = operand1 + operand2;
                    // printf("operand1 + operand2 = %.1f\n",Stack.DoubleStack[Stack.top]);
                    // printf("top = %d\n",Stack.top);
                    break;
                case '-':
                    Stack.DoubleStack[++Stack.top] = operand1 - operand2;
                    // printf("operand1 - operand2 = %.1f\n",Stack.DoubleStack[Stack.top]);
                    // printf("top = %d\n",Stack.top);
                    break;
                case '*':
                    Stack.DoubleStack[++Stack.top] = operand1 * operand2;
                    // printf("operand1 * operand2 = %.1f\n",Stack.DoubleStack[Stack.top]);
                    // printf("top = %d\n",Stack.top);

                    break;
                case '/':
                    if (operand2 == 0) {
                        // Handle division by zero here
                        return 0.0;
                    }
                    Stack.DoubleStack[++Stack.top] = operand1 / operand2;
                    // printf("operand1 / operand2 = %.1f\n",Stack.DoubleStack[Stack.top]);
                    // printf("top = %d\n",Stack.top);
                    break;
                case '^':
                    Stack.DoubleStack[++Stack.top] = pow(operand1, operand2);
                    // printf("top = %d\n",Stack.top);
                    break;
            }
        }
    }
    // if (Stack.top != 0) {
    // // Invalid expression
    // return 0.0;
    // }
    if (Stack.top == 0) {
        return Stack.DoubleStack[0];
    } else {
        return Stack.DoubleStack[Stack.top];
    }
}

int main() {
    char infix[MAX_EXPRESSION_LENGTH];
    char postfix[MAX_EXPRESSION_LENGTH];
    // vnums[0] is the number of variables
    // printf("Enter the infix expression: ");
    while (fgets(infix, MAX_EXPRESSION_LENGTH, stdin)) {
        int values[26] = {0};                // Assuming variables are lowercase letters a to z
        infix[strcspn(infix, "\n")] = '\0';  // Remove newline character if present
        if (infix[0] == '\0') {
            continue;
        }
        inToPostfix(infix, postfix);
        if (strcmp(postfix, "ERROR") != 0) {
            getValue(infix, values);
        }

        if (strcmp(postfix, "ERROR") != 0) {
            double result = eval(postfix, values);
            printf("Postfix expression:%s\n", postfix);
            printf("eval:%.1f\n", result);

        } else {
            // printf("Infix expression:%s is ERROR\n",infix);
            printf("ERROR\n");
        }
    }
    return 0;
}