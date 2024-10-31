#include <bits/stdc++.h>
#define all(vec) vec.begin(), vec.end()
#define int long long int
using namespace std;

int val(char c){
    if(c>='0'&&c<='9'){
        return c-'0';
    }else if(c>='A'&&c<='Z'){
        return c-'A'+10;
    }else if(c>='a'&&c<='z'){
        return c-'a'+36;
    }else
        return -1;
}

signed main() {
    string s;
    while(cin>>s){
        int Max=1;
        int flag=0;
        for(int i=0;i<s.size();i++){
            Max=max(Max,val(s[i]));
        }
        for(int i=Max+1;i<=62;i++){
            int sum=0;
            for(int j=0;j<s.size();j++){
                sum=sum*i+val(s[j]);
            }

            if(sum%(i-1)==0){
                cout<<i<<endl;
                flag=1;
                break;
            }
        }
        if(!flag)cout<<"such number is impossible!"<<endl;
        s.clear();
    }
    
}
