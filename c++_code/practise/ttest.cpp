#include <stdio.h>
#include <stdbool.h>
#include <ctype.h>

// 函數原型
bool isValidPolynomial(char* polynomial);

int main() {
    char polynomial[100];

    // 提示用戶輸入多項式
    printf("請輸入多項式（例如 x^3+4x+5）: ");
    scanf("%[^\n]", polynomial);

    // 檢測多項式格式是否正確
    if (isValidPolynomial(polynomial)) {
        printf("多項式格式正確。\n");
    } else {
        printf("多項式格式錯誤。\n");
    }

    return 0;
}

// 函數定義
bool isValidPolynomial(char* polynomial) {
    char* ptr = polynomial;

    // 檢查多項式是否以 'x' 開頭
    if (*ptr != 'x' && *ptr != '+' && *ptr != '-') {
        return false;
    }
    ptr++;

    while (*ptr != '\0') {
        // 檢查是否有 '^'
        if (*ptr == '^') {
            ptr++;

            // 檢查 '^' 後面是否是數字
            if (!isdigit(*ptr)) {
                return false;
            }
            // 跳過數字部分
            while (isdigit(*ptr)) {
                ptr++;
            }
        }

        // 檢查 '+' 或 '-' 符號
        if (*ptr == '+' || *ptr == '-') {
            ptr++;

            // 檢查是否是 'x'
            if (*ptr != 'x') {
                return false;
            }
            ptr++;

            // 檢查 '^' 後面是否是數字
            if (*ptr == '^') {
                ptr++;
                if (!isdigit(*ptr)) {
                    return false;
                }
                while (isdigit(*ptr)) {
                    ptr++;
                }
            }
        }

        // 如果有其他不合法的字符
        if (!(*ptr == '\0' || *ptr == '+' || *ptr == '-' || *ptr == 'x')) {
            return false;
        }

        // 跳過空格
        while (*ptr == ' ') {
            ptr++;
        }
    }

    return true;
}
