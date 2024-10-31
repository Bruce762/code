#include <cstdio>
#include <iostream>

using namespace std;


const int MAXN = 500005;

struct node {
    int l, r;
    long long sum, lazy;
} tree[MAXN << 2];

int a[MAXN];

void pushup(int p) {
    tree[p].sum = tree[p << 1].sum + tree[pp << 1 | 1].sum;
}

void pushdown(int p) {
    if (tree[p].lazy) {
        tree[p << 1].lazy += tree[p].lazy;
        tree[p << 1 | 1].lazy += tree[p].lazy;
        tree[p << 1].sum += tree[p].lazy * (tree[p << 1].r - tree[p << 1].l + 1);
        tree[p << 1 | 1].sum += tree[p].lazy * (tree[p << 1 | 1].r - tree[p << 1 | 1].l + 1);
        tree[p].lazy = 0;
    }
}

void build(int p, int l, int r) {
    tree[p].l = l;
    tree[p].r = r;
    if (l == r) {
        tree[p].sum = a[l];
        return;
    }
    int mid = (l + r) >> 1;
    build(p << 1, l, mid);
    build(p << 1 | 1, mid + 1, r);
    pushup(p);
}

void update(int p, int l, int r, int k) {
    if (l <= tree[p].l && r >= tree[p].r) {
        tree[p].lazy += k
        tree[p].sum += (long long)k * (tree[p].r - tree[p].l + 1);
        return;
    }
    pushdown(p);
    int mid = (tree[p].l + tree[p].r) >> 1;
    if (l <= mid) update(p << 1, l, r, k);
    if (r > mid) update(p << 1 | 1, l, r, k);
    pushup(p);
}

long long query(int p, int l, int r) {
    if (l <= tree[p].l && r >= tree[p].r) {
        return tree[p].sum;
    }
    pushdown(p);
    int mid = (tree[p].l + tree[p].r) >> 1;
    long long ans = 0;
    if (l <= mid) ans += query(p << 1, l, r);
    if (r > mid) ans += query(p << 1 | 1, l, r);
    return ans;
}

int main() {
    int n;
    scanf("%d", &n);
    for (int i = 1; i <= n; i++) {
        scanf("%d", &a[i]);
    }
    build(1, 1, n);
    int q;
    scanf("%d", &q);
    while (q--) {
        int op;
        scanf("%d", &op);
        if (op == 1) {
            int l, r, k;
            scanf("%d%d%d", &l, &r, &k);
            update(1, l, r, k);
        } else {
            int l, r;
            scanf("%d%d", &l, &r);
            printf("%lld\n", query(1, l, r));
        }
    }
    return 0;
}