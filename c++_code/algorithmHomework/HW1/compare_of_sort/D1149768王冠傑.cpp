#include <chrono>
#include <fstream>
#include <iostream>
#include <sstream>
#define int long long

using namespace std;
using namespace std::chrono;

void bubble_sort(vector<int> &arr) {
    for (int i = 0; i < arr.size(); i++) {
        for (int j = i + 1; j < arr.size(); j++) {
            if (arr[i] > arr[j]) swap(arr[i], arr[j]);
        }
    }
}

int qsort_partirion(vector<int> &arr, int head, int tail) {
    int i = head;
    for (int j = head; j < tail; j++) {
        if (arr[j] < arr[tail]) {
            swap(arr[i], arr[j]);
            i++;
        }
    }
    swap(arr[i], arr[tail]);
    return i;
}
void quick_sort(vector<int> &arr, int head, int tail) {
    if (head < tail) {
        int mid = qsort_partirion(arr, head, tail);
        quick_sort(arr, head, mid - 1);
        quick_sort(arr, mid + 1, tail);
    }
}

void heap_merge(vector<int> &arr, int head, int tail) {
    int mid = (head + tail) / 2;
    int i = 0, j = 0;
    vector<int> left(arr.begin() + head, arr.begin() + mid + 1);
    vector<int> right(arr.begin() + mid + 1, arr.begin() + tail + 1);
    left.push_back(INT_MAX);
    right.push_back(INT_MAX);
    for (int k = head; k <= tail; k++) {
        if (left[i] < right[j]) {
            arr[k] = left[i];
            i++;
        } else {
            arr[k] = right[j];
            j++;
        }
    }
}
void heap_sort(vector<int> &arr, int head, int tail) {
    if (head < tail) {
        int mid = (head + tail) / 2;
        heap_sort(arr, head, mid);
        heap_sort(arr, mid + 1, tail);
        heap_merge(arr, head, tail);
    }
}

int cal_sort_time(vector<int> &arr, int round) {
    auto start = high_resolution_clock::now();  // 計時開始
    if (round == 0) {
        bubble_sort(arr);
    } else if (round == 1) {
        quick_sort(arr, 0, arr.size() - 1);
    } else if (round == 2) {
        sort(arr.begin(), arr.end());
    } else if (round == 3) {
        heap_sort(arr, 0, arr.size() - 1);
    }

    auto end = high_resolution_clock::now();                     // 計時結束
    auto durationStd = duration_cast<nanoseconds>(end - start);  // 計算耗時
    return durationStd.count();
}

signed main() {
    int avg = 0;
    string line;
    fstream file("data.csv");
    if (!file) {
        cerr << "無法開啟檔案" << endl;
    }
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    for (int i = 0; i < 4; i++) {
        int cnt = 1;
        file.clear();
        file.seekg(0);
        switch (i) {
            case 0:
                cout << "氣泡排序" << endl;
                break;
            case 1:
                cout << "快速排序" << endl;
                break;
            case 2:
                cout << "內建排序" << endl;
                break;
            case 3:
                cout << "推積排序" << endl;
                break;
            default:
                break;
        }
        while (getline(file, line)) {
            if (!line.empty() && line.back() == '\r') {
                line.pop_back();
            }

            stringstream s(line);
            string tmp;
            vector<int> arr;
            while (getline(s, tmp, ',')) {
                arr.push_back(stoi(tmp));
            }

            // 2次取平均
            avg = 0;
            for (int j = 0; j < 2; j++) {
                vector<int> tmp_arr = arr;
                avg += cal_sort_time(tmp_arr, i);
            }
            avg /= 2;

            cout << "第" << cnt << "筆資料：" << "花費時間 " << cal_sort_time(arr, i) << " 10^-9秒" << endl;
            //cout << cal_sort_time(arr, i) << endl;
            cnt++;
        }
        cnt = 0;
    }
}