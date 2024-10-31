#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct Nodes {
    int pos;
    char str[1000];
} typedef Nodes;

void find(Nodes *arr,int cnt,int arrPos){
    char str[10005];
    int strPos=0;
    arrPos=1;
    scanf("%s",str);
    if(str[strPos]==arr[arrPos].str[0]){
        //找左 找右
        find(arr,cnt,);
        find(arr,cnt,)
    }
}


signed main() {
    int n;
    int cnt = 1;
    Nodes arr[1000];
    scanf("%d", &n);
    if (n == 1) {
        for (int i = 1;1; i *= 2) {
            bool check = 0;
            for (int j = 0; j < i; j++) {
                scanf("%s", arr[cnt].str);
                printf("%s", arr[cnt].str);
                arr[cnt].pos = j;
                
                if (strcmp(arr[cnt].str,"end")==0) {
                    check = 1;
                    break;
                }
                cnt++;
            }
            if (check) break;
        }

        printf("\n");
        int sss=1;
        for (int i = 1; i <= cnt; i++) {
            printf("%s ",arr[i].str);
            if(i==sss){
                printf("\n");
                sss=sss*2+1;
            }
        }

    } else if (n == 2) {
        find(arr,cnt);
    }
    return 0;
}
/*
1
a
b c
d e f 99 
end 

*/