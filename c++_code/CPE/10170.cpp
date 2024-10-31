#include <bits/stdc++.h>
#define ll long long
#define all(vec) vec.begin(), vec.end()
using namespace std;
signed main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    ll s,d;
    while(cin>>s>>d){
        ll sum=0,i=s;
        for(;sum<d;i++){
            sum+=i;
        }
        cout<<i-1<<endl;
    }
    
    return 0;
}