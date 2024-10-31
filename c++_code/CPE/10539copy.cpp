#include <bits/stdc++.h>
#define int long long
#define all(vec) vec.begin(), vec.end()
using namespace std;

const int N = 1000005;
vector<bool> arr(N, 0);
vector<int> prime;
vector<int> ap;

//法一 使用埃拉托斯特尼篩法生成質數
void buildPrime() {
    for (int i = 2; i < N; i++) {
        if (!arr[i]) {
            prime.push_back(i);
            if (i * i < N) {
                for (int j = i * i; j < N; j += i) {
                    arr[j] = 1;
                }
            }
        }
    }
}
// 法二 使用歐拉篩法生成質數
vector<bool> isprime(N + 1, true);
void buildPrime2() {
    isprime[0] = isprime[1] = false;
    for (int i = 2; i <= N; i++) {
        if (isprime[i]) prime.push_back(i);
        for (int j = 0; j < prime.size() && i * prime[j] <= N; j++) {
            isprime[i * prime[j]] = false;
            if (i % prime[j] == 0) break;  // 提前退出
        }
    }
}

void buildAlmostP() {
    for (int i = 0; i < prime.size(); i++) {
        for (long long j = prime[i] * prime[i]; j < 1e12; j *= prime[i]) {
            ap.push_back(j);
        }
    }
    sort(all(ap));
}

signed main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    long long n, l, r;
    buildPrime2();
    buildAlmostP();
    // for (auto i : ap) cout << i << " ";
    cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> l >> r;
        cout << upper_bound(all(ap), r) - lower_bound(all(ap), l) << endl;
    }

    return 0;
}