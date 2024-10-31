#include <bits/stdc++.h>
using namespace std;

int main() {
    int a,b;
    while(cin>>a>>b){
        int carry=0,sum=0;
        if(a==0 && b==0)break;
        while (a!=0 || b!=0){
            if(a%10+b%10+carry>=10){
                carry=1;
                sum++;
            }else{
                carry=0;
            }
            a/=10;
            b/=10;
        }
        if(!sum)cout<<"No carry operation."<<endl;
        else if(sum==1)cout<<sum<<" carry operation."<<endl;
        else cout<<sum<<" carry operations."<<endl;
    }
    return 0;
}