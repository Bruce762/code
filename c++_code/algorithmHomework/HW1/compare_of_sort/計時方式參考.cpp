#include <iostream>
#include <chrono>

using namespace std;
using namespace std::chrono;

int main() {
    auto start = high_resolution_clock::now(); // 計時開始
    sort(); // 這裡放入你要計時的程式碼
    auto end = high_resolution_clock::now(); // 計時結束
    auto durationStd = duration_cast<nanoseconds>(end - start); // 計算耗時
    cout << "std::sort 花費時間: " << durationStd.count() << " 10^-9秒" << endl;
    return 0;
}