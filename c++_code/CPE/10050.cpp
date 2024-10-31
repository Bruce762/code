#include <bits/stdc++.h>
#define int long long int
#define all(vec) a.begin(),a.end()
using namespace std;

void start(){
    int day,cnt,sum=0;
    cin>>day;
    cin>>cnt;
    int* arr=(int*) calloc(day+1,sizeof(int));
    for(int i=0;i<cnt;i++){
        int d,pos;
        cin>>d;
        pos=d;
        while(pos<day+1){
            if(arr[pos]==0){
                arr[pos]=1;
                if(pos%7!=6 && pos%7!=0){
                    sum+=1;
                }
            }
            pos+=d;
        }
    }
    cout<<sum<<endl;
}
// 0000100 0010000
signed main(){
    int n;
    cin>>n;
    while(n--){
        start();
    }
    return 0;
}