#include <bits/stdc++.h>
#define all(vec) vec.begin(), vec.end()
#define int long long int
using namespace std;


int cmp( pair<int,int> a, pair<int,int> b) {
    if(a.second==b.second)return a.first>b.first;
    else
        return a.second < b.second;
}


signed main() {
    string s;
    int iffirst = 0;
    while (getline(cin, s)) {
        string p;
        int arr[1000] = {0};
        vector<pair<int,int> >result;
        if (iffirst) printf("\n");
        for (int i = 0; i < s.size(); i++) {
            if (arr[s[i]] == 0) {
                p.push_back(s[i]);
            }
            arr[s[i]]++;
        }
        for (int i = 0; i < p.size(); i++)
            result.push_back(make_pair(p[i], arr[p[i]]));
        sort(all(result),cmp);
        for(int i=0;i<result.size();i++){
            printf("%lld %lld\n",result[i].first,result[i].second);
        }
        iffirst=1;
        result.clear();
        p.clear();
    }
}
