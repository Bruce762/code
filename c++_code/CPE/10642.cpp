#include <bits/stdc++.h>
#define all(vec) vec.begin(), vec.end()
#define int long long int
using namespace std;
void start() {
    
}
signed main() {
    int n;
    cin >> n;
    for(int i=0;i<n;i++){
        int x1,y1,x2,y2,sum1=0,sum2=0;
        cin>>x1>>y1>>x2>>y2;
        sum1=(x1+y1)*(x1+y1+1)/2+x1;
        sum2=(x2+y2)*(x2+y2+1)/2+x2;
        cout<<"Case "<<i+1<<": "<<sum2-sum1<<endl;
    }
    return 0;
}