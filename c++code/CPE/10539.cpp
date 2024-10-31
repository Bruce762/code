#include <bits/stdc++.h>
#define ll long long
#define all(vec) vec.begin(), vec.end()
#define MAX 1000001
using namespace std;
/*
const int N = 10005;
vector<bool> arr(N, 0);
vector<int> prime;
*/
bool mark[MAX];          // 求使用篩選法求1000001以內質數，true非質數  false質數
int prime[78498];        // 1000001以內質數有78498個
long long int ans[MAX];  // 儲存質數的n次方
void erase() {
    int sq = (int)sqrt(1000001.0);
    mark[1] = true;  // 1不是質數
    for (int i = 2; i <= sq; i++) {
        if (!mark[i]) {  // 將2,3,5...倍數的數皆不是質數
            for (int j = i * i; j < 1000001; j += i) {
                mark[j] = true;
            }
        }
    }
}

signed main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    int n;
    /*
    for (int i = 2; i < N; i++) {
        if (!arr[i]) {
            prime.push_back(i);
            if (i * i < N) {
                for (int j = i * i; j < N; j += i) {
                    arr[j] = 1;
                }
            }
        }
    }*/
    int num=0;
    erase();
    for (int i = 2; i < 1000001; i++) {  // 將1000000以內質數記錄於prime陣列
        if (!mark[i]) {
            prime[num] = i;
            num++;
        }
    }
    for (auto i : prime) cout << i << " ";

    for (int i = 0; i < n; i++) {
    }
    return 0;
}