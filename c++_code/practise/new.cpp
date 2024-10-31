#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main() {
    int *a = NULL;  // relloc之前的指標不能沒有指定東西，就算是NULL也好
    a = (int *)realloc(a, sizeof(int)*size);
    printf("%d", a[0]);
    return 0;
}
