#include <stdio.h>

// 快速排序函式
void quickSort(int arr[], int left, int right) {
    if (left >= right) return;
    
    int pivot = arr[right]; // 選擇最右邊的元素作為樞紐點
    int i = left - 1;
    for (int j = left; j < right; j++) {
        if (arr[j] < pivot) {
            i++;
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
    
    int temp = arr[i + 1];
    arr[i + 1] = arr[right];
    arr[right] = temp;
    
    quickSort(arr, left, i);
    quickSort(arr, i + 2, right);
}

// 主程式
int main() {
    int arr[] = {34, 7, 23, 32, 5, 62};
    int n = sizeof(arr) / sizeof(arr[0]);
    
    printf("排序前: ");
    for (int i = 0; i < n; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");
    
    quickSort(arr, 0, n - 1);
    
    printf("排序後: ");
    for (int i = 0; i < n; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");
    
    return 0;
}
