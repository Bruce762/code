#include <bits/stdc++.h>
#define all(vec) vec.begin(), vec.end()
#define int long long int
using namespace std;

signed main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    int n;
    vector<int> arr;
    arr.push_back(1);
    arr.push_back(2);
    for (int i = 2; arr[i - 1] < 100000000; i++) {
        arr.push_back(arr[i - 1] + arr[i - 2]);
    }
    cin >> n;
    while (n--) {
        string ss;
        int num,pnum;
        cin>>num;
        pnum=num;
        auto pos=upper_bound(all(arr),num);
        for(auto i=pos-1;i>=arr.begin();i--){
            if(num>=*i){
                ss.push_back('1');
                num-=*i;
            }else{
                ss.push_back('0');
            }
        }
        cout<<pnum<<" = "<<ss<<" (fib)"<<endl;
    }
    return 0;
}
