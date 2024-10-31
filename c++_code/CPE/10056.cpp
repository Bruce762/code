#include <bits/stdc++.h>
#define all(vec) vec.begin(), vec.end()
#define int long long int
using namespace std;
void start() {
    double sum = 0, p;
    int num, a;
    cin >> num >> p >> a;
    for (int i = 0; i < 100000; i++) {
        sum += pow(1 - p, a - 1) * p * pow(1 - p, i * num);
    }
    printf("%.4f\n", sum);
}
signed main() {
    int n;
    cin >> n;
    while (n--) {
        start();
    }
    return 0;
}