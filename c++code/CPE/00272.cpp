#include<bits/stdc++.h>
#define int long long int
#define bn(vec) (vec).begin(),(vec).end()
using namespace std;
signed main(){
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    string ss;
    int cnt=0;
    while(getline(cin,ss)){
        for(int i=0;i<ss.size();i++){
            if(ss[i]=='"'){
                if(cnt%2==0){
                    cout<<"``";
                }else if(cnt%2==1)
                    cout<<"''";
                cnt++;
            }else
                cout<<ss[i];
        }
        cout<<endl;
    }
    return 0;
}