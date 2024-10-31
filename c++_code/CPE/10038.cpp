#include <bits/stdc++.h>
#define int long long int
#define all(vec) a.begin(),a.end()
using namespace std;

signed main(){
    int n;
    while(cin>>n){
        set<int> s;
        int a,b;
        cin>>a;
        for(int i=0;i<n-1;i++){
            cin>>b;
            s.insert(abs(a-b));
            a=b;
        }
        if(s.size()!=n-1)cout<<"Not jolly"<<endl;
        else cout<<"Jolly"<<endl;
    }
    return 0;
}