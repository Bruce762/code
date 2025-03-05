#include <stdio.h>

// 堆排序函式
void heapify(int arr[], int n, int i) {
    int largest = i;
    int left = 2 * i + 1;
    int right = 2 * i + 2;
    
    printf("heapify: n=%d, i=%d, left=%d, right=%d\n", n, i, left, right);
    
    if (left < n && arr[left] > arr[largest]) {
        printf("-> arr[left] (%d) > arr[largest] (%d), updating largest\n", arr[left], arr[largest]);
        largest = left;
    }
    
    if (right < n && arr[right] > arr[largest]) {
        printf("-> arr[right] (%d) > arr[largest] (%d), updating largest\n", arr[right], arr[largest]);
        largest = right;
    }
    
    if (largest != i) {
        printf("-> Swapping arr[%d] (%d) with arr[%d] (%d)\n", i, arr[i], largest, arr[largest]);
        int temp = arr[i];
        arr[i] = arr[largest];
        arr[largest] = temp;
        
        heapify(arr, n, largest);
    }
}

void heapSort(int arr[], int n) {
    printf("Building heap\n");
    for (int i = n / 2 - 1; i >= 0; i--) {
        printf("Calling heapify on index %d\n", i);
        heapify(arr, n, i);
    }
    
    printf("Heap built. Sorting begins\n");
    for (int i = n - 1; i > 0; i--) {
        printf("-> Swapping arr[0] (%d) with arr[%d] (%d)\n", arr[0], i, arr[i]);
        int temp = arr[0];
        arr[0] = arr[i];
        arr[i] = temp;
        
        heapify(arr, i, 0);
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
    
    heapSort(arr, n);
    
    printf("排序後: ");
    for (int i = 0; i < n; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");
    
    return 0;
}
