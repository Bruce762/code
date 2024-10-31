#include <string.h>

#include <iostream>
#include <queue>
using namespace std;
#define mxsz 1005

struct pos {
    int x, y;
    bool p;
    int d;
};

int s[mxsz][mxsz];
char v[mxsz][mxsz];

int vec[4][2] = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

int bfs(int n, int m) {
    pos st;
    queue<pos> q;
    for (int i = 0; i < n; i++)
        for (int j = 0; j < m; j++) {
            if (v[i][j] == 'J') st = {i, j, 1, 0};  // Line A
            if (v[i][j] == 'F') q.push({i, j, 0, 0});
        }

    q.push(st);  // LINE B
    s[st.x][st.y] = 0;
    while (!q.empty()) {
        pos p = q.front();
        q.pop();
        if (p.p && (p.x == 0 || p.x == n - 1 || p.y == 0 || p.y == m - 1)) return s[p.x][p.y];
        for (int k = 0; k < 4; k++) {
            int nx = p.x + vec[k][0], ny = p.y + vec[k][1];
            if (nx >= 0 && nx < n && ny >= 0 && ny < m && v[nx][ny] == '.' && s[nx][ny] == -1) {
                s[nx][ny] = p.d + 1;
                q.push({nx, ny, p.p, s[nx][ny]});
            }
        }
    }
    return -1;
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    int t, n, m;
    cin >> t;
    for (int kas = 1; kas <= t; kas++) {
        memset(s, -1, sizeof(s));

        cin >> n >> m;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                cin >> v[i][j];

        int sum = bfs(n, m);
        if (sum == -1)
            cout << "IMPOSSIBLE\n";
        else
            cout << sum + 1 << '\n';
    }
}