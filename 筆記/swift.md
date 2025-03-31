---
title: swift
tags: [Coding]

---

# swift

## 加入第三方庫
![截圖 2025-02-27 下午6.36.50](https://hackmd.io/_uploads/HJuybTa9Jx.png)
按右下角add package
![截圖 2025-02-27 下午6.37.15](https://hackmd.io/_uploads/HkF1bpp91g.png)
按add package
![截圖 2025-02-27 下午6.37.27](https://hackmd.io/_uploads/BktJZaac1e.png)
這點很重要 要到Frameworks,Libraries,and Embedded Content按\+號
![截圖 2025-02-27 下午6.37.42](https://hackmd.io/_uploads/H1cJZ6pcye.png)
把Alamofire add進去
![截圖 2025-02-27 下午6.37.55](https://hackmd.io/_uploads/ryt1bpTqJl.png)

## 語法

## var let
```swift=
//練習一
let pi: Double = 3.14
var radius: Double = 10.0
var circleArea: Double = radius * radius * pi
var a: String = "dfdf"
print("圓面積為: \(circleArea)")
print("aa","bb")
```
```
圓面積為: 314.0
aa bb
```
## if
```swift=
//練習五
var examSore = 80
if(examSore >= 60){
    print("恭喜你通過考試")
}
examSore = 40
if(examSore >= 60){
    print("恭喜你通過考試")
}
```
```
恭喜你通過考試

```
## function 
```swift=
//練習六
func scorefunc(score: Int){
    if score >= 85{
        print("恭喜你通過考試～表現得很好喔")
    }
    else if score >= 60{
        print("恭喜你通過考試")
    }
    else{
        print("再好好加油")
    }
}

var examSore2: Int = 90
scorefunc(score: examSore2)
examSore2 = 70
scorefunc(score: examSore2)
examSore2 = 50
scorefunc(score: examSore2)

func scorefunc2(_: Int){ //也可留白
    
}
scorefunc2(1)
```
```
恭喜你通過考試～表現得很好喔
恭喜你通過考試
再好好加油
```
```swift=
//練習七
func scorefunc2(score: Int){
    if score >= 85 && score <= 100{
        print("你本學期成積為A")
    }
    else if score >= 75{
        print("你本學期成積為B")
    }
    else if score >= 60{
        print("你本學期成積為C")
    }
    else if score >= 0{
        print("你本學期成積為D")
    }
    
}

var examSore3: Int = 90
scorefunc2(score: examSore3)
examSore3 = 80
scorefunc2(score: examSore3)
examSore3 = 65
scorefunc2(score: examSore3)
```
```
你本學期成積為A
你本學期成積為B
你本學期成積為C
```

```swift=
//function
func greeting1(){
    print("Hello,你好")
}
func greeting2(name:String){
    print("Hello,你好\(name)")
}
func greeting3()->String{
    return "Hello,你好"
}
func greeting4(name:String)->String{
    return "Hello,你好\(name)"
}
func greeting5(name1:String, name2:String)->String{
    return "Hello,你好\(name1)和\(name2)"
}
greeting1()
greeting2(name: "Alice")
var str1:String = greeting3()
print(str1)
print(greeting4(name: "Alice"))
var str2:String = greeting5(name1:"Alice", name2:"Joyce")
print(str2)
```
```
Hello,你好
Hello,你好Alice
Hello,你好
Hello,你好Alice
Hello,你好Alice和Joyce
```

```swift=
//練習十
func plus(a:Int, b:Int)->Int{
    return a+b
}
var v1:Int = 4,v2:Int = 9;
var result:Int = plus(a: v1, b: v2)
print("\(v1) + \(v2) = \(result)")
```
```
4 + 9 = 13
```
### 可以傳tuple出來
```swift=
func aaa(_ a:Int, _ b:Int)->(Int, Int){
    return (a+1,b+1)
}
let result = aaa(1,2)
print(result)
```
```
(2, 3)
```
### 在函式裡面放函式
```swift=
func add(_ n1:Int,_ n2:Int)->Int{
    return n1+n2;
}
func sub(_ n1:Int,_ n2:Int)->Int{
    return n1-n2;
}

//法一
func printResult(_ op:(Int,Int)->Int, _ a:Int, _ b:Int){
    print(op(a,b))
}

//法二
typealias operate = (Int,Int) -> Int //跟typedef一樣
func printResult2(_ op:operate, _ a:Int, _ b:Int){//取代原本冗長部分
    print(op(a,b))
}

printResult(add, 3, 4)
printResult(sub, 3, 4)
```
```
7
-1
```
### 含式同名不同參數
完全一樣會出現ambiguous錯誤
![image](https://hackmd.io/_uploads/H1W0npPaJl.png)
```swift=
func add(_ n1:Int,_ n2:Int)->Int{
    return n1+n2;
}
func add(n1:Int,_ n2:Int)->Int{//些微不一樣也可以 參數或回傳不一樣都可以
    return n1+n2+1;
}
func add(_ n1:String,_ n2:String)->String{
    return n1+n2;
}

print(add(1, 2))
print(add(n1: 1, 2))
print(add("aa","bb"))
```
```
3
4
aabb
```

### 系統其實幫你隱藏的預設了回傳 -> Void
都不會報錯
```swift=
func printHello1(){
    print("hello")
}
printHello1()

func printHello2()->Void{
    print("hello")
}
printHello1()

func printHello3(){
    print("hello")
    return Void()
}
printHello1()

func printHello4(){
    print("hello")
    return ()
}
printHello1()

func printHello5(){
    print("hello")
    return
}
printHello1()

```
## for
```swift=
//練習八
for i in 1 ... 9{
    print("5 * \(i) = \(5*i)")
}
```
```
5 * 1 = 5
5 * 2 = 10
5 * 3 = 15
5 * 4 = 20
5 * 5 = 25
5 * 6 = 30
5 * 7 = 35
5 * 8 = 40
5 * 9 = 45
```
```swift=
//for(int i=10;i<=1010;i+=100)
for i in stride(from: 10,through: 1010,by: 100){ 
    print(i)
}
```
```
10
110
210
310
410
510
610
710
810
910
1010
```
```swift=
var arr1=["a","b","c"]
for name in arr1{
    print(name)
}
```
```
a
b
c
```
```swift=
for i in 1...10 where i%2 == 1{
    print(i)
}
```
```
1
3
5
7
9
```
## while
```swift=
//練習九
var index = 1
while index <= 9{
    print("5 * \(index) = \(5*index)")
    index += 1
}
```
```
5 * 1 = 5
5 * 2 = 10
5 * 3 = 15
5 * 4 = 20
5 * 5 = 25
5 * 6 = 30
5 * 7 = 35
5 * 8 = 40
5 * 9 = 45
```
```swift=
repeat{
    code
}while condition //就是do while
```
## class
```swift=
//練習十一
class Person{
    var firstname:String
    var lastname:String
    init(firstname:String, lastname:String){
        self.firstname = firstname
        self.lastname = lastname
    }
    func sayHello(){
        print("Hello,my name is \(lastname) \(firstname)")
    }
}
var alice:Person = Person(firstname: "Alice", lastname: "Lin")
var joyce:Person = Person(firstname: "Joyce", lastname: "Lu")
alice.sayHello()
joyce.sayHello()
```
```
Hello,my name is Lin Alice
Hello,my name is Lu Joyce
```
## struct
```swift=
//練習十二
struct Movie{
    var title:String
    var director:String
    func describe(){
        print("\(title)這部電影是由\(director)所執導")
    }
}
var lucy:Movie = Movie(title: "露西",director: "盧・貝松")
var anna:Movie = lucy
lucy.describe()
anna.describe()
anna.title = "安娜"
lucy.describe()
anna.describe()

```

```
露西這部電影是由盧・貝松所執導
露西這部電影是由盧・貝松所執導
露西這部電影是由盧・貝松所執導
安娜這部電影是由盧・貝松所執導
```



## class與struct的比較
```swift=
struct PersonStruct {
    var name: String
}

class PersonClass {
    var name: String
    init(name: String) {
        self.name = name
    }
}

var structPerson = PersonStruct(name: "John")
var classPerson = PersonClass(name: "John")

var copiedStruct = structPerson
var copiedClass = classPerson

copiedStruct.name = "Mike"
copiedClass.name = "Mike"

print(structPerson.name) // John (不受影響)
print(classPerson.name)  // Mike (受到影響)
```
```
使用 struct 時，改變 copiedStruct 不會影響原始值。
使用 class 時，改變 copiedClass 會影響原始物件。
```
* 使用 struct：
    * 當需要一個 簡單且不可變的物件（例如資料模型、座標點、顏色等）。
    * 當希望提升效能時，因為結構體是 輕量且效能較佳。
    * 當不需要繼承功能時。
* 使用 class：
    * 當需要 共享狀態（例如管理應用程式中的單一實例）。
    * 當需要使用 多態與繼承。
    * 當涉及 複雜物件與大量資料處理。
## String
```swift=
//練習二
var myGreeting = "Hello, "
var name = "John"
myGreeting += name
print(myGreeting)
let wordCount = myGreeting.count
print("myGreeting 長度為 \(wordCount)")
```
```
Hello, John
myGreeting 長度為 11
```
## array
```swift=
//練習三
var studentScores3: [Int] = []
studentScores3.append(85)
studentScores3.append(90)
studentScores3.append(78)
print("目前有\(studentScores3.count)筆學生資料")
studentScores3[0] = 100;
print("3號同學分數為\(studentScores3[0])")
```
```
目前有3筆學生資料
3號同學分數為100
```
複製陣列
```swift=
var arr = [1,2,3,4]
var newArr = Array(arr[1...3])
print(newArr)//[2, 3, 4]
```
初始化
```swift=
//var arr1 = []//錯誤
//var arr2: [Int]//錯誤
var arr3: [Int] = []//正確
//arr2.append(1)//錯誤 沒有初始值
arr3.append(1)
print(arr3) // [1]
```
元素控制
```swift=
var arr3: [Int] = []
arr3.append(1)
arr3 += [3] //也是加在最後面
arr3.insert(2, at: 1)
print(arr3)
arr3 = [4,5,6]//可以直接指定成另一個陣列
print(arr3)
arr3.swapAt(0, 2)//交換
print(arr3)
arr3.remove(at: 1) //移除指定
print(arr3)
arr3.removeFirst() //popfront
print(arr3)
arr3.removeLast()  //popback
print(arr3)
print(arr3.isEmpty)//看是否空
```
```
[1, 2, 3]
[4, 5, 6]
[6, 5, 4]
[6, 4]
[4]
[]
true
```
查看
```swift=
var arr3: [Int] = [1,5,6,5,7]
print(arr3.count)  //數有多少個元素
print(arr3.first)  //Optional(1) 要解開
if let i = arr3.first ,let j = arr3.last ,let k = arr3.max(){
    print(i,j,k) //1
}
print(arr3.contains(2))
if let i = arr3.firstIndex(of: 5), let j = arr3.lastIndex(of: 5){//找5這個元素 第一次出現與最後一次出現的位置 也要解開
    print(i,j)
}

for i in arr3.enumerated(){
    print(i) // i是一個tupple
}
//法ㄧ
for i in arr3.enumerated(){
    print(i.offset,i.element)
}
print()
//法二
for (i,j) in arr3.enumerated(){
    print(i,j)
}
```
```
5
Optional(1)
1 7 7
false
1 3
(offset: 0, element: 1)
(offset: 1, element: 5)
(offset: 2, element: 6)
(offset: 3, element: 5)
(offset: 4, element: 7)
0 1
1 5
2 6
3 5
4 7

0 1
1 5
2 6
3 5
4 7
```
陣列放tuple
```swift=
let people: [(String, Int)] = [
    ("Alice", 25),
    ("Bob", 30),
    ("Charlie", 22)
]

print(people)
// [("Alice", 25), ("Bob", 30), ("Charlie", 22)]
```
```swift=
let products: [(name: String, price: Double)] = [
    (name: "iPhone", price: 999.99),
    (name: "MacBook", price: 1299.99),
    (name: "iPad", price: 799.99)
]

print(products[0].name) // iPhone
print(products[1].price) // 1299.99

```
## ditionary
```swift=
//練習四
var days:[String:String] = ["Sunday":"星期天","Monday":"星期一","Tuesday":"星期二"]
print("Monday為\(days["Monday"] ?? "")")
// 那這行程式碼會輸出：
// Monday為星期一
// 如果 days 中沒有 "Monday"，則輸出：
// Monday為
days["Sunday"] = "星期日"
print("Sunday為\(days["Sunday"] ?? "")")
days["Wednesday"] = "星期三"
days["Thursday"] = "星期四"
days["Friday"] = "星期五"
days["Saturday"] = "星期六"
print("一星期有\(days.count)天")
days["Sunday"] = nil
print("一星期有\(days.count)天")
print("Sunday為\(days["Sunday"] ?? "")")
```
```
Monday為星期一
Sunday為星期日
一星期有7天
一星期有6天
Sunday為
```
新增刪除
```swift=
var a: [String:Int] = [:]//賦予空值
a = ["aa":5,"bb":2,"cc":3]
print(a)//字典沒有順序
print(a["aa"])//可能有nil所以是optional
print(a["dd"])//nil 不會新增"dd"進去
print(a.isEmpty)
print(a.count)
a["aa"] = 1 //修改
a["dd"] = 2 //加新的值進去
a.removeValue(forKey: "bb")//刪除某個值
a["cc"] = nil//或是這樣刪除
```
```
["bb": 2, "cc": 3, "aa": 5]
Optional(5)
nil
false
3
```
遍歷
```swift=
var a: [String:Int] = [:]//賦予空值
a = ["aa":5,"bb":2,"cc":3]

for (i,j) in a {
    print(i,j)
}

for (_,j) in a{
    print(j)
}

for i in a.keys{
    print(i)
}

for j in a.values{
    print(j)
}
```
```
bb 2
aa 5
cc 3
2
5
3
bb
aa
cc
2
5
3
```
## tuple
```swift=
var myTuple = (4,"老王","老牛")//像是pair這種結構
print("\(myTuple.0) \(myTuple.1) \(myTuple.2)")
var (score1, name1, _) = myTuple//直接賦值 沒有要賦值的就用_
print("\(score1) \(name1)")

let myTuple1 = (score:3, name:"李四")//可以命名 let是const的意思
print("\(myTuple1.score) \(myTuple1.name)")
```
## set
```swift=
var a: Set<Int> //宣告方式比較特別
a = [1,2,3,1] //重複的數不會再被放進去
print(a)
print(a.contains(2))
a.insert(5)
a.remove(2)
var b: Set<Int> = [3]
print("a:\(a)")
print("b:\(b)")
print(a.intersection(b))//兩個set的交集
print(a.symmetricDifference(b))//全部剪掉交集（對稱差集）
print(a.union(b))//連集
a.formUnion(b)//把b的都加進a
print(a)
```
```
[3, 1, 2]
true
a:[3, 1, 5]
b:[3]
[3]
[1, 5]
[3, 1, 5]
[3, 1, 5]
```
## enum
```swift=
enum myenum1 {
    case a
    case b
}
enum myenum2:String {
    case c = "abc"
    case d = "def"
}
enum myenum3:Int {
    case e = 3 //指定第一個的rawValue
    case f
}
enum myenum4:Int {
    case g //沒有指定rawValue的話系統會給0
    case h //rawValue = 1 
}
print("myenum1 \(myenum1.a)") //只會印出a
//print(myenum.a.rawValue) //要加rawValue 的話要先指定成變數
print("myenum2 \(myenum2.c.rawValue)")
print("myenum3 \(myenum3.e.rawValue)")
print("myenum4 \(myenum4.g.rawValue)")

enum Obj {
    case a,b,c,d;
}
let tmp:Obj = .a;
if(tmp == .a){
    print("good")
}
```
```
myenum1 a
myenum2 abc
myenum3 3
myenum4 0
good
```
## Optional
nil是空值但不代表0
0也是一個值
### 封裝
```swift=
var a:String?
//var a:String? = nil 預設會給nil值

print(a)
a="abc"
print(a)
a=nil
print(a)

let b = Int("2")//將字串轉成變數
print(b)//但其實是optional
let c = Int("abc")//因為可能轉錯東西 所以要用optional包裝
print(c)//轉錯了所以是nil
```
```
nil
Optional("abc")
nil
Optional(2)
nil
```
```swift=
let weather = wxElement?["time"][0]["parameter"]["parameterName"].stringValue ?? "未知"
//第一個?是怕wxElement會是nil，以optional處理。第二個??是如果是nil就回傳"未知"
```
### 暴力拆箱（有風險）
```swift=
var a: Int? = 2
print(a!)

var b: Int? = nil
print(b!)//因為是nil 所以會報錯
```
```
2
報錯
```
### 安全開箱
**if拆箱**
開完箱之後 就不能用（不再同一個scope）
要裡面不是nil才會進入if
```swift=
var a: Int? = 2

if(a != nil){
    let k = a!
    print(k)
}

if let k = a{//簡化成這樣
    print(k)
}

if(a != nil){//在scope內變數可以取一樣名子
    let a = a!
    print(a)
}

if let a = a{//在scope內變數可以取一樣名子
    print(a)
}

print(a)
```
```
2
2
2
2
Optional(2)
```
**多重開箱**
```swift=
var a: Int? = 2
var b: Int? = 3
if let a = a, let b = b {
    print(a + b)
}else{
    print("error")
}
```
**guard開箱**
是開完箱之後還可以繼續用那個變數
但是是區域變數
```swift=
func printNumber(optionalNumber: Int?) {
    guard let number = optionalNumber else { //如果開失敗就跳出
        print("數字是 nil，結束執行")
        return
    }
    print("數字是：\(number)")
}
```
```
printNumber(optionalNumber: 10)
printNumber(optionalNumber: nil)
```
**??開箱**
```swift=
var a:Int?
var b = a ?? 1 // 如果a是nil 就賦予??後面的值
print(b)

var c:Int? = 3
var d = c ?? 1 // 如果a有值 就賦予a的值 並且是開箱完的值
print(d)
```
```
1
3
```
## closure
```swift=
let a = {
    (n1:Int, n2:Int) -> Int in
    return n1 * n2
}
print(a(2,3))

let b:(Int, Int) -> Int = { //或是先宣告型別再寫函式
    (n1,n2) in
    return n1 * n2
}
print(b(2,3))

let c:(Int, Int) -> Int = { //只有一行可以不用寫return
    (n1,n2) in
    n1 * n2
}
print(c(2,3))

let d:(Int, Int) -> Int = { //可以用$0 $1代替
    $0 * $1
}
print(d(2,3))
```
```
6
6
6
6
```
# swiftUI
## 功能介紹
```swift=
import SwiftUI

@main //標記應用程式的入口點。
struct chatApp: App { //chatApp 型別是 App protocol 的結構體，表示這個struct是一個應用程式。
    var body: some Scene { //body 是必要屬性，類似於 SwiftUI 的 View。
                           //它的型別是 some Scene，表示需要一個視圖場景來顯示 UI。通常代表應用程式的主要視窗或多視窗環境。
        WindowGroup { //用來定義應用程式的主要視窗群組，每當使用者開啟應用程式時，會顯示一個新的視窗。
            ContentView()//ContenView是主要視圖
        }
    }
}
```
```swift=
import SwiftUI

struct ContentView: View {//用來定義 SwiftUI 中的畫面。是View protocol，代表它可以作為畫面或元件使用。SwiftUI 中，每個畫面、按鈕、文字等 UI 元件都是 View。
    
    var body: some View {//body以外可以放宣告的變數或函式
                         //some View 是一種不透明回傳型別，表示 body 會回傳某種符合 View protocol 的物件，但具體的型別對外部不可見。
        VStack{ //VStack 也是一種 View
            
        }
    }
}

#Preview {//讓右邊可以顯示預覽話面 不會被編譯進去
    ContentView()
}

```
## 排版練習
```swift=
//
//  ContentView.swift
//  chat
//
//  Created by Bruce on 2025/3/28.
//

import SwiftUI

struct ContentView: View {
    var myword = Text("helloaaaasd")
    var myButton = Button(action:{} , label: {Text("my first button")})
    var body: some View {
        VStack(alignment: .trailing) {//垂直排列 最多10個 向右對齊
            Spacer()
            HStack(alignment: .top, spacing: 20){//水平排列 20是間距 向上對齊 很龜毛要按照順序
                Rectangle().foregroundColor(.yellow)
                    .frame(width: 10,height: 100)
                myword.blur(radius: /*@START_MENU_TOKEN@*/3.0/*@END_MENU_TOKEN@*/)
                myword
            }
            Spacer()
            myword
                .rotation3DEffect(Angle(degrees: 50), axis: (x:10 , y:10 , z:20))
            Spacer()//這三個spacer會讓他們自己調整間距
            Button("first Button"){
                
            }
            Button(action: {}){
                Text("button")
            }
            .background(Color.orange)
            .cornerRadius(.infinity)
            .padding(30)
            myButton.background( Image(systemName: "button.programmable"))
        }
    }
}

#Preview {
    ContentView()
}
```
![image](https://hackmd.io/_uploads/HJWuwWE6Je.png =300x)

## @state
swift的class與struct幾乎一樣
差別在於struct的內容不太能改變但class可以
但swiftUI剛好就是struct的結構
所以加上@State才會更新內容並且渲染到swiftUI上面
```swift=
struct ContentView: View {
    @State var score1 = 1
    var score2 = 1
    var body: some View {
        Button(action:{
            self.score1 += 1 //不會報錯
            self.score2 += 1 //會報錯
        }){
            Text("button")
        }
        
    }
}
```
實時更新例子
```swift=
struct ContentView: View {
    @State var score1 = 1.0//一定要打1.0才知道是浮點數打1只會覺得是整數int
    var body: some View {
        VStack{
            Text("\(score1)")
            Slider(value: $score1, in: 1...100)//這邊是浮點數
        }
    }
}
```
![image](https://hackmd.io/_uploads/HkSYXfETkg.png)

## @Binding
```swift=
struct ContentView: View {
    var body: some View {
        VStack {
            Text("歡迎來到 Chat App")
            ParentView()
        }
    }
}

struct ParentView: View {
    @State private var isOn = false

    var body: some View {
        VStack {
            ToggleView(isOn: $isOn) //把isOn這個變數傳給ToggleView取使用
            Text(isOn ? "開啟中" : "關閉中")
        }
    }
}

struct ToggleView: View {
    @Binding var isOn: Bool

    var body: some View {
        Toggle("開關狀態", isOn: $isOn) //借由按鈕去改變狀態
            .padding()
    }
}
```
![image](https://hackmd.io/_uploads/HJadhQ_T1g.png =300x)

## @enviroment
@Environment 讓視圖可以訪問系統環境變量或自定義環境變量。
```swift=
struct ContentView: View {
    @Environment(\.colorScheme) var colorScheme
    
    var body: some View {
        Text("當前模式: \(colorScheme == .dark ? "深色" : "淺色")")
            .padding()
            .background(colorScheme == .dark ? Color.black : Color.white)
            .foregroundColor(colorScheme == .dark ? Color.white : Color.black)
    }
}
```

## @ObservedObject
要手動傳入obj到要用的視圖
```swift=
// 定義一個 ObservableObject，負責管理使用者的資料
// ObservableObject 是一個可以讓 SwiftUI 監聽資料變化的協定
class UserSettings: ObservableObject {
    // 使用 @Published 來標記變數，當它改變時，所有監聽它的視圖都會重新渲染
    @Published var username = "Guest"
    @Published var isLoggedIn = false
}

struct ProfileView: View {
    // 使用 @ObservedObject 綁定資料來源，監聽 UserSettings 的變化
    // settings 是由外部傳入的 UserSettings 物件
    @ObservedObject var settings: UserSettings
    
    var body: some View {
        VStack {
            Text("用戶名: \(settings.username)")
            Text("登入狀態: \(settings.isLoggedIn ? "已登入" : "未登入")")
            Button("登入") {
                settings.isLoggedIn = true
                settings.username = "SwiftUI開發者"
                print("用戶已登入，名稱變更為 \(settings.username)")
            }
        }
    }
}

struct ContentView: View {
    // 創建一個 UserSettings 的實例，供 ProfileView 使用
    let settings = UserSettings()
    
    var body: some View {
        // 將 settings 物件傳入 ProfileView，讓它監聽並顯示資料
        ProfileView(settings: settings)
        //變數名稱要對應@ObservedObject var settings: UserSettings 
        //名稱不一樣會報錯
    }
}
```
## @StateObject
@StateObject 類似於 @ObservedObject，但它還負責創建和管理對象的生命週期。當視圖重新渲染時，@StateObject 確保對象不會被重新創建。
為了減少渲染所造成的記憶體消耗
```swift=
class DataModel: ObservableObject {
    @Published var value = 0
    
    init() {
        print("DataModel 被初始化")
    }
}

struct ContentView: View {
    @StateObject private var model = DataModel()
    
    var body: some View {
        VStack {
            Text("值: \(model.value)")
            Button("增加") {
                model.value += 1
            }
            // 即使這個視圖內部結構變化，model 也不會被重新初始化
        }
    }
}
```
## @EnvironmentObject
只要用了.environmentObject()之後子圖只要用@EnvironmentObject var settings: AppSettings 就可以共用變數資料並且更改
```swift=
import SwiftUI

// 根視圖注入環境對象
@main
struct chatApp: App {
    @StateObject private var settings = AppSettings()
    
    var body: some Scene {
        WindowGroup {
            ContentView()
                .environmentObject(settings)
                //如果不加environemntObject(settings)的話，當程式執行到需要使用 @EnvironmentObject 的視圖時，例如 SettingsView 或 DetailView，它們將無法找到對應的環境物件 (AppSettings)，並且會導致 程式崩潰（Crash）。
                //但所有子視圖，包括 SettingsView 和 DetailView，都可以使用 @EnvironmentObject 直接存取 AppSettings。
        }
    }
}

// 定義共享的數據模型
class AppSettings: ObservableObject {
    @Published var theme = "默認"
    @Published var fontSize = 14
}



// 主視圖
struct ContentView: View {
    var body: some View {
        NavigationView {
            SettingsView()
        }
    }
}

// 設置視圖 - 不需要傳入 settings
struct SettingsView: View {
    @EnvironmentObject var settings: AppSettings
    
    var body: some View {
        VStack {
            Text("當前主題: \(settings.theme)")
            Text("字體大小: \(settings.fontSize)")
            
            Button("更改主題") {
                settings.theme = "暗黑"
            }
            
            Button("增大字體") {
                settings.fontSize += 2
            }
            
            NavigationLink("前往詳情頁", destination: DetailView())
        }
    }
}

// 詳情視圖 - 同樣可以訪問環境對象
struct DetailView: View {
    @EnvironmentObject var settings: AppSettings
    
    var body: some View {
        Text("詳情頁")
            .font(.system(size: CGFloat(settings.fontSize)))
            .foregroundColor(settings.theme == "暗黑" ? .white : .black)
            .background(settings.theme == "暗黑" ? Color.black : Color.white)
    }
}

#Preview {
    ContentView()
        .environmentObject(AppSettings()) // 注入環境物件 不然preview會崩潰
}



```

## 加入圖片

![截圖 2025-03-31 下午2.21.59](https://hackmd.io/_uploads/H15iVhDp1g.png =400x)

![截圖 2025-03-31 下午2.19.42](https://hackmd.io/_uploads/ByFwE2v6kg.png =400x)

![截圖 2025-03-31 下午2.19.29](https://hackmd.io/_uploads/rkmTE2w61x.png =600x)

## function
**法一：在Struct內部**
```swift=
struct ContentView: View {
    
    var body: some View {
        VStack{ 
            
        }
    }
    
    // 在 Struct 內部宣告的函式
    func incrementCount() {
        //code
    }
}
```
**法二：用Extension**
```swift=
struct ContentView: View {
    
    var body: some View {
        VStack{ 
            
        }
    }
}

// 使用 Extension 定義功能
extension ContentView {
    func incrementCount(currentCount: Int) -> Int {
        return currentCount + 1
    }
}
```
**法三：用新檔案**
![截圖 2025-03-31 下午2.38.05](https://hackmd.io/_uploads/SkDjO3vTkg.png)

## extension
**為 View 新增功能**
```swift=
import SwiftUI

// 為 View 新增擴展功能
extension View {
    func roundedBackground(color: Color = .blue, cornerRadius: CGFloat = 12) -> some View {
        self
            .padding()
            .background(color)
            .cornerRadius(cornerRadius)
    }
}

struct ContentView: View {
    var body: some View {
        VStack {
            Text("Hello, SwiftUI!")
                .roundedBackground(color: .green, cornerRadius: 20)
            
            Text("擴展功能好方便！")
                .roundedBackground()
        }
    }
}

```
**使用 Extension 分離邏輯**
```swift=
import SwiftUI

struct ContentView: View {
    @State private var count = 0
    
    var body: some View {
        VStack {
            Text("Count: \(count)")
            Button("增加") {
                count = count.incremented()
            }
        }
    }
}

// 使用 extension 分離邏輯
extension Int {
    func incremented() -> Int {
        return self + 1
    }
}
```
**使用 Extension 提取 View 元件**
```swift=
import SwiftUI

struct ContentView: View {
    var body: some View {
        VStack {
            CustomButton(title: "確認", action: {
                print("確認按鈕點擊")
            })
            CustomButton(title: "取消", action: {
                print("取消按鈕點擊")
            })
        }
    }
}

// 使用 Extension 提取重複元件
extension View {
    func CustomButton(title: String, action: @escaping () -> Void) -> some View {
        Button(action: action) {
            Text(title)
                .bold()
                .frame(width: 100, height: 40)
                .background(Color.blue)
                .foregroundColor(.white)
                .cornerRadius(10)
        }
    }
}
```
**為系統類別新增功能**
```swift=
import SwiftUI

// 擴展 String 型別
extension String {
    func toInt() -> Int? {
        return Int(self)
    }
}

struct ContentView: View {
    var body: some View {
        Text("123".toInt() != nil ? "轉換成功" : "轉換失敗")
    }
}
```
**使用 Protocol 和 Extension 結合**
```swift=
import SwiftUI

// 定義一個協定
protocol CustomizableView {
    func customText() -> String
}

// 提供預設實作
extension CustomizableView {
    func customText() -> String {
        return "這是預設文字"
    }
}

// 套用協定並使用預設實作
struct ContentView: View, CustomizableView {
    var body: some View {
        Text(customText())
    }
}
```

## closure
```swift=
import SwiftUI

struct ContentView: View {
    let numbers = [1, 2, 3, 4, 5]
    
    var squaredNumbers: [Int] {
        numbers.map { $0 * $0 }
    }
    
    var body: some View {
        VStack {
            ForEach(squaredNumbers, id: \.self) { number in
                Text("平方數：\(number)")
            }
        }
    }
}
```
![image](https://hackmd.io/_uploads/B1DuLQupye.png =300x)

## print在console的方法
**法一：在按鈕動作中使用 print()**
```swift=
struct ContentView: View {
    var body: some View {
        VStack {
            Text("歡迎來到 Chat App")
            Button("點我") {
                print("hi")
            }
            ParentView()
        }
    }
}
```
**法二：在 onAppear() 中使用 print()**
在畫面顯示時執行 print()
```swift=
struct ContentView: View {
    var body: some View {
        VStack {
            Text("歡迎來到 Chat App")
            ParentView()
        }
        .onAppear {
            print("hi")
        }
    }
}
```
**法三：使用 init() 中的 print()**
在視圖初始化時執行 print()
```swift=
struct ContentView: View {
    
    init() {
        print("hi")
    }
    
    var body: some View {
        VStack {
            Text("歡迎來到 Chat App")
            ParentView()
        }
    }
}
```