#include <bits/stdc++.h>
#include <iostream>
#define all(vec) vec.begin(), vec.end()

using namespace std;

int cmp(vector<int> a, vector<int> b) {
    if (a[0] != b[0])
        return a[0] < b[0];
    else if (a[1] != b[1])
        return a[1] < b[1];
    else
        return a[2] < b[2];
}
signed main() {
    vector<vector<int>> arr = {{3, 2, 4}, {2, 1, 2}, {5, 7, 2}, {3, 1, 4}};
    sort(arr.begin(), arr.end(), cmp);
}