#include <stdio.h>
#include <stdlib.h>





void bubble_sort(int arr[], int n) {
    
}

int main() {
    int arr[1000005];
    int count = 0;
    char ope;
    while (scanf("%c", &ope)) {
        if (ope == 'i') {
            for (int i = 0; i < 10000; i++) {
                count = i;
                if (scanf("%d", &arr[i]) != 1) {
                    break;
                }
            }
            
            for (int i = 0; i < count; ++i) {
                for (int j = 0; j < count - i - 1; ++j) {
                    if (arr[j] > arr[j + 1]) {
                        int temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                }
            }
            /*for (int i = 0; i < count; i++) {
                printf("%d", arr[i]);
            }*/
        } else if (ope == 'f') {
            int n;
            scanf("%d",&n);
            n--;
            if(n>=count || n<0){
                printf("NULL\n");
            }else{
                printf("%d\n",arr[n]);
            }
        } else if (ope == '0'){
            printf("quit");
        }
    }

    return 0;
}
/*
i
1 2 3 4 5 6 7 8
f 5


*/