#include <fstream>
#include <iostream>
#include <sstream>
#define int long long
using namespace std;

signed main() {
    string line;
    fstream file("data.csv");
    if (!file) {
        cerr << "無法開啟檔案" << endl;
    }
    file.clear();
    file.seekg(0);

    while (getline(file, line)) {
        line.erase(0, line.find_first_not_of(" \t\r\n"));  // 去掉前導空白
        line.erase(line.find_last_not_of(" \t\r\n") + 1);  // 去掉尾部空白

        stringstream s(line);
        string tmp;
        vector<int> arr;
        while (getline(s, tmp, ',')) {
            arr.push_back(stoi(tmp));
        }
        for (auto i : arr) cout << i;
    }
}
