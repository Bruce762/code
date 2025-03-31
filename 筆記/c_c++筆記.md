---
title: c/c++筆記
tags: [Coding]
---

---

## tags: Coding

# C/C++筆記

## 編譯與執行

c 用 gcc c++用 g\+\+
第一行編譯
第二行執行
**在 mac 上**
![image](https://hackmd.io/_uploads/SJX1GhUyR.png)

```cpp=
gcc -o aaa.c test //aaa.c要編譯的檔案 test指定的名子 -o代表重新命名
./test           //直接執行
./test input.txt // ./當前資料夾 input.txt餵給argc
./test<input.txt // ./當前資料夾 input.txt內的內容當成輸入資料
```

### 法一 argc 傳遞檔案名稱作為參數

```cpp=
./test  input.txt
```

引入 terminal 中的字
argc 是 argc 的長度，argc[]是存字串的陣列
argv[0]是程式的路徑，argv[1]是輸入的第一個字，argv[2]是第二個，依此類推
字與字中間用空白隔開
如果沒輸入東西 argc 會是 1，裡面是執行檔的路徑

**terminal**
![截圖 2024-03-31 下午1.42.22](https://hackmd.io/_uploads/B1XWAoIJA.png)

**test.c 內容**

```cpp=
int main(int argc, char *argv[]) {
    for (int i = 0; i < argc; i++) {
        printf("%s\n", argv[i]);
    }
}
```

### 法二（<）重定向

```cpp=
./test < input.txt
```

input.txt 裡面的內容會被當成輸入

```cpp=
#include <stdio.h>

int main() {
    char buffer[100];
    while (scanf("%s", buffer) != EOF) { // 從標準輸入讀取
        printf("Input: %s\n", buffer);
    }
    return 0;
}
```

輸出（假設 input.txt 內容為 hello world）：

```md
Input: hello
Input: world
```

### 編譯兩個檔案

```cpp=
gcc -c lex.yy.c -o lex.yy.o
gcc -c  y.tab.c -o y.tab.o
```

gcc：GNU 編譯器，這裡用來編譯 C 程式碼。
-c：告訴編譯器只執行編譯過程，不進行連結。這樣會生成目標檔案（.o 檔案）。
lex.yy.c：由 Lex（或 Flex）生成的 C 程式碼檔案，負責詞法分析。
-o lex.yy.o：指定輸出的目標檔案名稱為 lex.yy.o。
這條指令的作用是將詞法分析器的 C 程式碼（lex.yy.c）編譯為對應的目標檔案（lex.yy.o）。
這些目標檔案是編譯後的中間產物，下一步會將這些檔案與其他必要的檔案（如標準函式庫）進行連結，生成最終的可執行檔案。

把 lex.yy.o 與 y.tab.o 合成一個檔案

```cpp=
gcc lex.yy.o y.tab.o -o my_program
```

這會將兩個目標檔案連結起來，生成名為 my_program 的可執行檔案。

## I/O 列印讀取

### cin 的加速

```cpp=
ios_base::sync_with_stdio(false);
cin.tie(0);
```

**sync_with_stdio(true); 的作用**
在 C++ 中，sync_with_stdio(true); 是 C++ 預設的行為，它的作用是：

- 保持 C++ 的 I/O (cin/cout) 和 C 的 I/O (scanf/printf) 之間的同步，確保它們的輸出順序是正確的。
- 但會降低 I/O 效率，因為每次執行 cin 或 cout 時，系統會先強制同步 C 標準 I/O (stdio) 的 buffer，這樣能確保混用 cin 和 printf 時不會發生輸出錯誤或順序錯亂。

=>可以安全混用 cin / cout 和 printf / scanf。

如果呼叫 sync_with_stdio(false);，那麼 cin 和 printf 可能會交錯輸出，導致結果不可預測，因為它們不再同步。

**cin.tie(true)**
cin 和 cout 是綁定的
在 C++ 預設情況下，cin 和 cout 是綁定 (cin.tie(&cout);) 的，這表示：
每次執行 cin 讀取輸入時，cout 會先自動 flush (刷新緩衝區)，確保之前的輸出內容已經顯示到螢幕上。

=>確保輸出順序正確，但會影響效能

|                            | `ios::sync_with_stdio(false);`                  | `cin.tie(nullptr);`                          |
| -------------------------- | ----------------------------------------------- | -------------------------------------------- |
| **主要作用**               | `cin/cout` 與 `stdio.h` (`scanf/printf`) 的同步 | `cin` 和 `cout` 之間的關聯                   |
| **預設行為**               | `true`（同步開啟，確保與 C I/O 一致）           | `cout` 綁定 `cin`，`cin` 讀取時會刷新 `cout` |
| **影響 `cout` 輸出時機？** | 否，`cin` 仍然會刷新 `cout`                     | 是，`cin` 讀取時不會再自動 flush `cout`      |
| **影響 `cin` 效能？**      | 是，加速 `cin` 讀取                             | 否，只影響 `cout` 刷新時機                   |

| 設定                           | 是否可以混用 cin / cout 和 printf / scanf | 速度                                            |
| ------------------------------ | ----------------------------------------- | ----------------------------------------------- |
| sync_with_stdio(true);（預設） | ✅ 可以，輸出順序正確                     | 慢                                              |
| sync_with_stdio(false);        | ❌ 不能，可能會輸出錯亂                   | 快                                              |
| cin.tie(&cout); (預設)         | ✅                                        | 會自動 flush 保證輸出順序正確，但 I/O 效能較低  |
| cin.tie(nullptr);              | ❌                                        | 不會自動 flush 提高效能，但可能導致輸出順序錯亂 |

ex:
cin 和 cout 的緩衝區是獨立的，但在預設情況下，cin 會在讀取時自動 flush cout，這讓它們的行為看起來像是共用緩衝區。

```cpp=
#include <iostream>
using namespace std;

int main() {
    cin.tie(0); // 解除 cin 和 cout 的綁定
    string s;
    cin >> s;
    cout.flush(); // 強制輸出緩衝區內容
    return 0;
}
// 輸入：ab cd
// 輸出：（無）
```

### cin.get getchar getline cin.geline 整理

| 函式名稱                    | 讀取類型                 | 是否包含空格 | 是否包含 `\n` | 適用場景                                      |
| --------------------------- | ------------------------ | ------------ | ------------- | --------------------------------------------- |
| `getchar()`                 | 單一字元 (`int`)         | ✅           | ✅            | C 語言標準函式，適合單字元讀取                |
| `cin.get()`                 | 單一字元 (`char`)        | ✅           | ✅            | C++ 版本，與 `getchar()` 類似，但能搭配 `cin` |
| `getline(cin, str)`         | 整行輸入 (`std::string`) | ✅           | ❌            | 適合讀取整行 `std::string`                    |
| `cin.getline(buffer, size)` | 整行輸入 (`char[]`)      | ✅           | ❌            | 適合讀取整行 `char[]`                         |

### cout and print 用法

print 不能印 c++的東西例 vector、pair
cin cout printf scanf 混用可能會有印出順序不同的問題

```cpp=
cout<<setprecision(3)<<11.111//11.1 從最大位開始看往後數幾位
cout<<setprecision(3)<<111.11//111
cout<<setprecision(3)<<1111.11//1.11e+03 整數超過3位就會變成科學符號
cout<<fixed<<setprecision(3)<<11.11111//11.11 fixed只看小數點後面幾位fixed與setprecision的順序沒差
cout<<fixed<<setprecision(3)<<11.1//11.100
cout<<setw(7)<<setfill('p');//setw決定寬度位數 setfill決定填入什麼，預設是空白，每次輸出都要打一次
//注意會被endl洗掉
cout << left << setw(7) << setfill('0') << "abc" << endl;//補在右邊abc0000
//注意會被endl洗掉
cout.unsetf( ios::fixed );//關掉fixed

cout << defaultfloat << 11.11111 << endl;// 關閉 setprecision 的影響，恢復到預設的輸出格式，輸出 11.11111
cout<<defaulfloat;//關掉fixed
printf("%5d",10);//空空空10 少了補0，多了不動
printf("%-5d",10);//10空空空 補在右邊
printf("%05d",10);//00010
printf("%6.3f",1.1111);//空1.111 小數點後面是最多幾位，整數是總共幾位
printf("%6.3s","11111");//空空空111 截斷(長度3)後補零
printf("%s",string.c_str());//用printf印c++的string 要加c_str()
```

### 整數與字元分開讀

```cpp=
int n;
string s;
cin>>n; //當輸入是123asd時 只會讀入123(整數) 輸出123
cin>>s; //讀入asd 輸出asd
```

### getline cin 用法

getline 被包在<string\>
cin.getline 則不用因為是讀陣列
cin 後面要接 cin.ignore()或 cin.get()。因為 cin 讀取完迴車後會多保留一個迴車在緩衝區。所以我們就要用 ignore 函數來把緩衝區多餘的東西刪掉，好讓 getline 可以讀取新的東西。但 getline 讀完後不會留動西在緩衝區，所以 cin.get()可以讀到下一行。

```cpp=
#include <iostream>
#include <string>
int main() {
    string n;
    string str;
    char s[20];
    cin>>n;//讀完會在緩衝區留下迴車或是空白，會讓getline()讀到所以用cin.ignore()可以讀掉
    getline(cin,str);//讀取直到迴車。如果第一個入就是迴車，就會讀迴車且不放東西在字串裡(迴車不放入)，最後一個字元是'\0'。
    getline(cin,str,'a');//會讀空白、迴車直到a，且a會被讀掉不會放進字串，所以前面有迴車也會被輸出。
    cin.getline(s,5);//只能用字元陣列。其實只有讀4個，會讀空白，因為迴車算一個。如果遇到迴車會不管是否已經讀5個字元。
    for(char *i=s;*i!='\0';i++){
        cout<<*i;
    }//迴圈印法(可以知道長度)或是用cout<<s;
    cin.getline(s,5,'a');//同上，迴車換成a。
    getline(cin,str,'a');
    cin.get();//可以讀掉不輸入,單純讀一個字
    cout.flush();//ensure output is written
    cin.peek();  //peek character
    return 0;
}
```

#### 讀一整行直到遇到 EOF

```cpp=
while(getline(cin,s)){
    if(s.empty())break;//遇到換行就會是空值 然後就結束
    arr.push_back(s);
    maxN=max(maxN,(int)s.size());
}
```

#### 比較

```cpp=
string b;
cin>>b;
cout<<(char)cin.peek();//輸入完第一行，peek讀到第一行最後的換行
```

```cpp=
string b;
getline(cin,b);
cout<<(char)cin.peek();//輸入完第一行，peek讀到第二行
```

#### 換行\\n

| 輸入方式            | 實際存入文件的內容           | 被程式讀取時的表現                     |
| ------------------- | ---------------------------- | -------------------------------------- |
| 手動按 Enter        | 真正的換行符（ASCII 10）     | 會導致 getline() 或 cin.getline() 換行 |
| 鍵入 \n（兩個字元） | 字面上的 \n（反斜槓 \ 和 n） | 被讀取為普通的 \n 字串                 |

```cpp=
ifstream file("test.txt");
string line;

while (getline(file, line)) {
    cout << "讀取: " << line << endl;
}
// 文件：Hello\nWorld
// 輸出：Hello\nWorld
```

但如果你在程式中寫 "\\n"，它會被解釋為 單一字元 \\n（ASCII 10，換行符）。
在 string 或是 char\[\]中都一樣

```cpp=
string str = "Hello\nWorld";
cout << "字串長度: " << str.length() << endl;  // 計算長度

for (char c : str) {
    cout << "[" << c << "] ASCII: " << int(c) << endl;
}
// 輸出：
// 字串長度: 11
// [H] ASCII: 72
// [e] ASCII: 101
// [l] ASCII: 108
// [l] ASCII: 108
// [o] ASCII: 111
// [\n] ASCII: 10  // 這裡是單一的換行符
// [W] ASCII: 87
// [o] ASCII: 111
// [r] ASCII: 114
// [l] ASCII: 108
// [d] ASCII: 100

string str1 = "Hello\nWorld";  // \n 代表換行符
string str2 = "Hello\\nWorld"; // 手動輸入 \n（兩個字元）

cout << "str1 長度: " << str1.length() << endl;
cout << "str2 長度: " << str2.length() << endl;
// 輸出：
// str1 長度: 11
// str2 長度: 12
```

### EOF

https://blog.csdn.net/henulwj/article/details/39338727
EOF 其實就是-1
像這樣

```cpp=
#define EOF (-1)
```

不用 define 就有這樣的效果

```cpp=
printf("%d", EOF);//-1
```

**讀到 EOF 就結束**

```cpp=
while(cin>>n){
    //code
}
while(getline(cin,s)){
    //code
}
while(fgets(arr, sizeof(arr), stdin)!=NULL)
```

### 讀入檔案

在 input 中複製上題目的範例就可以直接讀

```cpp=
#include<fstream>
ifstream cin; cin.open("input.txt");
```

### fgets 字串讀取

**scanf 方法**

```cpp=
scanf("%[^\n]", str);//直到換行才讀進去
```

**fgets**
會放入\n 跟\0

```cpp=
char arr[100];
fgets(arr, sizeof(arr), stdin);//abc 放入a b c \n \0
int i = 0;
while (arr[i] != '\0') i++;
cout << i<< endl;//4
cout << strlen(arr);//4 換行也會被掃進去
```

**超出問題**

```cpp=
char arr[3];
fgets(arr, sizeof(arr), stdin);//輸入1234
printf("%s ", arr);//空間只有3 所以裡面是1 2 \0 但\0不會被印出來
```

**不要用 gets**
std::gets was deprecated in C\++11 and removed from C\++14
c\++11 有，但是 c++14 又被刪掉了

```cpp=
char arr[100];
gets(arr);
```

與 string cin

```cpp=
string s ;
cin>>s;//abc 放入a b c \0
i = 0;
while (s[i] != '\0') i++;
cout << i;//3
```

**puts**
印出字串後順便印換行

```cpp=
puts("abc");
puts("abc");
//abc
//abc
```

**getchar putchar**
getchar 一次只讀一個 char。
putchar 一次輸一個 char，不會順便換行。

```cpp=
char c = getchar();//a
putchar(c);
putchar(c);
//aa
```

**sprintf**
輸出到陣列，並返回字串的長度

```cpp=
char buffer [50];
int n, a=5, b=3;
n=sprintf (buffer, "%d plus %d is %d", a, b, a+b);
printf ("[%s] is a string %d chars long\n",buffer,n);
//[5 plus 3 is 8] is a string 13 chars long
```

**sscanf**
把陣列的東西讀入變數

```cpp=
char sentence[] = "Rudolph is 12 years old";
char str[20];
string s="Rudolph is 12 years old";
int i;
sscanf(sentence, "%s %*s %d", str, &i);//%*s 單純讀取不賦值，與單獨cin.get()可以讀掉相同。
sscanf(s.c_str(), "%s %*s %d", str, &i);//c++的string讀取時要用c_str()
printf("%s -> %d\n", str, i);
```

### fstream

可用 file.is_open() 來確認是否成功開啟檔案：

```cpp=
std::ifstream file("test.txt");
if (file.is_open()) {
    std::cout << "檔案開啟成功！" << std::endl;
}
```

| 模式             | 說明                                            |
| ---------------- | ----------------------------------------------- |
| std::ios::in     | 讀取模式（ifstream 預設模式）。                 |
| std::ios::out    | 寫入模式（ofstream 預設模式，會清空檔案內容）。 |
| std::ios::app    | 追加模式（寫入時不清空原內容）。                |
| std::ios::trunc  | 清空檔案後寫入（ofstream 預設行為）。           |
| std::ios::binary | 以二進位模式開啟檔案。                          |

```cpp=
#include <iostream>
#include <fstream>
#include <string>

int main() {
    std::fstream file("data.txt", std::ios::in | std::ios::out | std::ios::app);
    if (!file) {
        std::cerr << "無法開啟檔案！" << std::endl;
        return 1;
    }

    file << "新的一行數據" << std::endl; // 寫入新內容

    file.seekg(0, std::ios::beg); // 移動讀取指標到檔案開頭

    std::string line;
    while (std::getline(file, line)) {
        std::cout << line << std::endl; // 讀取檔案內容
    }

    file.close(); // 關閉檔案
    return 0;
}
```

#### 讀取 csv 檔

```cpp=
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
        if (line.empty()) continue;
        if (!line.empty() && line.back() == '\r') {
            line.pop_back();
        }  // 這個if非常重要 因為這個\r 害我debug超級久
        /*或是這個也可以
        line.erase(0, line.find_first_not_of(" \t\r\n"));  // 去掉前導空白
        line.erase(line.find_last_not_of(" \t\r\n") + 1);  // 去掉尾部空白
        */
        stringstream s(line);
        string tmp;
        vector<int> arr;
        while (getline(s, tmp, ',')) {
            arr.push_back(stoi(tmp));
        }
        for(auto i:arr)cout<<i;
    }
}
```

#### 檔案指標控制 (seekg & seekp)

當讀寫檔案時，可能需要移動檔案指標來調整讀取或寫入位置：

| 方法                     | 用途                                 |
| ------------------------ | ------------------------------------ |
| seekg(offset, direction) | 設定讀取位置（ifstream / fstream）。 |
| seekp(offset, direction) | 設定寫入位置（ofstream / fstream）。 |
| tellg()                  | 取得當前讀取位置。                   |
| tellp()                  | 取得當前寫入位置。                   |

```cpp=
file.seekg(0, std::ios::end); // 移動到檔案結尾
std::streampos size = file.tellg(); // 取得檔案大小
file.seekg(0, std::ios::beg); // 回到開頭
```

#### 二進位檔案讀寫 (binary)

若要處理二進位檔案（如圖片、音訊、序列化資料），需使用 std::ios::binary。

```cpp=
#include <iostream>
#include <fstream>

int main() {
    std::ifstream file("binary.dat", std::ios::binary);
    if (!file) {
        std::cerr << "無法開啟檔案！" << std::endl;
        return 1;
    }

    int data;
    file.read(reinterpret_cast<char*>(&data), sizeof(data));

    std::cout << "讀取的數據：" << data << std::endl;

    file.close();
    return 0;
}
```

#### 檔案錯誤處理 (eof, fail, bad)

可使用 eof()、fail()、bad() 檢查檔案狀態：

- file.eof()：是否到達檔案結尾。
- file.fail()：是否發生讀取或寫入錯誤（如開啟不存在的檔案）。
- file.bad()：是否發生更嚴重的錯誤（如硬碟損壞）。

```cpp=
if (file.fail()) {
    std::cerr << "讀取錯誤！" << std::endl;
}
```

## 資料流 stream 與緩衝區

**重在理解流的一个概念**
**输入流（Input Stream）** 是用于从外部源（例如文件、键盘输入等）读取数据到程序中的流。
**输出流（Output Stream）** 是用于将程序中的数据发送到外部源（例如文件、屏幕输出等）的流。

**箭頭的方向是寫入寫出檔案的方向**

会引发缓冲区的刷新:

1. 缓冲区满时；
2. 执行 flush 语句；
3. 执行 endl 语句；
4. 关闭文件。

### 輸入緩衝

像是 cin cin.get peek getline cin.getline
在緩衝區沒東西的時候都會等待
但只要緩衝區有東西那他們就會一直讀緩衝區直到沒有緩衝區清空
當按下回車時那一行包括換行也會進入緩衝區

```cpp=
abcd efghi ↵
// 在緩衝區長這樣
// abcd efghi \n
```

|                      |                                    |                        |
| -------------------- | ---------------------------------- | ---------------------- |
| cin                  | 會清空第一個字元前所有的空白與換行 | 保留字元後的空白或換行 |
| cin.getline、getline | 不管什麼字元都會讀                 | 但會丟棄\\n            |
| cin.get、peek        | 不管什麼字元都會讀                 |                        |

```cpp=
char c, d;
string s;
cin >> s;
c = cin.get();
d = cin.peek();
cout <<'['<< c <<']'<<'[' << d <<']'<< endl;
// 輸入：
// a↵
// b
// 輸出：
// [
// ][b]
```

```cpp=
char c=cin.get();
cout<<'['<<c<<']'<<endl;

c=cin.get();
cout<<'['<<c<<']'<<endl;
// 輸入：
// a↵
// 輸出：
// [a]
// [
// ]
```

````cpp=
string s;
cin>>;
// 輸入：
// [空白]↵
//結果會等待 因為[空白]↵進入緩衝區之後被cin清掉

### stringstream

```cpp=
//基本用法-----------------------
ss << "Hello, " << "world! " << 2024;  // 像 cout 一樣寫入字串
string result = ss.str();  // 取得字串內容
cout << "stringstream 內容: " << result << :endl;
// 輸出：
// stringstream 內容: Hello, world! 2024

//解析數字-----------------------
string input = "123 456 789";
stringstream ss(input);

int num1, num2, num3;
ss >> num1 >> num2 >> num3;  // 從 ss 讀取數字，類似於 std::cin

cout << "解析出的數字: " << num1 << ", " << num2 << ", " << num3 << endl;
// 輸出：
// 解析出的數字: 123, 456, 789

//清空 stringstream-----------------------
stringstream ss;

ss << "12345";
cout << "第一次內容: " << ss.str() << endl;

ss.str("");  // 清空字串
ss.clear();  // 清空錯誤標誌（如 EOF）

ss << "67890";
cout << "第二次內容: " << ss.str() << endl;
// 輸出：
// 第一次內容: 12345
// 第二次內容: 67890

//分割字串-----------------------
string input = "apple banana cherry";
stringstream ss(input);

string word;
while (ss >> word) {
    cout << "讀取: " << word << endl;
}
// 輸出：
// 讀取: apple
// 讀取: banana
// 讀取: cherry

//getline分割-----------------------
string input = "apple,banana,cherry";
stringstream ss(input);

string word;
while (getline(ss, word, ',')) {  // 以逗號為分隔符
    cout << "讀取: " << word << endl;
}
// 輸出：
// 讀取: apple
// 讀取: banana
// 讀取: cherry

//數字轉字串-----------------------
int num = 42;
stringstream ss;
ss << num;  // 數字寫入 ss
string str = ss.str();  // 轉成字串

cout << "字串: " << str << endl;
// 輸出：
// 字串: 42

//字串轉數字-----------------------
string str = "1234";
stringstream ss(str);

int num;
ss >> num;  // 字串轉成數字

cout << "數字: " << num << endl;
// 輸出：
// 數字: 1234
````

**stringstream 可以做到 istringstream 與 ostringstream 的事**
只是為了可讀性
![IMG_0522](https://hackmd.io/_uploads/HyiAzV4iyl.jpg)

1.istringstream（只讀）
適用於僅解析字串，不需要寫入的情境（例如從 std::cin 或檔案中讀取內容後解析）。
只能用 >> 讀取，不能 << 寫入。

```cpp=
string input = "123 456 789";
istringstream iss(input);  // 只讀入

int num1, num2, num3;
iss >> num1 >> num2 >> num3;  // 解析數字

cout << "讀取數字: " << num1 << ", " << num2 << ", " << num3 << endl;
```

2.ostringstream（只寫）
適用於只需要寫入但不讀取的場景（例如格式化輸出）。
只能用 << 寫入，不能 >> 讀取。

```cpp=
ostringstream oss;  // 只寫入
oss << "C++ " << 20 << "23";  // 格式化字串

string output = oss.str();  // 取得字串
cout << "輸出結果: " << output << endl;
```

### 緩衝區

scanf 與 printf 也有緩衝區 但與 c++的緩衝區機制不同 所以不能混用
還有緩衝區是有大小的 4~8kb 都有 超出就會輸出
**1. 標準輸入輸出（cin, cout, cerr）**
**cout（標準輸出）**
預設有緩衝區
會先將輸出存入緩衝區，直到：

- 遇到 std::endl（會刷新緩衝區 flush()）
- 緩衝區滿了
- 程式結束時，自動刷新緩衝區

```cpp=
cout << "Hello, ";   // 不會立刻輸出
cout << "world!";    // 仍然不會立刻輸出
cout << endl;   // 遇到 std::endl，強制刷新緩衝區，輸出到螢幕
```

**cin（標準輸入）**
預設有緩衝區
使用者輸入時，實際上是先輸入到緩衝區，然後 cin 再從緩衝區讀取數據。
cin 會在 按 Enter 之後才開始讀取輸入內容。

```cpp=
int num;
cout << "輸入一個數字: ";
cin >> num;  // 直到使用者按 Enter，才會將數據從緩衝區讀取
cout << "你輸入的數字是: " << num << endl;
```

**cerr（標準錯誤輸出）**
而且會把緩衝區的先輸出
沒有緩衝區（即 cerr 是 不緩衝 的）
輸出錯誤訊息時，不會被緩衝，會立刻輸出
適合即時錯誤訊息，例如程式錯誤訊息、日誌

```cpp=
cerr << "這是錯誤訊息" << endl;
//cerr 立即輸出，不受 std::cout 的緩衝影響！
```

**clog（日誌輸出）**
有緩衝區
用來輸出日誌，類似 cerr，但因為有緩衝，所以效能較好

```cpp=
clog << "這是日誌訊息" << endl;  // 會進入緩衝區，適時輸出
```

**2. 檔案 I/O (fstream)**
**ifstream（讀取檔案）**
有緩衝區
讀取時，會一次讀取多個字元到緩衝區，提高效率。

```cpp=
ifstream file("data.txt");  // 開啟檔案
string content;
while (file >> content) {  // 逐字讀取
    cout << content << endl;
}
file.close();
```

**ofstream（寫入檔案）**
有緩衝區
ofstream 會先將資料寫入緩衝區，等到緩衝區滿了或 flush() 才會真正寫入檔案。

```cpp=
ofstream file("output.txt");
file << "Hello, file!" << endl;  // 可能還在緩衝區，尚未寫入檔案
file.flush();  // 立即寫入檔案
file.close();
```

**3. 控制緩衝區**
**cout.flush() 立即輸出**
endl 自動刷新緩衝區
cout.flush() 手動刷新緩衝區

```cpp=
cout << "開始... ";
cout.flush();  // 立即輸出，不等到換行
this_thread::sleep_for(chrono::seconds(2));  // 等 2 秒
cout << "結束！" << endl;
//輸出：
// 開始... (2 秒後) 結束！
```

**ios::sync_with_stdio(false);**
關閉 C++ I/O 和 C I/O (printf/scanf) 之間的同步
提高 cin/cout 效率（約 5~10 倍）

```cpp=
ios::sync_with_stdio(false);  // 關閉同步，提高效能
cin.tie(nullptr);  // 解除 cin 和 cout 的關聯，提高效能
```

**4. getchar、getline、cin.get() 是否有緩衝區？**
**getchar()**
有緩衝區，按下 Enter 才會開始讀取（類似 scanf）。
可以一次輸入多個字元，然後 getchar() 會 逐個從緩衝區讀取。

```cpp=
char c;
printf("請輸入一個字元: ");
c = getchar();  // 會等到按 Enter 才從緩衝區讀取
printf("你輸入的是: %c\n", c);
```

**getline(cin, s)**
有緩衝區，讀取一整行直到 \n，但不保留 \n。
不會遺留換行符，適合讀取完整字串。

```cpp=
string input;
cout << "請輸入一行文字: ";
getline(cin, input);
cout << "你輸入的是: " << input << endl;
```

**cin.get()**
有緩衝區，但可以用來讀取單個字元或整行。
cin.get() 保留換行符 \n，而 getline() 會丟棄它。

```cpp=
char c;
cout << "請輸入一個字元: ";
c = cin.get();  // 讀取一個字元（包括空白或換行）
cout << "你輸入的是: " << c << endl;
```

| 操作                   | 讀取方式                     | 是否讀取 \\n              | 緩衝區影響                           |
| ---------------------- | ---------------------------- | ------------------------- | ------------------------------------ |
| 按 Enter 鍵            | 會產生 \n，並進入 輸入緩衝區 |                           |
| cin                    | 讀取變數（忽略空白）         | ❌ 不讀 \\n（留在緩衝區） | getline() 可能讀取到 \\n（錯誤行為） |
| getline(cin, s)        | 讀取整行                     | ✅ 讀 \\n 並丟棄          | 受 cin >> 遺留的 \\n 影響            |
| cin.getline(buf, size) | 讀取整行                     | ✅ 讀 \n 並丟棄           | 受 cin >> 遺留的 \\n 影響            |
| cin.get\(c\)           | 讀取單個字元                 | ✅ 讀 \\n                 | 可能讀取 \\n，影響後續輸入           |
| cin.ignore()           | 忽略單個字元                 | ✅ 讀 \\n（可忽略掉）     | 用來清理 cin 遺留的 \\n              |

**總結**
類別/函式 |緩衝區 |立即輸出/讀取
|-|-|-|
cout |✅| ❌（預設緩衝，遇 std::endl 或 flush() 才輸出）
cin |✅| ❌（按 Enter 後才讀取）
cerr |❌| ✅（會把緩衝區的先輸出，無緩衝區，立即輸出）
clog|✅ |❌（適合日誌，使用緩衝區）
ifstream|✅ |❌（預設使用緩衝區讀取）
ofstream |✅|❌（預設使用緩衝區寫入）
getchar() |✅ |❌ 逐字讀取，從緩衝區讀取一個字元
getline(cin, s) |✅|❌ 讀取整行，直到 \n 為止
cin.get() |✅ |❌ 讀取單個字元或整行（視參數而定）

### 各自的緩衝區與大小

下面程式用來測試
以我的筆電來說是 4096byte
超過 4096 再輸出一個進去的話，就會先把前面 4096 個字輸出

```cpp=
for(int i = 0; i < 4096; i++)
{
    cout<<"x";
}
sleep(3);
cout<<"d";
sleep(3);
cout<<"z";
```

每個都有各自的緩衝區
![image](https://hackmd.io/_uploads/ryTMMAHjkx.png)

- 全缓冲：在这种情况下，当填满标准 I/O 缓存后才进行实际 I/O 操作。全缓冲的典型代表是对磁盘文件的读写。
- 行缓冲：在这种情况下，当在输入和输出中遇到换行符时，执行真正的 I/O 操作。这时，我们输入的字符先存放在缓冲区，等按下回车键换行时才进行实际的 I/O 操作。典型代表是键盘输入数据。
- 不带缓冲：也就是不进行缓冲，标准出错情况 stderr 是典型代表，这使得出错信息可以直接尽快地显示出来。

![image](https://hackmd.io/_uploads/ryx5z0Sjyl.png)
但這些緩衝區還是可以自定義更改大小的
並且就算是 cin 與 cout 的緩衝區也不是共用的
而是 cin.tie()有強制讓 cin 的時候要先把 cout 的緩衝區先輸出，才會看起來是共用的

### 緩衝區的互相影響

不是所有的 I/O 流都會刷新其他的緩衝區，但某些情況下，特定的 I/O 操作會影響其他流的緩衝區。這主要取決於 C++ 標準庫的同步機制 (std::ios::sync_with_stdio(true);) 以及 I/O 流的緩衝行為。
![image](https://hackmd.io/_uploads/BylBmRHskl.png)

## STL

### 優化

```cpp=
#pragma GCC optimize(3)
```

**作用：** 讓 stl 容器變快，但編譯時間變久。但拿編譯時間換執行時間很划算。

### vector

end()是最後一個位置加一=v[最後一位]+1 不等於 v[最後一位]
begin()是最初的位置=V[0]，但它們都是 iterator(跟指標有點像但不完全相同)。vector 的記憶體是連續的，就算用 insert 也還是會連續，因此 insert 很耗時。

```cpp=

vec.push_back();
vec.pop_back();
cout<<&(*a.begin())<<endl;//0xac9eb0
cout<<&a[0]<<endl;//0xac9eb0
cout<<&(*a.end())<<endl;//0xac9eb8
cout<<&a[1]<<endl;//0xac9eb4
cout<<&a[1]+1<<endl;//0xac9eb8

沒有vector<int> a[10]//不能用陣列的宣告方式
vector<int> vec(2);//代表vec[0]~vec[1]都是0
vector<int> vec(5, 10); // 初始化一個包含 5 個元素的 vector，每個元素值為 10
vector<int> vec={1,2,3};
vec.assign({1, 2, 3, 4});
//如果裡面原本有東西像是{5,23,5,1,7,9,3,6,} 會直接覆蓋掉
//size的值會被重新分配 但capacity沒有變

//清空的三種方法
vec.assign({});
vec.erase(vec.begin(),vec.end());
vec.clear();

//複製三種方法
vector<int> vec1 = {1, 2, 3, 4, 5};
vector<int> vec2 = vec1;  // 直接賦值
vector<int> vec2(vec1);  // 使用拷貝建構子
vec2.assign(vec1.begin(), vec1.end());// 使用 assign 來複製

vec.push_back(1);//0 0 1

//二微陣列宣告
//法一
vector<vector<int>> v;
for(int i=0;i<size;i++){
    vector<int> a;
    a.push_back(i);
    v.push_back(a);
}
//法二
vector<vector<int>> v(size);
for(int i=0;i<size;i++){
    v[i].resize(size);
}//裡面都會是零
//法三
vector<vector<int>> vec(size,vector<int>(size))//跟法二一樣都是0
//法四
vector<vector<int>> vec(size);
/*
vector<vector>vec;
vec.resize(size); 另一種不用在宣告時決定長度的方式
*/
for(int i=9;i>=0;i--){
    for(int j=0;j<=i;j++){
        vec[i].push_back(j);
    }
}//可以從後面塞東西長度可改動

//可以這樣印
for(auto i:array){
    for(auto j:i)cout<<j<<" ";
    cout<<endl;
}
```

存放位置問題：

```cpp=
ector<vector<int>> vec(2, vector<int>(2));
cout << &vec[0][1] - &vec[0][0] << endl;//1
/*
&vec[0][1] - &vec[0][0] 時，這個表達式的含義是計算這兩個指標所指向的元素之間相隔的「int」數量。
由於 vec[0][1] 與 vec[0][0] 相差一個 int，而 int 的大小是 4 個位元組，因此結果為 1，這代表它們之間相隔一個 int。
*/
cout << &vec[1][0] - &vec[0][0] << endl;//不可預期
//不是最底下那維的話 記憶體不會像是陣列一樣連續
```

如果沒有先宣告大小就用的話會出事 除非用 push

```cpp=
vector<int> arr;
這樣是不法的arr[0]=1;
arr.push_back(1);
```

### stack

first in - last out
不能用 begin()、[]、for(auto i:d)

```cpp=
stack<int> s;
s.push();//從尾端放入
s.pop();//移除尾端
s.top();//尾端的值
s.empty();//空的話傳1
for(auto i:s)//不能，也不能用s[i]
//沒有begin(),end()
while(!s.empty()){
    cout<<s.top();
    s.pop();
}//遍歷的方式
```

### queue

不能用 begin()、[]、for(auto i:d)

#### queue

```cpp=
queue<int> q;
q.push();//從尾端放入
s.pop();//移除頭
s.front();//頭的值
s.back();//尾端的值
s.empty();//空的話傳1
while(!s.empty()){
    cout<<s.front();
    s.pop();
}//遍歷的方式
```

#### priority_queue

```cpp=
prioirty_queue<int> q;//預設最大堆升續1 2 3 4
priority_queue<int,vector<int>,greater<int>> q//最小堆
priority_queue<int,vector<int>,less<int>> q//最大堆
priority_queue<int,vector<int>,cmp> q
//因為優先判定為!cmp，所以「由大排到小」需「反向」定義實現「最小值優先」。反之亦然。
s.push();
s.pop();//移除尾端
s.top();//尾端的值
s.empty();//空的話傳1
```

### set

會自動排序的 stl(升續)
時間複雜度都是 O(log(n))

1. set 中 s.insert()返還 pair<iterator,bool>如果 set 中沒有重複的數字,s.second(bool)=ture。不管返回 true 或是 false，s.first 會是插入的值。
2. multiset 是可以重複放的 set
3. 但 multiset 中 s.insert()返還 iterator

```cpp=
set<int> s{4,1,2}//會自動排列成1 2 4
s.insert(val);//不能重複插入同個值
s.erase(val);//新增與刪除只能用這兩個
s.find(val);//找到返回值的位置(iterator)，否則返回end()
s.count(val);//找到返回1否則返回0
s.clear();//清空
s.empty();//是否空
auto it=s.insert(val);
if(it.second){
    cout<<*it.first;
}
//印出方式 不能用s[i]
1. for(auto &i:s)cout<<i;
2. for(set<int>iterator i=s.begin(),i!=s.end();i++)cout<<*i;
```

### unordered_set

```cpp=
#include <iostream>
#include <unordered_set>

using namespace std;

int main() {
    unordered_set<int> uset = {1, 2, 3, 3};  // 重複元素不會儲存

    uset.insert(4);  // 插入元素
    uset.erase(2);   // 刪除元素

    if (uset.find(3) != uset.end()) {  // 查找元素
        cout << "3 存在\n";
    }

    for (int num : uset) {  // 遍歷元素（順序不固定）
        cout << num << " ";
    }
}
```

### map

插入：記 m["str"]=100 這個方法就好了
查找：find 返回 iterator。count 返回 1 或 0，但 multimap 的 count 會返回相同元素的數量
使用：像是陣列一樣使用就好了 m["str"]

```cpp=
map<string,int>m;
m["str"]=100;
m.insert({"str",100});
m.insert(make_pair("str",100));
m.insert(pair<string,int>("str",100));
//如果對應的值一樣就不會成功，像是m["str"]=1，由於前面已經m["str"]=100所以不會插成功。

m.count("str");//看看有沒有重複，有回傳1，沒有回傳0
m.erase("str");//刪除key與對印的值
cout<<m["s"];
//雖然前面沒有宣告過，key也沒有值，但會預設0，同時也宣告了，相當於m["s"]=0

auto it=m.find("str");//沒有的話返還值為end()
cout<<it->first;//str
cout<<it->second;//100

auto it=m.insert({"str",100});
cout<<it.first->first;//str
cout<<it.first->second;//100
cout<<it.second;//true

auto it=m.insert(m.begin(),{"str",100});//給位置代表從哪裡開始蒐。為了可以比較快找到位置，沒找到還是會按照順序排。
cout<<it->first//str
cout<<it->second;//100
cout<<it.second;//沒有it.second，這行會發生錯誤。

for(auto i:m){
    cout<<i.first<<" "<<i.second<<enld;//first是key second是value
}
```

### unorder_map

平均複雜度 O(1)
最糟複雜度 O(n)

```cpp=
unordered_map<string, int> m;

// 插入元素的幾種方法
m["str"] = 100;
m.insert({"str", 100});
m.insert(make_pair("str", 100));
m.insert(pair<string, int>("str", 100));

// 插入值相同的情況
// 如果鍵已經存在，並且對應的值相同，插入將不會成功
// 如：m["str"] = 1，由於前面已經有 m["str"] = 100，因此不會覆蓋
// 但若用 m["str"] = 1，會直接覆蓋原本的值變成 1

// 使用 count() 函式檢查鍵是否存在
cout << m.count("str"); // 存在返回 1，不存在返回 0

// 刪除元素
m.erase("str"); // 刪除鍵 "str" 及其對應的值

// 如果鍵不存在，會預設值為 0 並插入到 `unordered_map`
cout << m["s"]; // 如果 "s" 不存在，會返回 0，並且相當於 m["s"] = 0

// 使用 find() 查找元素
auto it = m.find("str"); // 找不到時，返回 m.end()
if (it != m.end()) {
    cout << it->first; // str
    cout << it->second; // 100
}

// 使用 insert() 插入元素，返回 pair<iterator, bool>
auto result = m.insert({"str", 100}); // 已存在時不會插入
cout << result.first->first; // str
cout << result.first->second; // 100
cout << result.second; // true，如果插入成功返回 true，否則返回 false

// 使用 insert() 並提供提示位置
auto it2 = m.insert(m.begin(), {"str", 100}); // 提供起始位置作為提示
cout << it2->first; // str
cout << it2->second; // 100
// unordered_map 中沒有 second，it2 直接是 iterator，所以無法使用 it2.second
```

#### multimap

一個標籤可以對應到不同的值，所以不會有新增失敗的問題。
新增時只能用 insert，不能用中括號例如 m["str"]=100。
要印出一個標籤所有的值方法特別。

```cpp=
multimap<int,char,greater<int>> mm;//由小到大

m.insert({"a",100});
m.insert({"a",200});

auto it=m.c({"a",300});
cout<<it->first;//a
cout<<it->second;//100

cout<<m.count("a");//3 a的次數

auto it=m.find("a");
cout<<it->first;//a
cout<<it->second;//100

typedef multimap<string,int>::iterator mmit;//type跟#define一樣
pair<mmit,mmit>result=m.equal_range("a");
for(mmit i=result.first;i!=result.second;i++){
    cout<<i->second<<endl;
}
//100 200 300
```

### deque

可以用[]

```cpp=
deque<int> d;
d.push_back(2);//後面放入
d.push_front(3);//前面放入
d.pop_back();//後面拿掉
d.pop_front();//前面拿掉
```

### list

可以用 begin()、auto，但不能用[]

```cpp=
list<int> l;
l.push_back(2);//後面放入
l.push_front(3);//前面放入
l.pop_back();//後面拿掉
l.pop_front();//前面拿掉
auto it1=l.begin();
auto it2=l.end();
it1++;//只能用it++不能用it=it+1
l.erase(it1);//刪除it的位置
l.erase(it1,it2);//刪除[it1,it2)的範圍
l.insert(it1,10);
```

## iterator

### distance

算兩個 iterator 的距離(不能用減的)，順序小的要放前面，大的放後面。

```cpp=
vector<int>vec={1,2,3,4,5};
cout<<distance(vec.begin(),vec.end());// 5
```

### advance

對一個 iterator 移動(不能用加的)。

```cpp=
vector<int>vec={1,2,3,4,5};
auto it=vec.begin();
advance(it,2);
cout<<(*it);//3
```

## string

| **分類**         | **函數**                                                   | **功能**                      | **範例**                         |
| ---------------- | ---------------------------------------------------------- | ----------------------------- | -------------------------------- |
| **基本操作**     | `size()` / `length()`                                      | 取得字串長度                  | `s.size()` → `5`                 |
|                  | `empty()`                                                  | 判斷字串是否為空              | `s.empty()` → `false`            |
|                  | `clear()`                                                  | 清空字串                      | `s.clear()`                      |
|                  | `operator[]`                                               | 存取字元（索引）              | `s[1]` → `'e'`                   |
|                  | `at(pos)`                                                  | 存取字元（帶邊界檢查）        | `s.at(1)`                        |
|                  | `front()`                                                  | 取得字串的第一個字元          | `s.front()`                      |
|                  | `back()`                                                   | 取得字串的最後一個字元        | `s.back()`                       |
| **變更字串內容** | `append(str)`                                              | 追加字串                      | `s.append("World")`              |
|                  | `operator+=`                                               | 追加字串                      | `s += "World"`                   |
|                  | `insert(pos, str)`                                         | 插入字串                      | `s.insert(2, "ABC")`             |
|                  | `replace(pos, len, str)`                                   | 取代部分字串                  | `s.replace(1, 2, "XY")`          |
|                  | `erase(pos, len)`                                          | 刪除部分字串                  | `s.erase(1, 3)`                  |
|                  | `push_back(ch)`                                            | 追加單一字元                  | `s.push_back('X')`               |
|                  | `pop_back()`                                               | 刪除最後一個字元              | `s.pop_back()`                   |
| **字串搜尋**     | `find(str)`                                                | 從左側搜尋子字串              | `s.find("lo")` → `3`             |
|                  | `rfind(str)`                                               | 從右側搜尋子字串              | `s.rfind("lo")`                  |
|                  | `find_first_of(str)`                                       | 找到第一個匹配字元            | `s.find_first_of("aeiou")`       |
|                  | `find_last_of(str)`                                        | 找到最後一個匹配字元          | `s.find_last_of("aeiou")`        |
|                  | `find_first_not_of(str)`                                   | 找到第一個**不**匹配的字元    | `s.find_first_not_of("Hello")`   |
|                  | `find_last_not_of(str)`                                    | 找到最後一個**不**匹配的字元  | `s.find_last_not_of("Hello")`    |
| **子字串處理**   | `substr(pos, len)`                                         | 擷取部分字串                  | `s.substr(1, 3)` → `"ell"`       |
| **字串比較**     | `compare(str)`                                             | 比較字串（回傳 `0` 表示相等） | `s.compare("ABC")`               |
| **大小寫轉換**   | `std::transform(s.begin(), s.end(), s.begin(), ::toupper)` | 轉換為大寫                    | `"hello"` → `"HELLO"`            |
|                  | `std::transform(s.begin(), s.end(), s.begin(), ::tolower)` | 轉換為小寫                    | `"HELLO"` → `"hello"`            |
| **數值轉換**     | `std::to_string(num)`                                      | 整數/浮點數轉字串             | `std::to_string(123)` → `"123"`  |
|                  | `std::stoi(str)`                                           | 字串轉 `int`                  | `std::stoi("123")` → `123`       |
|                  | `std::stod(str)`                                           | 字串轉 `double`               | `std::stod("123.45")` → `123.45` |

```cpp=
getline(cin,string);
string str1;
string str2;
str1.empty(); //檢查str1是否為空,是回傳1,否則回傳0
str1.size();  //回傳str1的長度
str1.find("abc",3);  //從str1[3]開始找第一個"abc",回傳索引號,找不到則回傳-1
str1.rfind("abc",3);  //找最後的"abc",回傳索引號,找不到則回傳-1
str1.insert(3,"def");  //在str[3]插入"def"

str1.assign(str2);  //複製str2到str1 == str1=str2
str1.assign(str2,3,5);  //從str2的第3個字元取出5個字元指定給str1
str1.assign(str2,3); //從str2的第3個字元指定整串字串到str1
str1.assign(10,'c');  //複製10個c到str1
//append用法跟assign大同小異

str1.append(str2,3,5);  //從str2的第3個字元取出5個字元加在str1後面
str1.append("hello");  //直接給字串
ub
str1.push_back('j');//string的push只能用''字元 要push字串要用apeend
str1=str2.substr(3,5);  //從str2的第3個位置取5個字元放到str1
string a="ac",b="b";
max(a,b);//字串比大小由左到右比ascii code的大小誰先比較大就贏了
//不是比長度，但前面都一樣就會輸出長的
```

### 通常不包含結尾字符（\\0）

cin>>string 或是 字串轉成 string 都不會包含\\0

```cpp=
char arr[] = "hello";  // C 字符陣列，最後有隱含的 '\0'
string str = arr;      // 將字符陣列轉換成 std::string

cout << "C 字符陣列: " << arr << endl;
cout << "std::string: " << str << endl;
cout << "std::string 的長度: " << str.length() << endl;  // 字串長度

cout << str[5]; => 會是錯的 因為字串轉過來不會把最後的\0放進去
```

除非強制給他

```cpp=
string str = "abc\0def";  // 這個字串實際上包含 7 個字符

cout << "String length: " << str.length() << endl;  // 輸出字串長度
cout << "String content: " << str << endl;  // 直接輸出字串內容

// 遍歷字串的每個字符，顯示其 ASCII 值
for (size_t i = 0; i < str.length(); ++i) {
    cout << "Character at index " << i << ": " << (int)str[i] << endl;
}

String length: 7
String content: abc
Character at index 0: 97  // 'a'
Character at index 1: 98  // 'b'
Character at index 2: 99  // 'c'
Character at index 3: 0   // '\0'
Character at index 4: 100 // 'd'
Character at index 5: 101 // 'e'
Character at index 6: 102 // 'f'
```

### bitset 二進制

```cpp=
    int a=14;
    bitset<6> c(a);//6 is lenghth
    cout<<c<<endl;//001110
    c<<=1;//乘以2
    cout<<c<<endl;//011100
    cout<<c[2];//1
```

### char 陣列轉成 string

```cpp=
char charArray[] = "Hello, World!";

// 法一 使用 string 的建構函式將 char 陣列轉為 string
string str(charArray);

//法二
string str;
str.assign(charArray);

//法三
//char 陣列不包含終止符 \0
char charArray[] = {'H', 'e', 'l', 'l', 'o', '!', '!', '!'};
// 將 char 陣列指定長度轉為 string
std::string str(charArray, sizeof(charArray));

//但如果char陣列 有結尾字符\0 sizeof()也會算進去
char a[]="b";
string arr(a,sizeof(a));
cout<<arr.size();//2
cout<<arr;//b cout還是一樣會印到\0

//法四
char charArray[] = "Hello, World!";
std::string str;
str.append(charArray);
```

### 數字轉成 string

#### to_string

```cpp=
int num = 123;
string str = to_string(num);
```

#### sprintf

```cpp=
int num = 123;
char buffer[50];
sprintf(buffer, "%d", num);
std::string str(buffer);
```

### stringstream

c++專用 類似於 c 的 ssprintf、sscanf

```cpp=
stringstream ss;
int age = 30;
string name = "Alice";
ss << "Name: " << name << ", Age: " << age;

// 獲取寫入的字符串
string output = ss.str();
cout << output << endl; // 輸出: Name: Alice, Age: 30

// 讀取數據從字符串
string input = "100 200";
stringstream ssInput(input);
int x, y;
ssInput >> x >> y; // x = 100, y = 200

cout << "x: " << x << ", y: " << y << endl; // 輸出: x: 100, y: 200
```

清空內容：如果你想重用一個 stringstream 對象，可以使用 str("") 清空內容

```cpp=
ss.str(""); // 清空
ss.clear(); // 重置標誌
```

錯誤檢查：在讀取數據後，可以檢查流的狀態

```cpp=
if (ss.fail()) {
    std::cout << "讀取失敗" << std::endl;
}
```

## char 與字元與字元陣列與含式

### 字元

**\0:** 空字元 ascii 碼 0 (與空白 space 不是同一個東西)
**\n:** 換行 ascii 碼 10
**\t:** tab ascii 碼 9  
**' ':** space ascii 碼 32

```cpp=
char arr[] = " ,\t\r\n";// \t \r \n都算一個字元 所以長度是5
```

**ascii 碼**

![ASCII-Table-wide.svg](https://hackmd.io/_uploads/H1tw0jo7p.jpg)

**char 在指定前為隨機的 而不是\0(空字符)**

```cpp=
char c;
if(c=='\0')printf("a")
else printf("b")
//b
```

### char 陣列

包含在<string.h>裡面

```cpp=
char a[4]={"asdf"}//會報錯，因為最後一格要留給\0
char a[4]={"asd"}//才對
char s[] = {"asdf"};
printf("%s", s);//印出全部asdf，s是位址也是s[0]的位址
printf("%s", &s[1]);//印出sdf，會取s[1]的位址，然後印出直到遇到\n
```

**strtok**
**strlen**

```cpp=
char s=[100];
printf("%d",strlen(s));//100
```

**strtod**
分裂第一個遇到的浮點數與後面的字串

```cpp=
char str[40] = "20.4test 31.3";
char *ptr;
double ret;
ret = strtod(str, &ptr);
printf("%lf\n",ret);
printf("%s",ptr);
//20.400000
//test 31.3w
char str[40] = "www20.4test 31.3w";//改成這樣的輸出
//0.000000
//www20.4test 31.3w
```

**strtol**
其他進位轉換成十進位

```cpp=
char szNumbers[] = "2001 60c0c0 -1101110100110100100000 0x6fffff";
char* pEnd;
long int li1, li2, li3, li4;
li1 = strtol(szNumbers, &pEnd, 10);
li2 = strtol(pEnd, &pEnd, 16);
li3 = strtol(pEnd, &pEnd, 2);
li4 = strtol(pEnd, NULL, 0);
printf("The decimal equivalents are: %ld, %ld, %ld and %ld.\n", li1, li2, li3, li4);
//The decimal equivalents are: 2001, 6340800, -3624224 and 7340031.
```

**strchr**
str 中找到第一個有出現字元 C 的地址

```cpp=
char str[] = "fcba73";
printf("%c", *(strchr(str, 'c')));//c
```

**strcspn**
str 找到第一個與 s2 重複的字元的位置

```cpp=
char str[] = "fcba73";
char keys[] = "1234567890";
printf("position %d.\n", strcspn(str, keys););
//position 4.
```

**字串處理**

```cpp=
char s1[100]="abcde",s2[100]="fghi";
strcpy(s1,s2);//fghi 覆蓋複製
strncpy(s1,s2,n);//fghde 覆蓋前n個的複製
strcat(s1,s2);//abcdefghi 加到最後面
strncat(s1,s2,n);//abcdefgh 前n個加到最後面
strcmp(s1,s2);//-5 一個一個比較ascii code的大小前減後(a-f=-5)，都一樣就輸出0。
strncmp(s1,s2,n);//取前n個比較

char s1[100] = "a", s2[100] = "ab";
strcmp(s1, s2); //-98，如果前幾個都的ㄧ樣，則超出去沒有的字母會用0去計算
```

**字元處理**

```cpp=
//包含在ctype.h
char c;
isalpha(c)//是否a~z A~Z是的話回傳1
isdigit(c)//0~9
isxdigit(c)//0~9 a~z A~Z
islower(c)//a~z
isupper(c)//A~Z
toupper(c)//變大寫
tolower(c)//變小寫
```

## pair

```cpp=
#define pii pair<int,int>
pii a;
a={1,2};
a=make_pair(1,2);
a=pii(1,2);
vector<pair<int,int>>//是錯的
vector<pair<int,int> >//才是對的，因為>>會被當成是cin>>的>>
//binary_search不能用{}要用make_pair(1,2)或是pii(1,2)
```

## 各種定義

### 定義

其實 int char 都是用有號以及沒號的 byte 去定義 除了 float 不一樣。
uintX-t 就是通过 typedef 定义的，利用预编译和 typedef 可提高效率也方便代码移植。总结如下：

```cpp=
typedef unsigned char   uint8_t;     //无符号8位数
typedef signed   char   int8_t;      //有符号8位数
typedef unsigned int    uint16_t;    //无符号16位数
typedef signed   int    int16_t;     //有符号16位数
typedef unsigned long   uint32_t;    //无符号32位数
typedef signed   long   int32_t;     //有符号32位数
typedef float           float32;     //单精度浮点数
typedef double          float64;     //双精度浮点数
```

一般来说整形对应的\*\_t 类型为：
uint8_t 为 1 字节  
uint16_t 为 2 字节  
uint32_t 为 4 字节  
uint64_t 为 8 字节  
不难看出，通过头文件 X.h 定义了 uint8_t,其实编译器实际上是把它作为"char"来处理的，在对字符型的变量进行操作

#### c99 的定義

```cpp=
//typedef 舊名子 新名子
// Represents true-or-false values
typedef _Bool bool;
//bool其實就是uint1_t(雖然沒有這個表示法)只有一bit的變量
enum { false, true };
//也就是說打false會完全等於0 打true會完全等於1
```

```cpp=
// Explicitly-sized versions of integer types
typedef __signed char int8_t;
typedef unsigned char uint8_t;
typedef short int16_t;
typedef unsigned short uint16_t;
typedef int int32_t;
typedef unsigned int uint32_t;
typedef long long int64_t;
typedef unsigned long long uint64_t;

// Pointers and addresses are 32 bits long.
// We use pointer types to represent virtual addresses,
// uintptr_t to represent the numerical values of virtual addresses,
// and physaddr_t to represent physical addresses.
typedef int32_t intptr_t;
typedef uint32_t uintptr_t;
typedef uint32_t physaddr_t;
```

格式化输出：（都會是數字）

unit64_t %llu  
unit32_t %u
unit16_t %hu

注意：
必须小心 uint8_t 类型变量的输出，例如如下代码，会输出什么呢？

```cpp=
uint8_t fieldID = 67;
cerr<< "field=" << fieldID <<endl;
```

**结果发现是：field=C 而 不是我们所想的 field=67**

这是由于 typedef unsigned char uint8_t;
uint8_t 实际是一个 char, cerr << 会输出 ASCII 码是 67 的字符，而不是 67 这个数字.
因此，输出 uint8_t 类型的变量实际输出的是其对应的字符, 而不是真实数字.
若要输出 67,则可以这样：

```cpp=
cerr<< "field=" << (uint16_t) fieldID <<endl;
```

**结果是：field=67**

### 定義代表的數值

數值是印出來真的就是數字
其中定義的方式可能用 define 或是 enum

#### 1. C/C++ 標準庫中的數值定義

| 特性           | NULL（通常是 `0`）               | `nullptr`        |
| -------------- | -------------------------------- | ---------------- |
| **本質**       | 整數（`int` 或 `0`）             | `std::nullptr_t` |
| **可用於指標** | ✅（但易混淆）                   | ✅               |
| **可用於整數** | ✅（因為是 `0`）                 | ❌               |
| **過載解析**   | 可能造成歧義                     | 清楚選擇指標版本 |
| **建議使用**   | ❌（C++11 以上建議用 `nullptr`） | ✅               |

| 宏名稱         | 數值                                        | 描述                        |
| -------------- | ------------------------------------------- | --------------------------- |
| `NULL`         | `0` 或 `(void*)0`（C），`nullptr`（C++11+） | 空指標                      |
| `EOF`          | `-1`                                        | End of File（文件結束）     |
| `EXIT_SUCCESS` | `0`                                         | `exit(0)`，表示程式正常結束 |
| `EXIT_FAILURE` | `1`                                         | `exit(1)`，表示程式異常結束 |
| `RAND_MAX`     | `32767`（可能依系統不同）                   | `rand()` 產生的最大隨機數   |
| `FLT_MAX`      | `3.402823e+38`                              | `float` 型別的最大值        |
| `DBL_MAX`      | `1.797693e+308`                             | `double` 型別的最大值       |

**備註**

1. `NULL` 在 C++11 之後應該使用 `nullptr` 來取代，避免與整數 `0` 混淆。
2. 許多數學常數在 C++ 標準庫 `<cmath>` 中已經可以用 `constexpr` 替代，如 `std::numbers::pi`（C++20）。
3. `__cplusplus` 的數值代表 C++ 標準版本，例如：
   - `199711L` → C++98
   - `201103L` → C++11
   - `201402L` → C++14
   - `201703L` → C++17
   - `202002L` → C++20

#### 2. 數學相關 (`<math.h>` / `<cmath>`)

| 宏名稱      | 數值                 | 描述           |
| ----------- | -------------------- | -------------- |
| `M_PI`      | `3.141592653589793`  | 圓周率 π       |
| `M_E`       | `2.718281828459045`  | 自然對數底數 e |
| `M_LOG2E`   | `1.4426950408889634` | log₂(e)        |
| `M_LOG10E`  | `0.4342944819032518` | log₁₀(e)       |
| `M_LN2`     | `0.6931471805599453` | ln(2)          |
| `M_LN10`    | `2.302585092994046`  | ln(10)         |
| `M_SQRT2`   | `1.4142135623730951` | √2             |
| `M_SQRT1_2` | `0.7071067811865476` | 1/√2           |

#### 3. 文件 I/O (`<stdio.h>` / `<cstdio>`)

| 宏名稱     | 數值 | 描述                            |
| ---------- | ---- | ------------------------------- |
| `SEEK_SET` | `0`  | `fseek()`，從檔案開頭計算偏移量 |
| `SEEK_CUR` | `1`  | `fseek()`，從目前位置計算偏移量 |
| `SEEK_END` | `2`  | `fseek()`，從檔案結尾計算偏移量 |

#### 4. 錯誤碼 (`<errno.h>`)

| 宏名稱   | 數值（依系統不同） | 描述             |
| -------- | ------------------ | ---------------- |
| `EPERM`  | `1`                | 操作不允許       |
| `ENOENT` | `2`                | 沒有此文件或目錄 |
| `ESRCH`  | `3`                | 沒有此程序       |
| `EINTR`  | `4`                | 被中斷的系統調用 |
| `EIO`    | `5`                | I/O 錯誤         |
| `ENOMEM` | `12`               | 記憶體不足       |
| `EACCES` | `13`               | 權限不足         |
| `EEXIST` | `17`               | 檔案已存在       |

#### 5. 限制 (`<limits.h>` / `<climits>`)

| 宏名稱      | 數值                   | 描述                                |
| ----------- | ---------------------- | ----------------------------------- |
| `CHAR_BIT`  | `8`                    | `char` 型別的位數                   |
| `SCHAR_MIN` | `-128`                 | `signed char` 最小值                |
| `SCHAR_MAX` | `127`                  | `signed char` 最大值                |
| `UCHAR_MAX` | `255`                  | `unsigned char` 最大值              |
| `INT_MIN`   | `-2147483648`          | `int` 最小值                        |
| `INT_MAX`   | `2147483647`           | `int` 最大值                        |
| `UINT_MAX`  | `4294967295`           | `unsigned int` 最大值               |
| `LONG_MIN`  | `-9223372036854775808` | `long` 最小值（64 位系統）          |
| `LONG_MAX`  | `9223372036854775807`  | `long` 最大值（64 位系統）          |
| `ULONG_MAX` | `18446744073709551615` | `unsigned long` 最大值（64 位系統） |

#### 6. C++ 特定的宏

| 宏名稱        | 數值                               | 描述                                   |
| ------------- | ---------------------------------- | -------------------------------------- |
| `__cplusplus` | `199711L`, `201103L`, `201402L` 等 | C++ 標準版本（C++98、C++11、C++14 等） |
| `__STDC__`    | `1`                                | 是否符合 ANSI C 標準                   |
| `__FILE__`    | -                                  | 當前文件名稱                           |
| `__LINE__`    | -                                  | 當前行號                               |
| `__DATE__`    | -                                  | 編譯日期                               |
| `__TIME__`    | -                                  | 編譯時間                               |

## \#ifdef, \#ifndef, \#if 定義

### 1. `#ifdef`（如果已定義）

`#ifdef` 用來檢查某個 **宏是否已經被定義**，如果該宏存在，則執行後續的程式碼。

```cpp
#define DEBUG_MODE  // 定義 DEBUG_MODE

int main() {
#ifdef DEBUG_MODE //DEBUG_MODE被定義過的話執行
    cout << "Debug 模式開啟！" << endl;
#endif //表示ifdef的結束
    cout << "程式執行中..." << endl;
    return 0;
}
// 輸出：
// Debug 模式開啟！
// 程式執行中...
```

### 2. #ifndef（如果未定義）

\#ifndef 用來檢查某個 宏是否沒有被定義，如果該宏 尚未定義，則執行後續的程式碼。

```cpp=
#ifndef MAX_VALUE  // 如果 MAX_VALUE 沒有被定義
    #define MAX_VALUE 100
#endif

int main() {
    cout << "MAX_VALUE = " << MAX_VALUE << endl;
    return 0;
}
// 輸出：
// MAX_VALUE = 100
```

### 3. #if（條件判斷）

\#if 可用來根據 數值或表達式的結果 來決定是否編譯某段程式碼。

```cpp=
#include <iostream>

#define VERSION 2  // 定義版本號

using namespace std;

int main() {
#if VERSION == 1
    cout << "版本 1：基本功能" << endl;
#elif VERSION == 2 //else if
    cout << "版本 2：進階功能" << endl;
#else
    cout << "未知版本" << endl;
#endif
    return 0;
}
// 輸出：
// 版本 2：進階功能
```

- #if VERSION == 1，如果 VERSION 是 1，則執行對應程式碼。
- #elif VERSION == 2，如果 VERSION 是 2，則執行進階功能。
- #else，如果沒有匹配的條件，則執行 未知版本。

### 4. 常見用法：防止重複包含頭文件

在 C/C++ 中，防止頭文件被重複包含的常見做法是使用 #ifndef：
這樣可以避免頭文件被多次包含，導致編譯錯誤。

```cpp=
#ifndef MY_HEADER_H
#define MY_HEADER_H

void myFunction();

#endif  // MY_HEADER_H
```

## 模板 template

允許我們用一個通用的函式來適用於不同類型的變數，而不需要重複編寫相同邏輯的函式。

### 函式模板

```cpp=
template <typename T>
T add(T a, T b) {
    return a + b;
}

int main() {
    cout << "整數相加: " << add(3, 5) << endl;
    cout << "浮點數相加: " << add(2.5, 3.7) << endl;
    return 0;
}
// 輸出：
// 整數相加: 8
// 浮點數相加: 6.2
```

- T 是 泛型參數（Type Parameter），代表任何數據類型。
- add(3, 5) 會自動推導 T 為 int。
- add(2.5, 3.7) 會自動推導 T 為 double。

### 類別模板

```cpp=
template <typename T>
class Box {
private:
    T value;
public:
    Box(T val) : value(val) {}
    void show() {
        cout << "Box value: " << value << endl;
    }
};

int main() {
    Box<int> intBox(10);
    Box<double> doubleBox(5.75);
    Box<string> strBox("Hello Template");

    intBox.show();
    doubleBox.show();
    strBox.show();

    return 0;
}
// 輸出：
// Box value: 10
// Box value: 5.75
// Box value: Hello Template
```

### 非型別模板參數

除了類型參數 typename T，模板還可以接受 非型別參數（整數、指標、引用等）。
通常是陣列之類的才需要接受參數（引數）

```cpp=
template <typename T, int SIZE>
class Array {
private:
    T arr[SIZE];
public:
    void set(int index, T value) {
        if (index >= 0 && index < SIZE)
            arr[index] = value;
    }
    T get(int index) {
        return arr[index];
    }
    void printSize() {
        cout << "Array size: " << SIZE << endl;
    }
};

int main() {
    Array<int, 5> intArray;
    intArray.set(0, 42);
    cout << "Element at index 0: " << intArray.get(0) << endl;
    intArray.printSize();

    return 0;
}
// 輸出：
// Element at index 0: 42
// Array size: 5
```

#### 函式泛型化

```cpp=
template <class ForwardIterator, class T>//通常class跟tepename可以互相替代
```

函式模板 或 類別模板 的開頭，表示：

- ForwardIterator：代表某種「迭代器（Iterator）」類型。
- T：代表某種「資料類型」。

**(1) ForwardIterator：泛型「迭代器類型」**

- ForwardIterator 讓函式可以接受 任何可遍歷的容器類型，不只是 int\*（指標），還可以是：
  - vector\<int\>::iterator
  - list\<double\>::iterator
  - set\<string>::iterator
  - 甚至 指標（int\*）

**(2) T：泛型「資料類型」**

- T 讓函式可以適用於 任何資料型別，不只 int，還可以是：
  - double
  - string
  - char
  - 甚至是使用者自訂的型別

**沒有模板**
這個函式只能處理 int 陣列，如果要找 double 或 string，就得再寫一次類似的函式。

```cpp=
#include <iostream>

using namespace std;

int* findInt(int* first, int* last, int value) {
    while (first != last) {
        if (*first == value)
            return first;  // 找到，回傳指標
        ++first;
    }
    return nullptr;  // 沒找到
}

int main() {
    int arr[] = {1, 2, 3, 4, 5};
    int* result = findInt(arr, arr + 5, 3);

    if (result)
        cout << "找到數字：" << *result << endl;
    else
        cout << "找不到數字" << endl;

    return 0;
}
```

**有模板**
現在我們想讓這個 find 函式支援 任何型別的容器（如 vector、list）和任何資料型態（int、double、string）

```cpp=
#include <iostream>
#include <vector>

using namespace std;

// 泛型 find 函式
template <class ForwardIterator, class T>
ForwardIterator myFind(ForwardIterator first, ForwardIterator last, const T& value) {
    while (first != last) {
        if (*first == value)
            return first;  // 找到目標值，回傳迭代器
        ++first;
    }
    return last;  // 沒找到則回傳 last
}

int main() {
    vector<int> vec = {1, 2, 3, 4, 5};
    auto it = myFind(vec.begin(), vec.end(), 3);

    if (it != vec.end())
        cout << "找到數字：" << *it << endl;
    else
        cout << "找不到數字" << endl;

    return 0;
}
```

**其中**

```cpp=
auto it = myFind(vec.begin(), vec.end(), 3);
```

編譯器會：

- 推導 ForwardIterator 為 vector\<int>::iterator
- 推導 T 為 int
- 自動產生對應的函式

結果：

```cpp=
vector<int>::iterator myFind(vector<int>::iterator first, vector<int>::iterator last, const int& value)
```

const int\& value 是完整複製變數並且在函式中不能改變的意思

## int、long、long long、double 大小

1byte = 8bits
sizeof(int a) => 4byte
sizeof(int a[10]) => 40byte
sizeof(char c) => 1byte
sizeof(char c[10]) => 10 byte
| 類型 | 最大值 |
|:---------:|:------------------:|
| int (4byte)| 2^31^ - 1 (2.1*10^9^) |
| int* (8byte)| |
| long | 2^31^ - 1 |
| long long (8byte)| 2^63^ - 1 (9.2*10^18^)|
| size_t (=unsigned long long)(8byte) | 2^64^ -1(1.8*10^19^)
| float| 2^128^ (3.403x10^38^)|
| double| 2^1024^ (1.798x10^308^)|
| INT_MAX| int 最大值|
| INT_MIN| int 最小值|
| LLONG_MAX| long long 最大值|
| LLONG_MIN| long long 最小值|
| FLT_MAX| float 最大值|

## 看型別

```cpp=
typeid(k).name();
```

## 函數預設值

```cpp=
int function_name(int param1, int param2 = default_value);
```

EX:

```cpp=
int tailrecsum(int x, int runningtotal = 0) {
    if (x == 0) return runningtotal;
    return tailrecsum(x - 1, runningtotal + x);
}

int main() {
    cout << tailrecsum(5);  // ✅ 自動使用 runningtotal = 0
    cout << tailrecsum(5, 10); // ✅ runningtotal = 10
    //避免編譯錯誤（若 runningtotal 無預設值，呼叫 tailrecsum(5); 會報錯）
}
```

要注意預設值要從右設到左，從最後設到前面

```cpp=
int func(int a, int b = 10);  // ✅ 正確
int func(int a = 10, int b);  // ❌ 錯誤：非最後一個參數不能有預設值
```

## switch

可以用 if 來替代，但可讀性有差

```cpp=
int day = 6;
  switch (day) {
    case 6:
      cout << "Today is Saturday";
      break;
    case 7:
      cout << "Today is Sunday";
      break;
    default:
      cout << "Looking forward to the Weekend";
  }
```

可以多個 case 做同一件事，下面的 case0 與 case1 做相同的事

```cpp=
for(int a=0;a<3;a++){
    switch(a){
        case 0:
        case 1:
            cout<<"01";
            break;
        case 2:
            cout<<"02";
            break;
    }
}
//010102
```

## binary search

時間複雜度 log(n)

```cpp=
binary_search(a.begin(),a.end(),val);//回傳是否找到值
lower_bound(a.begin(),a.end(),val);//找到大於等於的值的位置

auto it=lower_bound(a.begin(),a.end(),make_pair(a,b))
auto it=lower_bound(a.begin(),a.end(),pair<int,int>(a,b))
//pair的正式寫法要用make_pair()或pair<int,int>()而不是{a,b}(會錯)。

template <
    ForwardIterator, class T>
bool binary_search (ForwardIterator first, ForwardIterator last, const T& val)
{
  first = std::lower_bound(first,last,val);
  return (first!=last && !(val<*first));
}//背後原理

//搜尋struct
struct Node{
    int x,y,z;
};
bool cmp(const Node &a,const Node &b){
    if(a.x == b.x && a.y == b.y)return a.z<b.z;
    else if(a.x == b.x)return a.y<b.y;
    else return a.x<b.x;
}//每一層都要排到序才行
bool cmp2(const Node &a,const Node &b){
    return a.x<b.x;
}
bool cmp3(const Node &a,const Node &b){
    if(a.x == b.x)return a.y<b.y;
    else return a.x<b.x;
}
signed main(){
    vector<Node> a={{1,2,3},{3,4,5},{5,6,7}};
    Node ans={1,2,3};
    sort(a.begin(),a.end(),cmp);
    binary_search(a.begin(),a.begin()+3,ans,cmp);//ans的地方不能放{1,2,3}
    //用struct一定要用cmp才行

    Node b;//假如只想搜尋一個元素的方法
    b.x=1;
    cout<<binary_search(a.begin(),a.end(),b,cmp2);

    Node b;//假如只想搜尋兩個元素的方法
    b.x=1;
    b.y=2;
    cout<<binary_search(a.begin(),a.end(),b,cmp3);
}
```

**pair 版**

```cpp=
#define pii pair<int,int>
#define ff first
#define ss second
bool cmp4(pii a,pii b){
    if(a.ff==b.ff)return a.ss<b.ss;
    else return a.ff<b.ff;
}
signed main(){
    vector<pii> a={{1,4},{1,3},{5,6}};
    pii ans={1,4};
    sort(a.begin(),a.begin()+3);
    cout<<binary_search(a.begin(),a.begin()+3,pii(1,2));//ans的地方不能放{1,2}，可以不用cmp。
}
```

## 陣列最大長度問題

陣列，是在棧（stack）中申請的一段連續的空間。棧的預設大小為 2M 或 1M，開的比較小。
全域性變數，全域性陣列，靜態陣列（static）則是開在全域性區（靜態區）（static）。大小為 2G，所以能夠開的很大。
而 malloc、new 出的空間，則是開在堆（heap）的一段不連續的空間。理論上則是硬碟大小。

```cpp=
//測試環境ram==8GB vscode
int a[22000][22000];//用相乘22000*22000計算最多到500000000
int a[490000000];//差不多這麼大
int mian(){
    int array[500000];//差不多這麼大
    char a[4*518028];
    int a[2000][200];//用相乘2000*200計算最多500000
    //vector、new放外面裡面一樣大
    vector<int>a(5000000000);
    vector<vector<int>> a(200000,vector<int>(20000));
    //二維也是相乘最多5GB，但編譯時間無敵久

    //new方法 二維new最多開5GB
    int *tem =new int [1000000001];

    int **a=new int*[500000];
    for(int i=0;i<500000;i++){
        a[i]=new int[10000]{0};
    }
}
```

## 數學

```cpp=
pow(a,b);//a的b次方
sqrt(a);//==pow(a,(float)1/2)
round(a);//4捨5入 4.5 -> 5
floor(a);//無條件捨去 4.5 -> 4
ceil(a);//無條件進位 4.2 -> 5
a = 12.45
modf(a,&b);//a == 0.45，b == 12;
abs(a)//絕對值
```

## 指標

\*去那個位置中的值 &取得它位置

```cpp=
int a //宣告變數
int *a //宣告指標
```

### const 修飾

```cpp=
int *a;//指向的值與指向的地址都可以改
const int *a;//指向的值不能改 指向的地址可以改
int * const a;//指向的值可以改 指向位置不能改(不能寫成int const *a)
const int * const a;//指向位置與值都不能改
```

### 函式指標

```cpp=
int main(){
    void hello();
    void(*func)()=hello;//(*func)()一定要這樣寫，原本hello要寫成&hello，但hello本來就會自動轉型成&hello，所以可以簡化
    func();//func()隱性轉型成&func()
    (***func)();//(***func)()隱性轉型成(***&func)()，*跟&抵銷變成(**func)()
    (&func)();//編譯錯誤自動轉型後變成(&&func)()沒辦法抵銷
}
void hello(){
    cout<<"hello";
}
```

有傳參數的話括號裡面要寫東西

```cpp=
void hello(int,int);
void(*func)(int,int)=hello;//(*func)
```

### 指標傳函式

sort(a.begin(),a.end(),cmp)中 cmp 的原理

```cpp=
int main(){
    void hello(int(*)(int,int));
    int add(int ,int);
    hello(add);
    return 0;
}
void hello(int (*op)(int,int)){
    cout<<op(1,2);
}
int add(int a,int b){
    return a+b;
}
```

### 陣列用其他指標搭配[ ]

```cpp=
char c[]="ab";
char *d=c;
cout<<d[1];
```

## 函式完整複製變數

```cpp=
void change(int &a){
    a=2;
}
int main(){
    int h=1;
    cout<<h<<" ";// 1
    change(h);
    cout<<h;// 2
}
```

## 遍歷

```cpp=
for(auto i:a){
    cout<<i<<endl;
    i = i*2;//不會改到值
}//auto不能改成vector<int>::iterator
for(vector<int>::iterator i=a.begin();i!=a.end();i++){
    cout<<(*i);
    i = i*2;//會改到值
}
```

## algotithm

### heap

```cpp=
int myints[] = {10,20,30,5,15};
vector<int> v(myints,myints+5);

make_heap (v.begin(),v.end());//製作，要先執行。把數字最大排在第一，其他亂排

pop_heap (v.begin(),v.end()); //把數字最大排在最後，其他亂排

v.push_back(99);
push_heap (v.begin(),v.end());//(重排)把數字最大排在第一，其他亂排

std::sort_heap (v.begin(),v.end());//用sort就好
```

### reverse 反轉

```cpp=
myvector//1 2 3 4 5
reverse(myvector.begin(),myvector.end());//5 4 3 2 1
```

## conditional(ternary)operator 三元運算子

```cpp=
((i==a) ? 10 : 20);//if i==a return 10,else return 20.
```

## 位元運算子

\&優先權大於\|
\&\&\與|\|也是

```cpp=
// >> <<
0001 << 1 = 0010//右加零 左去掉
1000 >> 1 = 0100//左加零 右去掉
// & AND 要兩個都是1
0 & 0 = 0
0 & 1 = 0
1 & 0 = 0
1 & 1 = 1
// | OR 其中一個是1
0 | 0 = 0
0 | 1 = 1
1 | 0 = 1
1 | 1 = 1
// ^ XOR 兩個不一樣
0 ^ 0 = 0
0 ^ 1 = 1
1 ^ 0 = 1
1 ^ 1 = 0
// ~ not 補位(相反)
~ 00000000000000000000000000000011   -> 3
 ----------------------------------
  11111111111111111111111111111100   -> -4
```

運算例子

```cpp=
//16進位表示法 但底層都是用二進制
//去掉最後四位 取8~4位後 最左邊加上 1111
0x10 | (N>>4) & 0xF
/*
 * ex:
 * n=        000100101100
 * (N>>4)    00010010 去四位
 * & 0xF     00001111 取四位
 * =         00000010
 * (0x10 |)  11110000 最左邊加1111
 * =         11110010
*/
```

### 特殊技巧

```cpp=
int a=15;//odd 奇數
a<<=1;//a乘以2
a>>=1;//a除以2 (慎用，-9 變 -5 但 9 變 4)
a=(a|1);//if a is even ,plus 1. if a is odd,no add.
a=(a&1);//equal a%2

void swap(int &a ,int &b){
    a^=b;
    b^=a;
    a^=b;
}
```

## 三元運算子

條件式 ? 條件式為 true 時執行的陳述句 : 條件式為 false 時執行的陳述句

```cpp=
int ret, a = 10, b = 11;
bool flag = true;
if (flag)
    ret = a;
else
    ret = b;
```

相當於

```cpp=
ret = flag ? a : b;
```

## distance,resize,unique

```cpp=
//時間複雜度是 O(n)
int myints[] = {10,20,20,20,30,30,20,20,10};   // 10 20 20 20 30 30 20 20 10
it = unique (myvector.begin(), myvector.end());   // 10 20 30 20 10 ?  ?  ?  ?
                                                                    ^ 傳回的位置
v.resize(大小)
distance(v.begin(),v.end()) ->[a,b)
```

## insert

```cpp=
vector<int> a{2,3,4};
a.insert(a.begin(),0)//0 2 3 4
a.insert(a.begin()+1,1);//0 1 2 3 4
```

## 離散化

當要數字代表的陣列太大時使用，重點是取得相對位置

```cpp=
vector<int> a{2,1,10};
sort(a.begin(),a.end());//要先排列因為lower_bound要用排列過後的陣列
a.erase(unique(a.begin(),a.end()),a.end());//刪除多餘的元素，unique刪除連續重複的元素並傳回新陣列end的位置。
lower_bound(a.begin(),a.end(),x)-a.begin()+1;//查詢X所在的位置。沒有則返回第一個大於X的位置。
upper_bound(a.begin(),a.end(),x)-a.begin()+1;//離散化沒用到(補充)，返回第一個大於X的位置。
```

## sort

包含在\<algorithem>
sort 只會交換陣列裡面的值，位置不變
複雜度 n\*log(n)

```cpp=
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
  vector<int> a{2,1};
  int k[2] = {2,1};

  cout<<&a[0]<<endl;
  cout<<a[0]<<endl;
  sort(a.begin(),a.end());
  cout<<a[0]<<endl;
  cout<<&a[0]<<endl;
/*0x1076eb0
2
1
0x1076eb0
*/
  cout<<&k[0]<<endl;
  cout<<k[0]<<endl;
  sort(begin(k),k+2);
  cout<<k[0]<<endl;
  cout<<&k[0]<<endl;
/*
0x7fffdcda5918
2
1
0x7fffdcda5918
*/
}
```

```cpp=
bool cmp1(const vector<int> a, const vector<int> b){
	return a[1] > b[1];
}//一微陣列用法

bool sortbysec(pair<int,int> a,pair<int,int> b){
    return (a.second < b.second);//升序1,3,4,6
}//pair用法 沒有指定時就是排first的順序。
sort(a.begin(),a.end(),cmp)
```

### 多條件排法

#### 法一

```cpp=
struct Node{
	int x,y,z;
};
bool cmp(const Node &a,const Node &b){
    if(a.x == b.x && a.y == b.y)return a.z<b.z;
    else if(a.x == b.x)return a.y<b.y;
    else return a.x<b.x;
}//每一層都要排到序才行
signed main(){
    vector<Node> a={{1,2,3},{3,4,5},{5,6,7},{3,7,1},{2,0,518}};
    Node ans={1,2,3};
    sort(a.begin(),a.end(),cmp);
    for(auto i:a)cout<<i.x<<" "<<i.y<<" "<<i.z<<endl;
}
```

#### 法二

```cpp=
#include <bits/stdc++.h>
using namespace std;

struct Node{
	int x,y,z;
};
bool cmp(const Node &a,const Node &b){
    if(a.x > b.x)return false;
    else if(a.x == b.x && a.y>b.y)return false;
    else if(a.x == b.x && a.y==b.y && a.z>b.z)return false;
	else return true;
}//false會交換
signed main(){
    vector<Node> a={{1,2,3},{3,4,5},{5,6,7},{3,7,1},{2,0,518}};
    Node ans={1,2,3};
    sort(a.begin(),a.end(),cmp);
    for(auto i:a)cout<<i.x<<" "<<i.y<<" "<<i.z<<endl;
}
```

#### 法三（最推）

```cpp=
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
```

## 陣列

```cpp=
     int arr[3] = {7};//會是7 0 0
     int arr[z][y][x] = {};//全部初始為0，{}裡面不用放東西
```

### 陣列不能亂用函式傳指標

**下面會出錯**

```cpp=
char* a(){
    char seg[7]="000000";
    return seg;
}
int main(int argc, char *argv[]) {
    printf("%s",a());//印不出東西來
}
```

### 陣列用其他指標搭配\[ \]

```cpp=
char c[]="ab";
char *d=c;
cout<<d[1];
```

#### 但二維會出錯

**錯誤語法**

```cpp=
int a[2][2]={{1,2},{3,4}};
int* b =a;
printf("%d\n",b[1][0]);
```

**正確方法**

```cpp=
int a[2][2] = {{1, 2}, {3, 4}};
int (*b)[2] = a;
// b 是一個指向包含 2 個 int 元素的陣列的指標
// a 的型態是 int (*)[2]，即「指向包含 2 個 int 元素的陣列的指標」
printf("%d\n", b[1][0]); // 輸出 3
```

**三維的話**
宣告了一個指標 b，其型態是 int (_b)[3][4]。這表示 b 是一個指向「包含 3 行，每行 4 個元素的二維陣列」的指標。由於 a 的型態退化為 int (_)[3][4]，所以 b 可以正確地指向 a。

```cpp=
int a[2][3][4] = {
    {
        {1, 2, 3, 4},
        {5, 6, 7, 8},
        {9, 10, 11, 12}
    },
    {
        {13, 14, 15, 16},
        {17, 18, 19, 20},
        {21, 22, 23, 24}
    }
};

// 使用指標來指向三維陣列
int (*b)[3][4] = a;

// 打印 b 指向的三維陣列中的一些元素
printf("a[0][1][2] = %d\n", b[0][1][2]); // 輸出 7
```

### 陣列在函式中

陣列是一串連續的記憶體，但在函示中只會傳位置與值，長度另外傳。所以在函式中只佔 8byte(兩個 int 的長度)。簡單來說，在宣告函式中就只是複製陣列的位置與長度，因此可以改到本體。

```cpp=
void printA(int b[]){//int b[]也可改成int *b
    /*完整複製 除非b[]改成(&b)[100]但是長度要一樣,且for(auto)也要改成這個才能運行。
    sizeof(b)/sizeof(b[0]);就會是100*/
    int a = sizeof(b)/sizeof(b[0]);//長度不是100而是2
    cout << sizeof(b) << " "<< a << endl;
    for(int i=0;i<100;i++){
        cout<<b[i]<<" ";
    }
    b[0] = 1;//會改到原本的
}
int main(){
    int b[100] = {0};
    cout << b <<endl;//注意這是位置0xced21ff520
    int a = sizeof(b)/sizeof(int);//算長度的方式
    cout << a << endl;//100
    printA(b);//只有傳位置與長度大小所以佔4*2個byte
}
```

但是 vector 就要傳位置(跟其他元素一樣)

```cpp=
int printV(vector<int> *a){
    vector<int> k = *a;//只是複製一份
    for(int i=0;i<k.size();i++){
        cout<< k[i]<<" ";
    }//1 2 3
    k[1] = 111;//沒改到1 2 3
    (*a)[1] = 111;//有改到1 111 3
}
int main(){
    vector<int> v{1,2,3};
    printV(&v);
    cout << v[1];
}
```

### 多維陣列

![](https://i.imgur.com/WCeEDdC.jpg)
c 的多維陣列沒辦法像 vector 寫死大小傳入到函式中。因為 c 的多維陣列是連續的，所以要用算的。

```cpp=
/*  錯誤示範
void print(int a[][][] 或 a*** 或 a* ){//除非寫死大小int a[4][3][2]
    for(int i=0;i<l;i++){
        for(int j=0;j<r;j++){
            for(int k=0;k<c;k++){
                cout<a[i][j][k];
            }
            cout<<endl;
        }
        cout<<endl;
    }
}*/
void print(int *a){
    for(int i=0;i<l;i++){
        for(int j=0;j<r;j++){
            for(int k=0;k<c;k++){
                cout<<*(a+i*r*c+j*c+k);
            }
            cout<<endl;
        }
        cout<<endl;
    }
}
int arr[l][r][c]={};
print((int*)arr);//(int*)arr=(&arr)[0][0][0]=int *
                 //arr=&arr[0]=int *[][]
```

### 特殊寫法

#### 二維

```cpp=
void func(int arr[][5]){//這裡是關鍵，5一定要放在這裡，而且不用寫int *
    for(int i=0;i<2;i++){
        for(int j=0;j<5;j++){
            cout<<*(*(arr+i)+j);
        }
    }
    arr[0][0]=11;//一樣會改到
}

int arr[2][5]={{1,2,3,4,5},{6,7,8,9,10}};
func(arr);
cout<<endl<<arr[0][0];//11
```

#### 三維

依此類推也是這樣
![](https://i.imgur.com/hkQWoEO.jpg)

```cpp=
void func(int arr[][2][5]){
    for(int i=0;i<2;i++){
        for(int j=0;j<2;j++){
            for(int k=0;k<3;k++){
                cout<<*(*(*(arr+i)+j)+k)<<" ";
            }
            cout<<endl;
        }
    }
    arr[0][0][0]=11;
}

int arr[2][2][5]={
    {{1,2,3},{6,7,8}},
    {{9,10,11},{12,13,14}}
};
func(arr);
cout<<endl<<arr[0][0][0];
```

## class 用法

### 變數

```cpp=
#include <iostream>
using namespace std;
class Car {        // The class
  public:          // Access specifier
    int a;
    int b;
};//注意要打分號
int main() {
    Car x[4];// Print values
    x[1].a=1;
    x[1].b=2;
    cout<<x[1].a<<endl;
    swap(x[0],x[1])//也可以整坨交換
  return 0;
}

```

### 含式

```cpp=
void print(){
    cout<<"good";
}

class myclass {
   public:
    void print(){
        cout<<"bad";
        print();//印出bad 然後一直遞回下去不會印上面的good
    }
    int hello(int a) {
        return a;
    }
};

signed main() {
    myclass obj;
    cout << obj.hello(1);
    obj.print();
    return 0;
}
```

## struct

### 使用方法

```cpp=
struct Point{
    int x, y;
};
int main(){
    Point p,x=1,k;
    cout<<p.x;//1

    p={1,2};// c++11以上可以這樣寫
    p=(struct Point){1,2};// c++11之下要用標準寫法

    k={1,2};
    if(k.x==p.x && k.y==p.y)//合法
    if(k==p)//兩坨直接比是不合法的
}
```

### 配合 typedef

c++可以不用 typedef 就直接用 Point 宣告東西

```cpp=
typedef struct Point{
     int n;
}Point;//把struct Point命名成Point
```

```cpp=
typedef struct Point{
      struct Point link;
}Point;//如果要用串列鏈結指向自己就不能先用重新命名的來宣告
```

### 記憶體算

https://hackmd.io/TZ2D4_O1Rbm-o3c7cjoObw

## bool 印成 true

```cpp=
cout.setf(ios_base::boolalpha);布林值會印成true false 而不是0 或 1
```

## partition (分組)

```cpp=
vector<int>v{1,3,2,4,5};
partition(v.begin(),v.end(),[](int i){ return i%2==0; });//2 4 1 3 5不會照順序
partition(v.begin(),v.end(),[](int i){ return i>=3; });//5 3 4 2 1
partition_point(v.begin(),v.end(),[](int i){ return i%2==0;});//傳送第一組的end()->1的位置
is_partitioned(v.begin(),v.end(),[](int i){ return i%2==0;});//判斷有沒有分過組
```

## switch 用法

有點像是 if else 的用法，但是比較有條理一點。

```cpp=
int day = 4;
switch(day) {
  case 1:
    cout << "Monday";
    break;
  case 2:
    cout << "Tuesday";
    break;
  default:
    cout <<"No match";//當沒有配對到時執行
}
```

## max_element min_element

返回一個 iterator，區間內最大的值。

```cpp=
vector<int> vec={2,3,1,5};//也可以用陣列
int max=*max_element(vec.begin(),vec.end());// 5
        //注意這裡是返回iterator，要加*才是值
```

## enum

有點像是字指定給的數字
https://www.programiz.com/c-programming/c-enumeration

```cpp=
enum week {Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday};

enum week today;
today = Wednesday;
printf("Day %d",today+1);//3
```

## static variable

跟全域變數不樣一樣，可以活永久的時間，但只活在自己的 scope 內。

```cpp=
void fuc(int i){
    static int x=0;
    x++;
    if(i==4)cout<<x;//5
    return;
}
int main(){
    for(int i=0;i<5;i++){
        fuc(i);
    }
}
```

## 看 C++編譯器版本

```cpp=
cout << __cplusplus << endl;
```

看數字對應到的版本

```
g++ main.cpp
199711
g++ -std=c++11 main.cpp
201103
g++ -std=c++14 main.cpp
201402
g++ -std=c++17 main.cpp
201500
```

## unique 與 erase

```cpp=
//每個區塊有重複的元素會被丟到後面，然後回傳刪完的尾端。
//接著用erase刪掉
int myints[] = {10,20,20,20,30,30,20,20,10};           // 10 20 20 20 30 30 20 20 10
vector<int> myvector (myints,myints+9);
vector<int>::iterator it;
it = std::unique (myvector.begin(), myvector.end());   // 10 20 30 20 10 ?  ?  ?  ?
                                                       //                ^
erase(it,myvector.end());
```

## 記憶體分配

![image](https://hackmd.io/_uploads/SyTDUWYJJl.png)
malloc 不會指定值，但 calloc 與 realloc 都會指定值為 0。realloc 可以用在擴充陣列大小。
![image](https://hackmd.io/_uploads/ryr8Y-FyJe.png)

**malloc、free**

```cpp=
int *arr = (int *)malloc(5 * sizeof(int)); // 分配空間給 5 個整數
if (arr == NULL) {
    printf("記憶體分配失敗\n");
    return 1;
}
free(arr);  // 釋放記憶體
```

**calloc**

```cpp=
int size=10;
int *arr = (int *)calloc(5, sizeof(int)); // 分配並初始化 5 個整數
printf("%d",arr[1]);//0
```

**realloc**
可以變大變小矩陣
但使用 realloc 縮小記憶體時，後面的數據會被釋放並無法再訪問。

```cpp=
int size=10;
int *arr = NULL;  // relloc之前的指標不能沒有指定東西，就算是NULL也好
arr = (int *)realloc(arr, 10 * sizeof(int));
printf("%d",arr[1]);
```

**memset**
複雜度 O(n)
可用來重置陣列
1.int 用 memset()函数初始化时，只能初始化为 0 或者-1，否则值將為随机数。
2.char 数组利用 memset()函数初始化时不受限制，可初始化为任意字符。

```cpp=
arr[2][2];
memset(arr,0,sizeof(arr));//arr[0][0]=0 arr[1][1]=0
memset(arr,-1,sizeof(arr));
memset(arr,'=',sizeof(arr));
memset(arr,2,sizeof(arr));//不合法的
```

**new、delet**

```cpp=
int* p = new int;    // 分配單個變量
delete p;            // 釋放單個變量

int* arr = new int[5];  // 分配陣列
delete[] arr;           // 釋放陣列
```

new 也可以用來構建 class 物件

```cpp=
#include <iostream>
using namespace std;

class MyClass {
public:
    MyClass() { // 建構函數
        cout << "物件被初始化！" << endl;
    }
};

int main() {
    MyClass* obj = new MyClass; // 使用 new 分配記憶體並調用建構函數
    delete obj; // 使用 delete 釋放記憶體
    return 0;
}

```

## if 特殊用法

可以先存入然後再比較

```cpp=
int c;
if((c=2)==1){
    printf("test1");//沒印出
}
if((c=2)==2){
    printf("test2");//有印出
}
```

## 檔案操作

### 讀取寫入

https://openhome.cc/Gossip/CGossip/FileIO.html

### exit vs EXIT_FAILURE

參數 EXIT_FAILURE（是 1），EXIT_SUCCESS（是 0）
https://blog.csdn.net/deniece1/article/details/102880681

## int 與 unsiged

https://blog.csdn.net/leaf_in_the_moon/article/details/121866091

在比较 int 和 unsigned 时，会先把 int 隐式类型转换为 unsigned int

int 型转换为 unsigned int 型其实就是把 int 型的 31 位和表示符号的最高位，一起看作是 unsigned int 型的 32 位一并读取，例如：
int 型 1：0000 0000 0000 0000 0000 0000 0000 0001，按 unsigned 的 32 位读法仍然为 1
int 型-1：1111 1111 1111 1111 1111 1111 1111 1111，按 unsigned 的 32 位读法为 2^32-1 = 4294967295
正数转换为 unsigned 时，数值与原来相等；负数转换为 unsigned 时，数值会发生变化。

```cpp=
#include<iostream>
using namespace std;
int main()
{
	if (-1 < (unsigned int)1)
		cout << "-1小于(unsigned int)1为真" << endl;
	else cout << "-1小于(unsigned int)1为假" << endl;
}
//程序运行打印输出为：
//-1小于(unsigned int)1为假
```

打印输出结果为：
1
4294967295

## 二進位 八進位 十六進位

https://blog.csdn.net/leaf_in_the_moon/article/details/121866091
可以輸入不同進位的表示法 程式會自動轉換

```cpp=
#include <stdio.h>

int main(void) {

    int a = 0b10100011;  //二进制数字    前綴0b
    int b = 0244;        //八进制数字    前綴0
    int c = 0XA5;        //十六进制数字  前綴0x

    printf("八进制输出：\n");    //以八进制形似输出
    printf("a=%o, b=%#o, c=%#o\n", a, b, c);  //中间加上#，可以输出前缀
    printf("十进制输出：\n");    //以十进制形式输出
    printf("a=%d, b=%#d, c=%#d\n", a, b, c);  //十进制没有前缀，加上没啥用
    printf("十六进制输出：\n");  //以十六进制形式输出
    printf("a=%x, b=%#x, c=%#X\n", a, b, c);  //X大写，则输出的前缀和字母都大写

    return 0;
}
```

## 二進位轉十進位

```cpp=
string a;
for(int i=0;i<strlen(a);i++){
    sum=(sum>>2)+a[i]-'0';
}
```

![image](https://hackmd.io/_uploads/Hy-kSI2rC.png)

## struct 算記憶體

1byte = 8bit
4byte = 32bit
int = 4byte
double = 8byte

**規則一:**
每放入一個東西要先檢查前面的記憶體大小是不是要放入的倍數

```cpp=
struct ex {
    int a;//4byte
    double b;//放入a之後變4byte，但是4不是8的倍數，所以最後記憶體會變成16byte
}
printf("%lu", sizeof(ex));//16byte
```

**規則二:**
最後要檢查是不是最大記憶體的大小

```cpp=
struct side {
    int c;//4byte
    double s;//4不是8的倍數 4要補成8 8+8=16
    int d;//16是4的倍數 不用補 16+4=20 最後20不是8的倍數要補成24
};
printf("%lu", sizeof(ex));//是24byte不是20
```

但在指定 bit 時不適用規則一
然後規則二看的是 byte 不是指定的 bit

```cpp=
struct ex {
    unsigned int a : 28;//29bit
    unsigned int b : 3;//29不是3的倍數，但不用看規則一，所以28+3=31bit，但31bit不是一個int(4byte)的倍數，所以補成4byte
};//4
```

電腦預設最大對齊數是 8，但其實可以用 pragma pack 改。改完後最大對齊就不能超過。就是說，如果就算有 double 的話對齊要是 8，但 pragma 設成 2 的話最多就只能對齊就只能是 2。

```cpp=
#pragma pack(2)//預設2
#pragma pack(4)//   4
#pragma pack(8)//   8
```

**預設**

```cpp=
struct ex {
    char a; // 0+1
    int b; // 4+4
}; //原本是8
```

**改 2**

```cpp=
#pragma pack(2)
struct ex {
    char a; // 0+1
    int b; // 2+4
}; //變成6
```

**不具名指定**

```cpp=
struct t1 {
    unsigned int a : 1;
    unsigned int :0;  // 等於unsigned int c:31;
    unsigned int b : 1;
};  // 8
```

**unsigned int a:32 == int a**

```cpp=
struct ss {
    unsigned int a : 29; //29 0+29(bit)
    unsigned int b : 31; //31 29+31(bit)
    unsigned int d : 32; //4byte 8+4 32的話跟int一樣，就沒有指定bit忽略規則一的功能。
    unsigned int e : 1; //1bit 12byte+1bit
}; //16
```

**其他例子**

```cpp=
struct ex {
    char a;// 0+1
    unsigned int b : 1; // 1+1 因為指定bit不用看規則一
    // 1+1 最後才因為int對齊，這裡雖然指定int b的大小，但是最後對齊還是看int的4byte。如果原本就有double在裡面，最後對齊就對齊8byte
};//4
```

```cpp=
struct ex {
    unsigned int b : 1; // 0+1
    int a; // 4+4
};//8
```

```cpp=
struct ss {
    char d;              // 1 0+1
    char a[1];           // 1 1+1
    unsigned int b : 8;  // 1 2+1
    unsigned int : 0;    // 1 3+1
    unsigned int f : 8;  // 1 4+1
    double s;            // 8+8
    int l;               // 4 16+4
};//24
```

```cpp=
#pragma pack(2)
struct ss {
    char d;              // 1 0+1
    char a[1];           // 1 1+1
    unsigned int b : 8;  // 1 2+1
    unsigned int : 0;    // 1 3+1
    unsigned int f : 8;  // 1 4+1
    double s;            // 8 6+8
    int l;               // 4 14+4
};//18
```

```cpp=
struct test {
    char a[21];//21 0+21
    char b[16];//16 21+16
    unsigned int c;//4 40+4
    union {
        char h[13];
        char d[40];
    };//union 會取最大的40 44+40
    struct
    {
        char e[40];
        char f[3];
        char g[6];
    };//struct算完後 49 49+84
};//算出133後對齊int完136
```

```cpp=
#pragma pack(2)
struct test {
    char a[21]; //21 0+21
    char b[16]; //16 21+16
    unsigned int c; //4 38+4
    union {
        char h[13];
        char d[40];
    };//40 42+40
    struct
    {
        char e[40];
        char f[3];
        char g[6];
    };//49 82+49=131
};//132
```

```cpp=
struct t1 {
    char c;//1 0+1
    struct test
    {
        int a[10];
    };//40 4+40
    char d[3];/3 44+3
};//48
```

## 找元素位置 lowerbound find

沒辦法排序用 find **時間複雜度 n**
有排序用 lower_bound **時間複雜度 logn**
只是要看存不存在就用 binary_search **時間複雜度 logn**

```cpp=
std::vector<int> vec = {1, 3, 5, 7, 9};

int target = 5;
auto it = std::find(vec.begin(), vec.end(), target);

if (it != vec.end()) {
    std::cout << "找到了 " << target << "，位於索引 " << (it - vec.begin()) << std::endl;
} else {
    std::cout << target << " 不存在於向量中。" << std::endl;
}
```

lower_bound 回傳第一個大於或等於要找的元素的位置 找不到會回傳 end
upper_bound 回傳第一個大於要找的元素的位置 找不到會回傳 end

```cpp=
std::vector<int> vec = {1, 3, 5, 7, 9};

int target = 5;
auto lower = std::lower_bound(vec.begin(), vec.end(), target);
auto upper = std::upper_bound(vec.begin(), vec.end(), target);

if (lower != vec.end() && *lower == target) {
    std::cout << target << " 找到了，位於索引 " << (lower - vec.begin()) << std::endl;
} else {
    std::cout << target << " 不存在於向量中。" << std::endl;
}

std::cout << "不小於 " << target << " 的第一個元素位置為索引 " << (lower - vec.begin()) << std::endl;
std::cout << "大於 " << target << " 的第一個元素位置為索引 " << (upper - vec.begin()) << std::endl;

/*
5 找到了，位於索引 2
不小於 5 的第一個元素位置為索引 2
大於 5 的第一個元素位置為索引 3
*/

```

equal_range 如果找不到也是回傳大於的第一個或是 end

```cpp=
std::vector<int> vec = {1, 3, 5, 5, 5, 7, 9};

int target = 5;
auto range = std::equal_range(vec.begin(), vec.end(), target);

std::cout << target << " 的範圍從索引 " << (range.first - vec.begin()) << " 到索引 " << (range.second - vec.begin()) << std::endl;
//5 的範圍從索引 2 到索引 5
```

## define int long long 的風險

使用 #define int long long int 會有以下風險：

- 影響標準庫：會干擾標準庫中使用 int 的地方，導致不預期的行為。
- 影響第三方庫：會影響第三方庫或舊代碼，可能導致不兼容。
- 內存和性能問題：long long int 佔用更多內存，影響性能。
- 調試困難：代碼難以讀懂和維護，錯誤訊息混淆。
- 全局影響：宏的作用域是全局的，難以控制局部應用。

建議使用 typedef 或是 using

```cpp=
typedef long long int ll;

// 或使用 C++11 的 using
using ll = long long int;

```

## return 與 exit

### 不同點

**return**
用於 main() 函數的結束，表示程式執行成功。
語義上屬於從 main() 函數返回結果給呼叫者（作業系統）。

```cpp=
int main() {
    printf("Hello, World!\n");
    return 0;  // 返回 0 給作業系統
}
```

**exit**
用於任何地方直接結束程式（不局限於 main() 函數）。
是標準庫函數，立即結束程式執行，不會返回到 main()。

- exit(0)：成功。
- exit(1)：一般性錯誤。
- exit(2)：命令行參數錯誤。
- exit(3)：文件未找到。
- exit(4)：權限問題。
- exit(>4)：自定義的錯誤情況

```cpp=
void cleanup() {
    printf("Cleaning up...\n");
    exit(0);  // 在其他函數中結束程式
}
int main() {
    cleanup();
    return 0;  // 永遠不會執行到這裡
}
```

父進程檢查退出碼

```cpp=
#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <unistd.h>

int main() {
    pid_t pid = fork();
    if (pid == 0) {
        // 子進程模擬不同的退出情況
        exit(3); // 假設子進程因文件未找到而退出
    } else {
        int status;
        wait(&status); // 父進程等待子進程結束

        if (WIFEXITED(status)) {
            int exit_code = WEXITSTATUS(status);
            if (exit_code == 0) {
                printf("Child process exited successfully\n");
            } else if (exit_code == 3) {
                printf("Child process: File not found error\n");
            } else {
                printf("Child process exited with code %d\n", exit_code);
            }
        }
    }
    return 0;
}
或是用 Shell腳本處理退出碼
```

```bash=
#!/bin/bash

./program arg.txt
exit_code=$?

if [ $exit_code -eq 0 ]; then
    echo "Program executed successfully"
elif [ $exit_code -eq 2 ]; then
    echo "Error: Command line argument missing"
elif [ $exit_code -eq 3 ]; then
    echo "Error: File not found"
else
    echo "Program failed with exit code $exit_code"
fi
```

### 什麼時候使用 return 0 或 exit(0)？

**使用 return 0:**
正常完成程式並退出。
用於 main() 函數的結尾。

**使用 exit(0):**
在程式執行過程中需要立即退出，例如：
某個條件滿足後提前結束程式。
發生不可恢復的錯誤（可以搭配非 0 的退出碼，如 exit(1) 表示錯誤）。
不需要返回到 main() 繼續執行程式。

## 物件 class

#### 兩種建構子

```cpp=
class Car {
public:
    string brand;
    int year;

    //法一：傳統建構子
    //易讀，但有額外的賦值開銷（物件建立時，成員變數會先被初始化為垃圾值，然後再賦值）。
    //無法初始化 const 或 reference 成員變數（因為它們不能被重新賦值）。
    Car(string b, int y) {
        brand = b;
        year = y;
    }

    // 法二：初始化列表建構子
    //更有效率（避免額外的賦值開銷）且可以初始化 const 或 reference 成員變數。
    //可讀性稍差
    Car(string b, int y) : brand(b), year(y) {}

    void display() {
        cout << brand << " " << year << endl;
    }
};

int main() {
    Car car1("Toyota", 2020);
    car1.display();  // Toyota 2020
    return 0;
}
```

**當需要在建構函式內執行額外邏輯（如 if 判斷）：使用傳統方法**

```cpp=
class Student {
public:
    string name;
    int age;
    Student(string n, int a) {
        if (a < 0) age = 0;
        else age = a;
        name = n;
    }
};
```

**當有 const 或 &（參考）成員變數：必須使用初始化列表**

```cpp=
class Example {
private:
    const int a;
    int &ref;
public:
    Example(int x, int &y) : a(x), ref(y) {}  // 必須使用初始化列表
};
```

### 主要架構

```cpp=
// 定義類別
class Car {
private:
    string password;  // 只有類別內能存取

public:
    // 屬性（成員變數）
    string brand;
    int year;

    // 建構函式 如果函式內要用與物件同樣名子的變數，那物件的變數要加this
    Car(string brand, int year) {
        this->brand = brand;
        this->year = year;
    }

    // 解構函式 物件被銷毀時自動執行
    ~Car() {
        cout << brand << " 被銷毀" << endl;
    }

    // 設定 private 成員
    void setPassword(string pwd) {
        password = pwd;
    }

    // 方法（函式）
    void displayInfo() {
        cout << "Brand: " << brand << ", Year: " << year << endl;
    }
};

int main() {
    // 創建物件
    //法一
    Car car1;
    car1.brand = "Toyota";
    car1.year = 2020;
    //法二
    Car car1("Honda", 2022);

    car1.setPassword("1234")
    car1.displayInfo();  // 輸出：Brand: Toyota, Year: 2020

    return 0;// 物件 car1 離開作用域時，自動執行解構函式
}
```

### 物件陣列

```cpp=
class Car {
public:
    string brand;
    int year;

    void display() {
        cout << brand << " " << year << endl;
    }
};

int main() {
    Car cars[2] = { {"Toyota", 2018}, {"Ford", 2022} };

    for (int i = 0; i < 2; i++) {
        cars[i].display();
    }

    return 0;
}
```

#### 隱含的拷貝建構函式（允許使用 {} 進行初始化）

```cpp=
Car(const Car& other) {};  // 編譯器自動生成
```

允許 Car 物件透過 {} 初始化，例如：

```cpp=
Car cars[2] = { {"Toyota", 2018}, {"Ford", 2022} };
```

- {"Toyota", 2018} 會對應到 brand = "Toyota"; year = 2018;
- {"Ford", 2022} 會對應到 brand = "Ford"; year = 2022;

C++ 允許用「聚合初始化（Aggregate Initialization）」，這種初始化方式適用於純資料類別（POD，Plain Old Data），其條件是：

1. 沒有 private 或 protected 成員變數
2. 沒有自定義的建構函式
3. 沒有虛擬函式
4. 沒有繼承

**如果提供「顯式建構函式」，聚合初始化會失效**
假設我們改寫 Car 類別，加上明確的建構函式：

```cpp=
class Car {
public:
    string brand;
    int year;

    // 明確建構函式
    Car(string b, int y) : brand(b), year(y) {}

    void display() {
        cout << brand << " " << year << endl;
    }
};

int main() {
    Car cars[2] = { {"Toyota", 2018}, {"Ford", 2022} }; // ❌ 編譯錯誤
    return 0;
}
```

❌ 這會導致錯誤！
因為 C++ 不允許聚合初始化用 {} 來呼叫顯式建構函式，需要改成：

```cpp=
Car cars[2] = { Car("Toyota", 2018), Car("Ford", 2022) };

或是
Car cars[] = { Car("Toyota", 2018), Car("Ford", 2022) };
```
