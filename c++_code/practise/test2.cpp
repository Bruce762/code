#include <bits/stdc++.h>
#define int long long
    using namespace std;

struct segment_tree {
    int add, value;
};

const int N = 6e+5;
vector<int> arr(N);
vector<segment_tree> st(4 * N);
int n;
void build(int l, int r, int index) {
    if (l == r) {
        st[index].value = arr[l];
    } else {
        int mid = (l + r) / 2, lson = index * 2, rson = index * 2 + 1;
        build(l, mid, lson);
        build(mid + 1, r, rson);
        st[index].value = st[rson].value + st[lson].value;
    }
}

void look() {
    int k = 1;
    cout << "look:" << endl;
    for (int i = 1; i < 4 * n; i++) {
        cout << st[i].value;
        if (k == i) {
            cout << endl;
            k = k * 2 + 1;
        }
    }
    cout << endl;
    k = 1;
    for (int i = 1; i < 4 * n; i++) {
        cout << st[i].add;
        if (k == i) {
            cout << endl;
            k = k * 2 + 1;
        }
    }
    cout << endl;
}

void push(int lson, int rson, int size, int index) {
    st[index].value += st[index].add * size;
    // cout<<"index: "<<index<<" add: "<<st[index].add<<endl;
    if (st[index].add > 0) {
        if (lson > 0) st[lson].add += st[index].add;
        if (rson > 0) st[rson].add += st[index].add;
    }
    st[index].add = 0;
}
// 1 2 3+5 4+5 5+5+3 6 7
int query(int a, int b, int l, int r, int index) {
    int mid = (l + r) / 2, lson = index * 2, rson = index * 2 + 1;
    if (l == r) rson = lson = -1;
    push(lson, rson, r - l + 1, index);
    if (a <= l && b >= r) {
        return st[index].value;
    } else {
        if (a > mid) {
            return query(a, b, mid + 1, r, rson);
        } else if (b <= mid) {
            return query(a, b, l, mid, lson);
        } else
            return query(a, b, l, mid, lson) + query(a, b, mid + 1, r, rson);
    }
    return 0;
}

void add_interval(int add, int a, int b, int l, int r, int index) {
    int mid = (l + r) / 2, lson = index * 2, rson = index * 2 + 1;
    if (a == l && b == r) rson = lson = -1;
    push(lson, rson, r - l + 1, index);
    if (a <= l && b >= r) {
        st[index].add = add;
    } else if (a > r || b < l) {
        return;
    } else {
        int left_size = mid - l + 1, right_size = r - (mid + 1) + 1;
        add_interval(add, a, b, l, mid, lson);
        add_interval(add, a, b, mid + 1, r, rson);
        st[index].value = st[lson].value + st[lson].add * left_size + st[rson].value + st[rson].add * right_size;
    }
    return;
}

void solve() {
    int ins, a, b, k;
    cin >> ins;
    if (ins == 1) {  // add
        cin >> a >> b >> k;
        add_interval(k, a - 1, b - 1, 0, n - 1, 1);
        // look();
    } else if (ins == 2) {  // query
        cin >> a >> b;
        cout << query(a - 1, b - 1, 0, n - 1, 1) << endl;
    }
}

signed main() {
    int m;
    cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> arr[i];
    }
    build(0, n - 1, 1);
    // look();
    cin >> m;
    while (m--) {
        solve();
    }
    return 0;
}
