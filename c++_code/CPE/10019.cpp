#include <bits/stdc++.h>
#define all(vec) vec.begin(), vec.end()
#define int long long int
using namespace std;

int transB1(int n) {
    int sum = 0;
    while (n != 0) {
        sum += n % 2;
        n /= 2;
    }
    return sum;
}
int transB2(int n) {
    int sum=0;
    char ss[10000];
    sprintf(ss,"%d",n);
    for(int i=0;i<strlen(ss);i++)sum=sum*16+ss[i]-'0';
    return transB1(sum);
    return 0;
}

void start() {
    int n;
    cin >> n;
    cout << transB1(n) << " " << transB2(n) << endl;
}

signed main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    int n;
    cin >> n;
    while (n--) {
        start();
    }
}