#include <iostream>
#include <vector>
#include <bit>

#define int long long

using namespace std;

int nextPowerOf2(int n) {
    if (n <= 0) return 1;
    n--;  
    n |= n >> 1;
    n |= n >> 2;
    n |= n >> 4;
    n |= n >> 8;
    n |= n >> 16;
    n |= n >> 32;
    return n + 1;
}

vector<vector<int>> add(vector<vector<int>> matrix1, vector<vector<int>> matrix2) {
    int size = matrix1.size();
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            matrix1[i][j] = matrix1[i][j] + matrix2[i][j];
        }
    }
    return matrix1;
}

vector<vector<int>> sub(vector<vector<int>> matrix1, vector<vector<int>> matrix2) {
    int size = matrix1.size();
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            matrix1[i][j] = matrix1[i][j] - matrix2[i][j];
        }
    }
    return matrix1;
}

vector<vector<int>> matrix_multiply(vector<vector<int>> matrix1, vector<vector<int>> matrix2) {
    int size = matrix1.size();

    if (size <= 0) {
        return vector<vector<int>>();
    }

    if (size < 10) {
        vector<vector<int>> result(size, vector<int>(size, 0));
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return result;
    }

    int new_size = size / 2;
    vector<vector<int>> ans(size, vector<int>(size, 0));
    vector<vector<int>> a11(new_size, vector<int>(new_size, 0));
    vector<vector<int>> a12(new_size, vector<int>(new_size, 0));
    vector<vector<int>> a21(new_size, vector<int>(new_size, 0));
    vector<vector<int>> a22(new_size, vector<int>(new_size, 0));
    vector<vector<int>> b11(new_size, vector<int>(new_size, 0));
    vector<vector<int>> b12(new_size, vector<int>(new_size, 0));
    vector<vector<int>> b21(new_size, vector<int>(new_size, 0));
    vector<vector<int>> b22(new_size, vector<int>(new_size, 0));

    for (int i = 0; i < new_size; i++) {
        for (int j = 0; j < new_size; j++) {
            a11[i][j] = matrix1[i][j];
            a12[i][j] = matrix1[i][j + new_size];
            a21[i][j] = matrix1[i + new_size][j];
            a22[i][j] = matrix1[i + new_size][j + new_size];
            b11[i][j] = matrix2[i][j];
            b12[i][j] = matrix2[i][j + new_size];
            b21[i][j] = matrix2[i + new_size][j];
            b22[i][j] = matrix2[i + new_size][j + new_size];
        }
    }

    vector<vector<int>> m1 = matrix_multiply(add(a11, a22), add(b11, b22));
    vector<vector<int>> m2 = matrix_multiply(add(a21, a22), b11);
    vector<vector<int>> m3 = matrix_multiply(a11, sub(b12, b22));
    vector<vector<int>> m4 = matrix_multiply(a22, sub(b21, b11));
    vector<vector<int>> m5 = matrix_multiply(add(a11, a12), b22);
    vector<vector<int>> m6 = matrix_multiply(sub(a21, a11), add(b11, b12));
    vector<vector<int>> m7 = matrix_multiply(sub(a12, a22), add(b21, b22));

    vector<vector<int>> c11 = add(sub(add(m1, m4), m5), m7);
    vector<vector<int>> c12 = add(m3, m5);
    vector<vector<int>> c21 = add(m2, m4);
    vector<vector<int>> c22 = add(sub(add(m1, m3), m2), m6);

    vector<vector<int>> result(size, vector<int>(size));
    for (int i = 0; i < new_size; i++) {
        for (int j = 0; j < new_size; j++) {
            result[i][j] = c11[i][j];
            result[i][j + new_size] = c12[i][j];
            result[i + new_size][j] = c21[i][j];
            result[i + new_size][j + new_size] = c22[i][j];
        }
    }
    return result;
}

signed main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    int a, b, c, d;
    int size = 0;
    cin >> a >> b >> c >> d;
    size = nextPowerOf2(max(a, b));
    
    vector<vector<int>> matrix1(size, vector<int>(size, 0));
    vector<vector<int>> matrix2(size, vector<int>(size, 0));

    for (int i = 0; i < a; i++) {
        for (int j = 0; j < b; j++) {
            cin >> matrix1[i][j];
        }
    }
    for (int i = 0; i < c; i++) {
        for (int j = 0; j < d; j++) {
            cin >> matrix2[i][j];
        }
    }
    // matrix1 * matrix2
    vector<vector<int>> ans(size, vector<int>(size, 0));
    ans = matrix_multiply(matrix1, matrix2);
    for (int i = 0; i < a; i++) {
        for (int j = 0; j < d; j++) {
            cout << ans[i][j] << " ";
        }
    }
    return 0;
}