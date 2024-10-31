#include <bits/stdc++.h>
using namespace std;

bool cmp(int a, int b) {
    return a > b;
}

int main() {
    int n;
    cin >> n;
    while (n--) {
        int w, h, k, total = 0, tmp = 0;
        int sum[10005] = {0};
        vector<int> arr1, arr2;
        cin >> w >> h;
        for (int i = 0; i < w; i++) {
            cin >> k;
            if (k != 0) arr1.push_back(k);
        }
        for (int i = 0; i < h; i++) {
            cin >> k;
            if (k != 0) arr2.push_back(k);
        }
        sort(arr1.begin(), arr1.end(), cmp);
        sort(arr2.begin(), arr2.end(), cmp);
        for (int i = 0; i < arr1.size(); i++) {
            for (int j = 0; j < arr1[i]; j++) {
                sum[j]++;
                total++;
            }
        }
        for (int i = 0; i < arr2.size(); i++) {
            if (sum[i] < arr2[i]) {
                int add = arr2[i] - sum[i];
                if (tmp > add) {
                    tmp -= add;
                } else {
                    total += arr2[i] - sum[i] - tmp;
                    tmp = 0;
                }
            }
            if (sum[i] > arr2[i]) tmp += sum[i] - arr2[i];
        }
        cout << total << endl;
    }
    return 0;
}