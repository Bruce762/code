#include <bits/stdc++.h>
#define all(vec) vec.begin(), vec.end()
#define int long long int
using namespace std;
//'kuti' (10000000)7, 'lakh' (100000)5, 'hajar' (1000)3, 'shata' (100)2
// 4 58 00 958
bool allzero(string s) {
    for (int i = 0; i < s.size(); i++) {
        //cout << " zero "<<s.size()<<" " << s[i] <<"|"<< endl;
        if (s[i] != '0') {
            return 0;
        }
    }
    return 1;
}

void printS(string s){
    int flag=0;
    for(int i=0;i<s.size();i++){
        if(s[i]!='0')flag=1;
        if(flag)cout<<s[i];
    }
}

void func(string s) {  // 49 42
    if (s.size() > 7) {
        func(s.substr(0, s.size() - 7));
        if (!allzero(s.substr(0, s.size() - 7)))cout << " " << "kuti";
        s = s.substr(s.size() - 7);
    }
    if (s.size() > 5) {
        string tmp = s.substr(0, s.size() - 5);
        s = s.substr(s.size() - 5);
        if (!allzero(tmp)){
            cout<<" ";
            printS(tmp);
            cout <<" lakh";
        }

    }
    if (s.size() > 3) {
        string tmp = s.substr(0, s.size() - 3);
        s = s.substr(s.size() - 3);
        if (!allzero(tmp)){
            cout<<" ";
            printS(tmp);
            cout <<" hajar";
        }
    }
    if (s.size() > 2) {
        string tmp = s.substr(0, s.size() - 2);
        s = s.substr(s.size() - 2);
        if (!allzero(tmp)){
            cout<<" ";
            printS(tmp);
            cout <<" shata";
        }
    }
    if (!allzero(s)){
        cout<<" ";
        printS(s);
    }
}
signed main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    string s;
    int i = 1;
    while (cin >> s) {
        cout << i << ".";
        if(s=="0")cout<<" 0"<<endl;
        else func(s);
        cout << endl;
        i++;
    }

    return 0;
}
