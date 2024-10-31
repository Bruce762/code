#include<bits/stdc++.h>
#define int long long int
using namespace std;
signed main(){
    int l,r;
    while(cin>>l>>r){
        int Max=0,flag=0;
        if(l>r){
            swap(l,r);
            flag=1;
        }
        for(int i=l;i<=r;i++){
            int n=i,step=1;
            while(n!=1){
                if(n%2)n=3*n+1;
                else n/=2;
                step++;
            }
            Max=max(Max,step);
        }
        if(!flag)cout<<l<<" "<<r<<" "<<Max<<endl;
        else cout<<r<<" "<<l<<" "<<Max<<endl;
    }
    return 0;
}