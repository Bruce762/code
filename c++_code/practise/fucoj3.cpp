#include <bits/stdc++.h>
using namespace std;

int dp(int *arr1, int *arr2, int n1, int n2) {
    int sum[1005][1005] = {0};
    for (int i = 1; i <= n1; i++) {
        for (int j = 1; j <= n2; j++) {
            // cout << arr1[i] << " " << arr2[j] << endl;
            if (arr1[i] == arr2[j])
                sum[i][j] = sum[i - 1][j - 1] + 1;
            else
                sum[i][j] = max(sum[i - 1][j], sum[i][j - 1]);
        }
    }
    return sum[n1][n2];
}

int main() {
    int first_len, second_len, index = 1;
    while (cin >> first_len >> second_len) {
        if(first_len==0 && second_len==0)break;
        int arr1[10005], arr2[10005];
        for (int i = 1; i <= first_len; i++) cin >> arr1[i];
        for (int i = 1; i <= second_len; i++) cin >> arr2[i];
        cout << "Twin Towers #" << index <<endl<< "Number of Tiles : " << dp(arr1, arr2, first_len, second_len) << endl;
        cout << endl;
        index++;
    }
    return 0;
}