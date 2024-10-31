#include <stdio.h>

void bubble_sort(int arr[], int n);
void output_arr(int arr[], int n) {
    for (int i = 0; i < n; ++i) {
        printf("%d ", arr[i]);
    }
    printf("\n");
}

int main() {
    char n;
    int num;
    int x;
    int arr[100];
   
    while (1) {
        scanf(" %c", &n);

        if (n == 'i') {
            x = 0;
            for (int i = 0; i < 100; i++) {
                if (scanf("%d", &arr[i]) != 1) {
                    break;
                }
                x++;
            }
            bubble_sort(arr, x);
        } else if (n == 'f') {
            scanf("%d", &num);
            if (num > 0 && num <= x) {
                printf("%d\n", arr[num - 1]);
            } else {
                printf("NULL\n");
            }
        } else if (n == '0') {
        printf("quit");
            break;
        }
    }

    return 0;
}

/* Bubble Sort */
void bubble_sort(int arr[], int n) {
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n - i - 1; ++j) {
            if (arr[j] > arr[j + 1]) {
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }
}