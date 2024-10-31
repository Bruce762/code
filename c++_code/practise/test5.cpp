#include <bits/stdc++.h>
#define int long long
using namespace std;
const int N = 5e+5;
int n;
struct segment_tree {
    int ch, value;
};

int arr[N];
segment_tree st[4 * N];

// 懶人標記向下壓
void push(int size, int lson, int rson, int index) {
    st[index].value += st[index].ch * size;
    if (st[index].ch > 0) {
        if (lson > 0) st[lson].ch += st[index].ch;
        if (rson > 0) st[rson].ch += st[index].ch;
    }
    st[index].ch = 0;
}

// 建樹
void build(int l, int r, int index) {
    if (l == r) {
        // cout << l;
        st[index].value = arr[l];
    } else {
        int mid = (l + r) / 2, lson = index * 2, rson = index * 2 + 1;
        build(l, mid, lson);
        build(mid + 1, r, rson);
        st[index].value = st[lson].value + st[rson].value;
    }
}

// 區間查詢
int query(int a, int b, int l, int r, int index) {
    int mid = (l + r) / 2, lson = index * 2, rson = index * 2 + 1;
    if (l == r) lson = rson = -1;
    push(r - l + 1, lson, rson, index);  // 沒有用到懶標的話這行跟上面那行就不用打
    if (a <= l && b >= r) {
        return st[index].value;
    } else {
        if (a > mid) {
            return query(a, b, mid + 1, r, rson);
        } else if (b <= mid) {
            return query(a, b, l, mid, lson);
        } else {
            return query(a, b, mid + 1, r, rson) + query(a, b, l, mid, lson);
        }
    }
    return 0;
}

// 單點修改
void change_onepoint(int ch, int pos, int l, int r, int index) {
    if (pos == l && pos == r) {
        st[index].value = ch;
    } else {
        int mid = (l + r) / 2, lson = index * 2, rson = index * 2 + 1;
        if (pos > mid) {
            change_onepoint(ch, pos, mid + 1, r, rson);
        } else if (pos <= mid) {
            change_onepoint(ch, pos, l, mid, lson);
        }
        st[index].value = max(st[lson].value, st[rson].value);
    }
    return;
}

// 區間改值 利用懶標
void change_interval(int ch, int a, int b, int l, int r, int index) {
    int mid = (l + r) / 2, lson = index * 2, rson = index * 2 + 1;
    if (l == r) rson = lson = -1;
    push(r - l + 1, lson, rson, index);
    if (a <= l && b >= r) {
        st[index].ch += ch;
    } else if (a > r || b < l) {
        return;
    } else {
        change_interval(ch, a, b, mid + 1, r, rson);
        change_interval(ch, a, b, l, mid, lson);
        int x1 = st[lson].value + st[lson].ch * (mid - l + 1);
        int x2 = st[rson].value + st[rson].ch * (r - (mid + 1) + 1);
        st[index].value = x1 + x2;
    }
    return;
}

void look() {
    int k = 1;
    cout << endl;
    cout << "look:" << endl;
    for (int i = 1; i < n * 4; i++) {
        cout << st[i].value << " ";
        if (i == k) {
            cout << endl;
            k = k * 2 + 1;
        }
    }
    k = 1;
    for (int i = 1; i < n * 4; i++) {
        cout << st[i].ch << " ";
        if (i == k) {
            cout << endl;
            k = k * 2 + 1;
        }
    }
    cout << endl;
}

void solve() {
    int ins, a, b, k;
    cin >> ins;
    if (ins == 1) {  // add
        cin >> a >> b >> k;
        change_interval(k, a - 1, b - 1, 0, n - 1, 1);
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