#include <bits/stdc++.h>
#define int long long int
#define all(vec) vec.begin(),vec.end()
using namespace std;
signed main(){
    int n;
    while(cin>>n){
        vector<int> arr(n);
        for(int i=0;i<n;i++){
            cin>>arr[i];
        }
        sort(all(arr));
        if(n%2==1){
            cout<<arr[n/2]<<" "<<upper_bound(all(arr),arr[n/2])-lower_bound(all(arr),arr[n/2])<<" "<<1<<endl;
        }else{
            int a=min(arr[n/2-1],arr[n/2]);
            cout<<a<<" ";
            if(arr[n/2-1]!=arr[n/2])
                cout<<upper_bound(all(arr),arr[n/2])-lower_bound(all(arr),arr[n/2])+upper_bound(all(arr),arr[n/2-1])-lower_bound(all(arr),arr[n/2-1])<<" "<<2+arr[n/2]-arr[n/2-1]-1<<endl;
            else
                cout<<upper_bound(all(arr),arr[n/2])-lower_bound(all(arr),arr[n/2])<<" "<<1<<endl;
        }
    }
    //0 1 2 2 4 4 5 5
    //0 1 2
    //1 1 1 2 3 4 5   1 1 1 1 2 3=9  1 2 3 4 =10
    //1 1 1 102 103 104 105
    return 0;
}
