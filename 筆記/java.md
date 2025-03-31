---
title: java
tags: [Coding]

---

---
tags: Coding
---

# java


## 編譯器
IntelliJ IDEA
改檔名：在public class後面選取文字然後⇧+fn+f6
取代全部文字：先用尋找，之後選select All Occurrences
自動排版：command + option + L
![image](https://hackmd.io/_uploads/HkJ0lfnva.png)


## 主程式
```java=
public class Main {//Main 是檔案名稱
    public static void main(String[] args) {
        /*do something*/
    }
}
```
## 規則
```
第一個字母要大寫ex: System,String
call by reference（指標）: obj (class), string , int[]（陣列）
function會被稱作method
一種class就是一種物件(object)
```
### 例外
double除以零不會出錯 反而是以infinity方式存 但float與int會錯
```java=
double x = 10.0 / 0;//Infinity
```
## 掃描
```java=
Scanner keyboard = new Scanner(System.in);
String str = keboard.nextLine();//會讀一整行
System.out.println("hollo" + "hi");
```
```java=
nextBoolean()	//Reads a boolean value from the user
nextByte()	//Reads a byte value from the user
nextDouble()	//Reads a double value from the user
nextFloat()	//Reads a float value from the user
nextInt()	//Reads a int value from the user
nextLine()	//Reads a String value from the user（一整行）
//特別注意如果nextLine前面有nextInt之類的東西要先在讀一次nextLine()，類似getline跟cin的用法
next()	        //Reads a String value from the user（單一個string讀到空格就會結束）
nextLong()	//Reads a long value from the user
nextShort()
```
## 印出
```java=
System.out.print(name);
System.out.println(name);//ln是換行
System.out.printf("%s\n%.2f",name,num);//double也是用%f
```
## 字串處理
```java=
str = str.toLowerCase();//把大寫改成小寫
str = str.toUpperCase();//把大寫改成小寫
str = str.substring(0, 2);//取0~1的字串
str = str.substring(0, str.indexOf(" "));//取到空白就停(不包含空白)
```
## class
```java=
public static class Pokemon {

    String name;

    public Pokemon(String name){
        this.name = name;//要加name才會存進去
    }

}
```
```java=
public static void main(String[] args) {
    Pokemon a = new Pokemon("ss");
    System.out.println(a.name);
}

```
class 可以放在主程式裡面，但放在上面不能有public static
```java=
class k{
    int a;
    int b;
}
public class App {
    public static void main(String[] args) {
        k d=new k();
        d.a=0;
        System.out.println(d.a);
    }
}
```
放在裡面就要加public static
```java=
public class App {
    public static class k{
        int a;
        int b;
    }
    public static void main(String[] args) {
        k d=new k();
        d.a=0;
        System.out.println(d.a);
    }
}
```
像是List裡面還有class的話
可以在下面補上class
但記得所有class中只能有一個是public
```java=
public class SeasonData {
    private String season;
    private List<TeamRecord> teamRecord;
    private List<PlayoffTeam> playoffTeams;
}

class TeamRecord {
    private String team;
    private int wins;
    private int losses;
    private String league;
    private String division;
}

class PlayoffTeam {
    private String team;
    private List<Series> series;
}

class Series {
    private String opponent;
    private boolean advanced;

    // Getters and Setters
    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    public boolean isAdvanced() {
        return advanced;
    }

    public void setAdvanced(boolean advanced) {
        this.advanced = advanced;
    }
}
```
## 建構子
### overloading 多個建構子
一個物件可以有多個建構子
```java=
class a1{
    int v1;
    public a1(){
    }//建構子
    public a1(int value){
        this.v1=value;
    }//另一個建構子
}
public class Main {
    public static void main(String[] args) {
        a1 ff = new a1();
        a1 ss = new a1(2);//兩種呼叫方法
    }
}
```
### lambok自動建構子
maven
```xml=
<dependencies>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.24</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```
只要import完後 在最前面加上@builder就可以省去setter getter
@toString會回傳裡面所以有建構子
```java=
import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class Stadium {
    private final String team;
    private final String stadium;
    private final String seatingCapacity;
    private final String playoffFullRate;
    private final String worldSeriesFullRate;
}
```
@toString裡面長這樣
```java=
@Override
public String toString() {
    return "Stadium(team=" + team + ", stadium=" + stadium + 
           ", seatingCapacity=" + seatingCapacity + 
           ", playoffFullRate=" + playoffFullRate + 
           ", worldSeriesFullRate=" + worldSeriesFullRate + ")";
}

```
## static
https://www.cnblogs.com/dotgua/p/6354151.html
### 用法一
method共用變數
```java=
public class App {
    public static class k{
        static int a;
        int b;
    }
    public static void main(String[] args) {
        k d=new k();
        k e=new k();
        d.a=0;
        e.a=2;
        System.out.println(d.a);//2 有加static代表在那個method的值都是共用的
    }
}
```
### 用法二
不用new而直接用method的函式
```java=
public class App {

    public static void print(Object o){
        System.out.println(o);
    }

    public static void main(String[] args) {
        App.print("Hello world");//不用先new一個物件出來
    }
}
```
### 法三
用括號new一個
```java=
(new Myclass()).func();
```
## public
https://yubin551.gitbook.io/java-note/basic_java_programming/accessmodifier

## Pass-by-Reference vs. Pass-by-Value

int,char,boolean,String都是pass by Value，也就是會複製成新的一份。
而reference就像是指標一樣。
陣列,list,物件都是pass by reference，也就是會複製地址，所以複製過後還是引用一樣的東西。
```java=
class obj{
    int a=1;
}

public class Main {
    public static void change(String a,int b,char c,int[] arr,List<Integer> l,obj ob){
        a="bb";
        b=2;
        c='d';
        arr[0]=2;
        l.add(2);
        ob.a=2;

        System.out.println("String: "+a);
        System.out.println("int: "+b);
        System.out.println("char: "+c);
        System.out.println("array: "+Arrays.toString(arr));
        System.out.println("List "+l.toString());
        System.out.println("obj.a "+ob.a);

    }
    public static void main(String[] args) {
        String a="aa";
        int b=1;
        char c='c';

        int[] arr=new int[3];
        arr[0]=1;

        List<Integer> l=new ArrayList<>();
        l.add(1);

        obj ob=new obj();

        System.out.println("---改變前---");
        System.out.println("String: "+a);
        System.out.println("int: "+b);
        System.out.println("char: "+c);
        System.out.println("array: "+Arrays.toString(arr));
        System.out.println("List "+l.toString());
        System.out.println("obj.a "+ob.a);
        System.out.println("---函式中---");
        change(a,b,c,arr,l,ob);
        System.out.println("---改變後---");
        System.out.println("String: "+a);
        System.out.println("int: "+b);
        System.out.println("char: "+c);
        System.out.println("array: "+Arrays.toString(arr));
        System.out.println("List "+l.toString());
        System.out.println("obj.a "+ob.a);
    }
}
```
output:
```md=
---改變前---
String: aa
int: 1
char: c
array: [1, 0, 0]
List [1]
obj.a 1
---函式中---
String: bb
int: 2
char: d
array: [2, 0, 0]
List [1, 2]
obj.a 2
---改變後---
String: aa
int: 1
char: c
array: [2, 0, 0]
List [1, 2]
obj.a 2

```

## 陣列
```java=
int[] arr = new int[5];//實際卻有6格
Arrays.fill(arr,0);//全部填入0
System.out.print(Arrays.toString(arr));//印出所有陣列所有東西[0,0,0,0,0,0]
```
**可以直接被回傳**
```java=
class c(){
    public static int[] f(){
        int[] arr;
        /*code*/
        return arr;
    }
}
int[] a = c.f();
```
## random
Math版
```java=
Math.random();//[0,1)的範圍
int n=(int)(Math.random()*2);//抽出0或是1
```
Random版
```java
import java.util.*;
public class Main {
    public static void main(String[] args)
    {
        // create random object
        Random ran = new Random();
        System.out.println(ran.nextInt());//所有int的範圍包含正負中選一個
        System.out.println(ran.nextInt(10));//0~9選一個
        /*
        -703910087
        8
        */
    }
}
```
## boolean
```java=
boolean[] check=new boolean[n];
Arrays.fill(check, false);
```
## private
```java=
class aaa{
    private int a;
    public aaa(int a){
        this.a=a;
    }
}


public class Main {
    private String fname = "John";
    private int age = 24;

    public static void main(String[] args) {
        Main myObj = new Main();
        System.out.println(myObj.fname);
        System.out.println("Age: " + myObj.age);//因為都在Main裡面所以不會報錯

        aaa otherObj =  new aaa(10);
        System.out.println(otherObj.a);//aaa的a不在Main裡面所以會報錯
    }
}
```

```java=
class aaa{
    private int a;
}

class bb extends aaa{
    public  bb() {
        System.out.println("b");
        this.a = 5;
    }//就算用繼承的也沒辦法繼承爸爸的private，所以會報錯
}

public class Main {
    public static void main(String[] args) {
        bb myObj = new bb();
    }
}
```
## final
可以先宣告，之後再初始化。
只是要注意初始化只能一次。
```java=
private final PrimeChecker primeChecker;
```
宣告了 primeChecker 是一個 final 變數。
這意味著一旦 primeChecker 被賦值（即指向某個 PrimeChecker 物件），它就不能再被賦值為另一個物件。
public Prime(PrimeChecker primeChecker) {
    this.primeChecker = primeChecker; // 在建構函數中初始化
}
雖然 primeChecker 是 final 但還沒初始化過所以可以初始化一次。
## reference
```java=
class Car {
    String make;
    
    Car(String make) {
        this.make = make;
    }
}

public class Main {
    public static void main(String[] args) {
	Car car1 = new Car("Toyota");
	Car car2 = new Car("Toyota");
	boolean result = (car1 == car2); // false
}
```

## inheritance 繼承

```java=
class aaa{
    int a;
    public  aaa(){
        System.out.print("a ");
        this.a=10;
    }
    public aaa(int a){
        this.a=a;
    }
}

class bb extends aaa{
    public  bb() {
        System.out.print("b ");
    }
}

public class Main {
    public static void main(String[] args) {
        bb myObj = new bb();
        System.out.println(myObj.a);//印出a b 10
        //最後的10是爸爸初始化過後的結果，也會被繼承
    }
}
```
### @Override
要繼承才可以用@Override
```java=
public class test {
    public void sayHello(){
        System.out.println("hello");
    }
}

public class test2 extends test{
    @Override
    public void sayHello(){
        System.out.println("hello");
    }
    
    @Override//會報錯 連裡面的引數也要一樣 不然不知道是在指哪個sayHello
    public void sayHello(String name){
        System.out.println("hello"+name);
    }
}
```
例子：
```java=
class Car {
  String make;

  Car(String make) {
    this.make = make;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    return this.make.equals(((Car)obj).make);
  }
}
public class Main {
  public static void main(String[] args) {

    Car car1 = new Car("Toyota");
    Car car2 = new Car("Toyota");
    boolean result1 = car1.equals(car2);
    System.out.println("car1 is equal to car2: " + result1);

    Car car3 = new Car("Ford");
    boolean result2 = car1.equals(car3);
    System.out.println("car1 is equal to car3: " + result2);
  }
}
/*output:
car1 is equal to car2: true
car1 is equal to car3: false 
*/
```
**遇到final**
```java=
class Animal {
    public final void spark(){//保護method不被子類覆寫
        System.out.println("spark");
    }
}

class Dog extends Animal {
    @Override //可寫可不寫，寫了只是提醒會覆寫到，通常都會寫
    public void spark (){//會報錯因為父類的spark被final保護住
        System.out.println("ss");
    }
}
```

## super
錯誤示範
```java==
class Animal {
    Animal(String type) {
        System.out.println("Creating an animal of type " + type);
    }
}

class Dog extends Animal {
    Dog() {
        Animal("Mammal"); //這裡要用super才對，因為不能在子類裡面直接打父類的名子
        System.out.println("Creating a dog...");
    }
}
```
正確示範
```java==
class Animal {
    Animal(String type) {
        System.out.println("Creating an animal of type " + type);
    }
}

class Dog extends Animal {
    Dog() {
        super("Mammal"); // Calling the constructor of the superclass
        System.out.println("Creating a dog...");
    }
}
```


## Polymorphism多型

```java=

class aa{
    private int a;
}

class bb extends aa{
    private int b;
    public bb(int b){
        this.b=b;
    }

    public int getB() {
        return b;
    }
}

public class Main {
    public static void main(String[] args) {
        aa men=new bb(1);
        System.out.println(men.getB());//會報錯
        System.out.println(((bb)men).getB());//用這個就可以轉型回去不會報錯
    }
}
```
但複寫的話就可以不用在前面加上轉型
```java=
class aa{
    private int a;
    public int getB() {
        return 2;
    }
}

class bb extends aa{
    private int b;
    public bb(int b){
        this.b=b;
    }
    
    @Override
    public int getB() {
        return b;
    }
}

public class test2 {
    public static void main(String[] args) {
        aa men=new bb(1);
        System.out.println(men.getB());//現在不會報錯了
        System.out.println(((bb)men).getB());//用這個就可以轉型回去不會報錯
    }
}
```

## instanceof
判斷物件是什麼class，物件放左、class放右
如果obj（左）與class（右）互為父子關係可以比較，但不在同一個父類或是只是繼承同樣的父類就會報錯，簡單來說繼承要在同一線上才可以比較。要obj有包含或等於class的繼承範圍才會是true，如果是小於就會false

**沒有同一個父類**
```java=
class aa{
}
class bb{
}
public class Main {
    public static void main(String[] args) {
        aa A=new aa();
        bb B=new bb();

        System.out.println(A instanceof bb);//會報錯
        System.out.println(B instanceof aa);//會報錯
    }
}
```


```java=
class aa{
}
class bb extends aa{
}
class cc extends aa{
}
public class Main {
    public static void main(String[] args) {
        aa A=new aa();
        bb B=new bb();//extend aa
        cc C=new cc();//extend aa

        System.out.println(A instanceof bb);//false 不會報錯是因為aa被bb繼承
        System.out.println(B instanceof bb);//true
        System.out.println(A instanceof aa);//true
        System.out.println(B instanceof aa);//true 是true是因為aa被bb繼承
        System.out.println(C instanceof bb);//會報錯，不會因為父類都一樣就可以比，除非宣告時的class一樣
    }
}
```
```java=
class aa{
}
class bb extends aa{
}
class cc extends aa{
}
class dd extends cc{
}
public class Main {
    public static void main(String[] args) {
        aa B=new bb();//extend aa。只要前面宣告aa的部分一樣，就可以比較
        aa C=new cc();//extned aa
        aa D=new dd();//extend cc
        System.out.println(B instanceof bb);//true
        System.out.println(C instanceof bb);//false
        System.out.println(D instanceof cc);//true看右邊new dd()包含的範圍
    }
}
```
## equal
可以用在字串比較
```java=
String myStr1 = "Hello";
String myStr2 = "Hello";
String myStr3 = "Another String";
System.out.println(myStr1.equals(myStr2)); // Returns true because they are equal
System.out.println(myStr1.equals(myStr3)); // false
```

## package
![image](https://hackmd.io/_uploads/HyxKwR9Bp.png)

**Main**
```java=
import aa.test1;//只引入test1.java
import aa.*;//引入aa這個package裡面所有
public class Main {
    public static void main(String[] args) {
        System.out.println(test1.hello());
    }
}
```

**aa**
**test1**
```java=
package aa;//一定要打 代表放在aa這個package下面

public class test1 {
    public static String hello(){
        return "hello";
    }
}
```
**test2**
```java=
package aa;
public class test2 {
}
```
## public protect default private

**public:** 只要有導入或在同一封包內都可以用
**protect:** 如果是導入的封包，就必須要用繼承的才可以用
**default(不用打字):** 一定要在同一個封包內才可以用
**private:** 只能在同一個class內才可以用

| 存取修飾 | 同一class | 同一package | 不同package子class(繼承) | 不同package的class(沒繼承)|
| -------- | -------- | -------- | -------- | -------- |
|private| OK ||||||	
|default|OK|OK||||	
|protected|OK|OK|OK|||	
|public	|OK|OK|OK|OK||

![image](https://hackmd.io/_uploads/r1A0YJiHp.png)

**public**

<img src="https://hackmd.io/_uploads/rkxL8JiBp.png" style="zoom:30%" />

**protect**

<img src="https://hackmd.io/_uploads/B1xIUJiHa.png" style="zoom:30%" />

**default(不用打字)**

<img src="https://hackmd.io/_uploads/ryZLI1jrT.png" style="zoom:30%" />

**private**

<img src="https://hackmd.io/_uploads/rkW88kjBa.png" style="zoom:30%" />

## abstract
就是不能直接被new一個物件，一定要用繼承得的。
**錯誤**
```java=
abstract class Animal{
    int height,weight;
    void move(){
        System.out.println("move...move...");
    }
}
class Test {
    public static void main(String[] args) {

        Animal ani = new Animal();//會報錯

    }// end of main(String[])
}// end of class Test
```
**正確**
```java=
abstract class Animal{
    int height,weight;
    void move(){
        System.out.println("move...move...");
    }
}
class Dog extends Animal{

}

public class Test {
    public static void main(String[] args) {

        Animal ani = new dog();

    }// end of main(String[])
}// end of class Test
```
如果有抽象method被繼承的時候一定要覆寫。
```java=
abstract class Animal {
    abstract void eat();
}
class Dog extends Animal{
    /*void eat(){
        System.out.println("啃骨頭...");
    }*/ //會報錯
}
```

## interface（介面）implements（實作）
跟abstract非常像，但是裡面只能放沒有body的method。
有body的method
```java=
public void animalSound() {
    System.out.println("The pig says: wee wee");
}
```
沒有body的method
```java=
public void animalSound();
```

```java=
// Interface
interface Animal {
  public void animalSound(); //裡面不能打東西
  public void sleep();
}

// Pig "implements" the Animal interface
class Pig implements Animal {//要用implements實作
  public void animalSound() {//一定要複寫
    System.out.println("The pig says: wee wee");
  }
  public void sleep() {
    System.out.println("Zzz");
  }
}

class Main {
  public static void main(String[] args) {
    Pig myPig = new Pig();  // Create a Pig object
    myPig.animalSound();
    myPig.sleep();
  }
}
```

## enum
https://www.tpisoftware.com/tpu/articleDetails/1432
變數變成有點像是字串的用法
```java=
public class Main {
    enum Level {
        LOW,
        MEDIUM,
        HIGH
    }

    public static void main(String[] args) {
        Level myVar = Level.MEDIUM;
        System.out.println(myVar);//印出MEDIUM(不是string)
        System.out.println(myVar.equals("MEDIUM"));//false，但實際上不是字串
        System.out.println(myVar.name());//印出MEDIUM(是string)
        System.out.println(myVar.toString());//印出MEDIUM(是string)
        System.out.println(myVar.ordinal());//印出1(順序)

        //字串轉enum
        String s="LOW";
        Level L= Level.valueOf(s);
        System.out.println(L);//LOW

        //用索引值的方法
        Level[] levels = Level.values(); //陣列長度為3，陣列內容為[level.LOW, level.MEDIUM, level.HIGH]
        System.out.println(Arrays.toString(levels));
        System.out.println(Level.values()[1]);
        /*
        [LOW, MEDIUM, HIGH]
        MEDIUM
        */
    }
}
```
switch與列舉
```java=
enum Level {
  LOW,
  MEDIUM,
  HIGH
}

public class Main {
  public static void main(String[] args) {
    Level myVar = Level.MEDIUM;

    switch(myVar) {
      case LOW:
        System.out.println("Low level");
        break;
      case MEDIUM:
         System.out.println("Medium level");
        break;
      case HIGH:
        System.out.println("High level");
        break;
    }
     
    //Iterating Over Enums
    for(Level lev:Level.values()){
        System.out.println(lev);//LOW MEDIUM HIGH
    }
  }
}
```
自訂enum
```java=
public class Main {
    enum Day {
        SUNDAY(4),//自訂index值 引用第9行 private Day(int value)的函式
        MONDAY(5),
        SATURDAY(11);//前幾行結尾都是逗號 這一行必須加分號

        private int value;

        //建構子預設為private，可寫可不寫，但絕對不能用public
        private Day(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }

    public static void main(String[] args) {
        for(Day day: Day.values()) {
            System.out.println("name:" + day.name() + "\nvalue:" + day.getValue());
        }
    }
}
/*output:
name:SUNDAY
value:4
name:MONDAY
value:5
name:SATURDAY
value:11
*/
```

## 錯誤偵測


https://programming.guide/java/list-of-java-exceptions.html
裡面有關於exception詳細的種類

https://blog.csdn.net/luoweifu/article/details/10721543
https://yoziming.github.io/post/211201-agg-ja-16/

### 分類
![image](https://hackmd.io/_uploads/SJh74DjvT.png)
![image](https://hackmd.io/_uploads/BkOvz8iD6.png)

在Java中，程序執行中的異常分為Exception與Error，他們都繼承自Throwable

(語法錯誤跟邏輯錯誤那不叫異常)
#### Error

錯誤，JVM系統內部錯誤虛擬機無法解決的問題、資源耗盡等嚴重情況，比如:

* 無限迴圈產生堆疊溢位(Stack Overflow)
* 寫錯分配導致記憶體不足(Out-Of-Memory)，

解決方法就是把它寫對

#### Exception

例外，發生了出乎預料的事，又依"受不受檢"分成

1. **Checked Exception**: 又稱編譯時異常，通常在原始碼中必須顯式地catch並且處理，比如:

* IOException、讀取文件不存在
* ClassNotFoundException
* 這部分在compile time就會檢查

2. **Unchecked Exception**: 又稱RuntimeException，運行時異常，比如:

* NullPointerException，空指針訪問
* NumberFormatException，數字類型不合
* InputMismatchException，輸入數據不符合，例如scan int結果來了字串
* ArithmeticException，算法異常，例如把某數除以0
* 通常是透過撰寫相應程式以避免的邏輯錯誤, 可以根據當下的情境來判斷是不是要catch

### try catch
用try catch有成功抓取到錯誤的話，就可以防止程式在出錯誤的時候被迫中斷。catch想抓的異常類型，如果有子父類關係，必須從小到大，否則報錯
下面程式碼中在try裡面如果發生錯誤，catch就會檢查裡面的Exception有沒有抓取到錯誤的訊息放到e，有的話就會執行裡面的程式。最後面的finally不是必須的，功能是無論如何不管try有沒有裡面錯誤都會執行，假設myNumbers[10]改成myNumbers[1]也會執行。
```java=
public class Main {
    public static void main(String[ ] args) {
        try {
            int[] myNumbers = {1, 2, 3};
            System.out.println(myNumbers[10]);
        } catch (Exception e) {
            System.out.println("Something went wrong.");
            System.out.println(e.toString());
            System.out.println(e.getMessage());//可以得到錯誤資訊
        } finally {
            System.out.println("finish");
        }
    }
}
/*output:
Something went wrong.
java.lang.ArrayIndexOutOfBoundsException: Index 10 out of bounds for length 3
finish
*/
```
Exception包含每一種錯誤，但其實有很多種錯誤例如：ArithmeticException、ArrayIndexOutOfBoundsException等等等
Exception會抓取最先發生錯誤的那個訊息
```java=
public class Main {
    public static void main(String[ ] args) {
        try {
            int[] myNumbers = {1, 2, 3};
            System.out.println(myNumbers[10]);//ArrayIndexOutOfBoundsException
            int a=1/0;//ArithmeticException
        } catch (ArithmeticException e){
            System.out.println(e.toString());
            System.out.println(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.toString());
            System.out.println(e.getMessage());
        } finally {
            System.out.println("finish");
        }
    }
}
/*output:
java.lang.ArrayIndexOutOfBoundsException: Index 10 out of bounds for length 3
Index 10 out of bounds for length 3
finish 
*/
```
第二個錯誤在第一個錯誤之後發生，是抓不到的，而且沒抓到會直接終止程式
```java=
public class Main {
    public static void main(String[ ] args) {
        try {
            int[] myNumbers = {1, 2, 3};
            System.out.println(myNumbers[10]);//ArrayIndexOutOfBoundsException
            int a=1/0;//ArithmeticException
        } catch (ArithmeticException e){
            System.out.println(e.toString());
            System.out.println(e.getMessage());
        } finally {
            System.out.println("finish");
        }
        System.out.println("ok");
    }
}
```
直接報錯給你看，而且還是報第一個錯
![image](https://hackmd.io/_uploads/B1YoR7sDa.png)

### throw throws
主動提出報錯
throw:通常跟new一起聲明在方法體內
```java=
public static void main(String[] args) {
    throw new NumberFormatException();//後面還是要有()
}
```
輸出
![image](https://hackmd.io/_uploads/BJz3P8oDa.png)
```java=
public static void main(String[] args) {
    throw new Exception();//會報錯，只有Exception不能直接打
}
```
例子：
```java=
public static void main(String[] args) {
    int a=1;
    try {
        if(a==1){
            throw new NumberFormatException("this is throw message");//括號()內可以放錯誤訊息
        }
    }catch (NumberFormatException e){
        System.out.println("oh no");
        System.out.println(e.getMesage());//取得錯誤訊息(this is throw massage)
    }
}
/*output:
oh no
this is throw massage
*/
```
在method中要用throw就一定要先用throws加在method後面
```java=
public static void function() throws Exception{
    String s = "abc";
    throw new Exception();
    //System.out.println(Double.parseDouble(s));
}
```
也可以有多個異常聲明
```java=
public static void function() throws Exception1,Exception2{} 
```
可以丟出來給別人接
```java=
public class Triangle {
    public static void main(String[] args) {
        try {
            Triangle.func();
            System.out.println("ok");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static String func() throws Exception {
            throw new Exception("nono");
    }
}
//java.lang.Exception: nono
```
throws範例：
也可以自訂(customize)一個例外
```java=
class EX extends Exception{
    public EX(){};
    public EX(String msg){
        super(msg);
        System.out.println("this is Customize");
    }
}
public class Main {
    public static void function() throws EX{//異常不是變數所以前面可以打void
        throw new EX("ok");
    }
    public static void main(String[] args) {
        int a=1;
        try {
            function();
        }catch (EX e){
            System.out.println(e.getMessage());
        }
    }
}
/*output:
this is Customize
ok
*/
```
### assert
assert是debug用的
檢查變數有沒有在範圍
例如下面程式 只要bmi超過會報錯
```java=
public double bmi() {
    double bmiValue = weight / (height * height);
    assert bmiValue >= 10 && bmiValue <= 50 : "BMI value must be between 10 and 50."; // 假設的合理範圍
    return bmiValue;
}
```
![image](https://hackmd.io/_uploads/rJQNxE-C0.png)
但要記得先去環境設定-ea要觸發assert
![image](https://hackmd.io/_uploads/Byl-ZEbCC.png)
![image](https://hackmd.io/_uploads/r1xEWN-RR.png)
### logger
**設定**
在pom.xml中加入
```xml
<dependencies>
    <!-- SLF4J API -->
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-api</artifactId>
        <version>2.0.7</version>
    </dependency>

    <!-- Logback Classic (實現 SLF4J API) -->
    <dependency>
        <groupId>ch.qos.logback</groupId>
        <artifactId>logback-classic</artifactId>
        <version>1.4.11</version>
    </dependency>
</dependencies>

```
**用法**
會產生報錯但就是一個日誌 就算是logger.warnig也不會停止程式
並且可以另外把錯誤都存到錯誤日誌
```java=
package demo;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Example {
    private static final Logger logger = Logger.getLogger(Example.class.getName());

    public static void main(String[] args) {
        try {
            // 設定 FileHandler，將日誌寫入檔案 "app.log"
            FileHandler fileHandler = new FileHandler("app.log", true); // true 表示追加到文件中
            fileHandler.setFormatter(new SimpleFormatter()); // 設定格式為簡單格式
            logger.addHandler(fileHandler);

            logger.info("這是一條 INFO 等級的日誌訊息");
            logger.warning("這是一條 WARNING 等級的日誌訊息");

            // 故意產生一個錯誤
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            logger.log(Level.SEVERE, "發生了算術異常: " + e.getMessage(), e);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "無法創建 FileHandler: " + e.getMessage(), e);
        }
    }
}
```
![image](https://hackmd.io/_uploads/HJ6lGBWAR.png)
![image](https://hackmd.io/_uploads/HJllmGr-RC.png)


## switch
```java=
switch(expression) {
  case x:
    // code block
    break;
  case y:
    // code block
    break;
  default:
    // code block
}
```
## ArrayList
https://ithelp.ithome.com.tw/articles/10234424
### 在main中要new但在其他類不用new的原因
一般來說要用List要用new初始化
```java=
import java.util.List;
import java.util.ArrayList;

public class Example {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(); // 創建一個 ArrayList 實例
        list.add("Alice");
        list.add("Bob");
        System.out.println(list);
    }
}
```
但class中不用 是因為會用建構子初始化 或是用setter初始化
```java=
import java.util.List;

public class Example {
    private List<String> list;// 只聲明變量，但沒有立刻 new
    
    // 使用建構子來注入
    public Example(List<String> list) {
        this.list = list;
    }
    
    public void initializeList() {
        list = new ArrayList<>(); // 在方法中延遲初始化
    }

    // 或者使用 setter 方法
    public void setList(List<String> list) {
        this.list = list;
    }
}

```
### HashMap
就是可以自訂索引值(key)與其值(value)的型態。
但裡面是無序的
也不能用value去找key，因為一個value可以對應到多個key
遍歷要用特別方法

```java=
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        // 如果要設定int要用Integer
        HashMap<String, Integer> City = new HashMap<String, Integer>();

        // Add keys and values (Country, City)
        City.put("England", 10);
        City.put("Germany", 20);
        
        //找key有沒有存在  
        System.out.println(City.containsKey("England"));
        //true
        
        //找value有沒有存在
        System.out.println(City.containsValue(10));
        //true
        
        System.out.println(City);
        //{England=10, Germany=20}
        
        System.out.println("the key of England is: "+City.get("England"));//索引
        //the key of England is: 10
        
        System.out.println("keyset: "+City.keySet());
        //keyset: [England, Germany]
        
        for(String s:City.keySet())System.out.print(s+" ");
        System.out.println();
        //England Germany 

        System.out.println("values: "+City.values());
        //values: [10, 20]
        
        for(int s:City.values())System.out.print(s+" ");
        System.out.println();
        //10 20 

        City.remove("England");//移除
        System.out.println("the key of England is: "+City.get("England"));
        //the key of England is: null

        City.clear();//清空
        System.out.println("the key of Germany is: "+City.get("Germany"));
        //the key of Germany is: null
    }
}
```
#### 遍歷
```java=
// 如果要設定int要用Integer
HashMap<String, Integer> City = new HashMap<String, Integer>();

// Add keys and values (Country, City)
City.put("England", 10);
City.put("Germany", 20);

/*錯誤範例，不能用直觀去跑
for(HashMap<String,Integer> i:City{
    System.out.println(i.getKey()+" "+i.getValue());
}
*/

for(Map.Entry<String,Integer> i:City.entrySet()){
    System.out.println(i.getKey()+" "+i.getValue());
}
/*
England 10
Germany 20
*/

//自動判斷型別
for(var i:City.entrySet()){
    System.out.println(i.getKey()+" "+i.getValue());
}
/*
England 10
Germany 20
*/
```
就算用物件method去存也會改到原本的東西
```java=
class Student {
    private HashMap<String,Integer> grades;
    public void setGrades(HashMap<String, Integer> grades) {
        this.grades = grades;
        this.grades.put("a",1);
    }
}

public class Main {
    public static void main(String  [] args){
        Student stu=new Student();
        HashMap<String, Integer> grades = new HashMap<String,Integer>();
        stu.setGrades(grades);//傳入的是grades的地址
        System.out.println(grades);
        /*output:
        {a=1}
        */
    }
}
```

## clone
shallow clone複製reference只會複製到地址
deep clone是完完全全複製成另一個獨立的東西

Person1跟Person2都是Person物件
```java=
public class Person{
    String name;
    int age;
    Person friend;
}
```
用等於拷貝(Person1=Person2)
![截圖 2023-12-30 下午5.03.36](https://hackmd.io/_uploads/SJ8X6IaPT.png)

shallow clone
![截圖 2023-12-30 下午5.11.50](https://hackmd.io/_uploads/r1Hb1Ppvp.png)

deep clone
![截圖 2023-12-30 下午5.03.09](https://hackmd.io/_uploads/ry8XpUawT.png)

### shallow clone
```java=
class Test {
    int x;
}
class Test2 implements Cloneable {//這邊的implements也怪怪的
    int a;
    Test c = new Test();

    public Object clone() throws CloneNotSupportedException {
        //Object是反回的東西
        //沒加throws的話會報錯 很奇怪的寫法不要管為什麼
        return super.clone();
    }
}

// Driver class
public class Main {
    public static void main(String args[]) throws CloneNotSupportedException {
        Test2 t1 = new Test2();

        t1.a = 10;
        t1.c.x = 30;

        System.out.println("t1: (int)" + t1.a + " (obj)" + t1.c.x + " ");
        Test2 t2 = (Test2) t1.clone();

        t2.a = 100;
        t2.c.x = 300;

        System.out.println("after");
        System.out.println("t1: (int)" + t1.a + " (obj)" + t1.c.x);
        System.out.println("t2: (int)" + t2.a + " (obj)" + t2.c.x);
    }
}

```
### deep clone
```java=
class Test {
    int x;
}

class Test2 implements Cloneable {
    int a;

    Test c = new Test();

    public Object clone() throws CloneNotSupportedException {
        Test2 t = (Test2) super.clone();
        t.c = new Test();//新配置一塊物件 寫的話deep clone會失敗
        t.c.x = c.x;
        return t;
    }
}

public class Main {
    public static void main(String args[])
            throws CloneNotSupportedException {
        Test2 t1 = new Test2();
        t1.a = 10;
        t1.c.x = 30;

        System.out.println("t1: (int)" + t1.a + " (obj)" + t1.c.x + " ");

        Test2 t3 = (Test2) t1.clone();

        t3.a = 100;
        t3.c.x = 300;

        System.out.println("after");
        System.out.println("t1: (int)" + t1.a + " (obj)" + t1.c.x);
        System.out.println("t2: (int)" + t3.a + " (obj)" + t3.c.x);
    }
}
```
## json讀取
json的格式大概長這樣
如果同一物件多種宣告就用\{ \}
如果是串列就用\[ \]
```json=
{
  "season":"2023",
  "teamRecord":[
    {
      "team": "Baltimore Orioles",
      "wins": 101,
      "losses": 61,
      "league": "AL",
      "division": "East"
    },
    {
      "team": "Tampa Bay Rays",
      "wins": 99,
      "losses": 63,
      "league": "AL",
      "division": "East"
    }
  ],
  "playoffTeams":[
    {
      "team": "Texas Rangers",
      "series": [
        {
          "opponent": "Tampa Bay Rays",
          "advanced": true
        },
        {
          "opponent": "Baltimore Orioles",
          "advanced": true
        }
      ]
    },
    {
      "team": "Tampa Bay Rays",
      "series": [
        {
          "opponent": "Texas Rangers",
          "advanced": false
        }
      ]
    }
  ]
}
```
class中 用jackson的話一定要用private搭配set get
```java=
package org.example;

import java.util.List;

public class SeasonData {
    private String season;
    private List<PlayoffTeam> playoffTeams;
    private List<TeamRecord> teamRecord;
    // Getters and Setters
    public String getSeason() {
        return season;
    }
    public void setSeason(String season) {
        this.season = season;
    }
    public List<TeamRecord> getTeamRecord() {
        return teamRecord;
    }
    public void setTeamRecord(List<TeamRecord> teamRecord) {
        this.teamRecord = teamRecord;
    }
    public List<PlayoffTeam> getPlayoffTeams() {
        return playoffTeams;
    }
    public void setPlayoffTeams(List<PlayoffTeam> playoffTeams) {
        this.playoffTeams = playoffTeams;
    }
}

class TeamRecord {
    private String team;
    private int wins;
    private int losses;
    private String league;
    private String division;
    // Getters and Setters
    public String getTeam() {
        return team;
    }
    public void setTeam(String team) {
        this.team = team;
    }
    public int getWins() {
        return wins;
    }
    public void setWins(int wins) {
        this.wins = wins;
    }
    public int getLosses() {
        return losses;
    }
    public void setLosses(int losses) {
        this.losses = losses;
    }
    public String getLeague() {
        return league;
    }
    public void setLeague(String league) {
        this.league = league;
    }
    public String getDivision() {
        return division;
    }
    public void setDivision(String division) {
        this.division = division;
    }
}

class PlayoffTeam {
    private String team;
    private List<Series> series;
    // Getters and Setters
    public String getTeam() {
        return team;
    }
    public void setTeam(String team) {
        this.team = team;
    }
    public List<Series> getSeries() {
        return series;
    }
    public void setSeries(List<Series> series) {
        this.series = series;
    }
}

class Series {
    private String opponent;
    private boolean advanced;
    // Getters and Setters
    public String getOpponent() {
        return opponent;
    }
    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }
    public boolean isAdvanced() {
        return advanced;
    }
    public void setAdvanced(boolean advanced) {
        this.advanced = advanced;
    }
}

```
用jackson函式庫去讀取
ObjectMapper是用來讀入的物件
```java=
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ObjectMapper objectMapper =new ObjectMapper();
        SeasonData Team;
        try{
            File jsonFile=new File("src/main/java/org/example/team2023battle.json");
            if(!jsonFile.exists()){
                throw new IOException("File not found: "+jsonFile.getAbsolutePath());
            }//因為new File不會偵測錯誤 所以要在這邊先檢查檔案存不存在 並且列出絕對路徑方便除錯
            Team=objectMapper.readValue(jsonFile,SeasonData.class);//讀取檔案
        }catch(Exception e){
            e.printStackTrace();
        };
    }
}
```
### Jackson 如何對應 JSON 和 Java 類屬性
1. **屬性名稱對應**
- 成員名稱匹配： Jackson 在反序列化時，首先會嘗試直接通過屬性名稱進行匹配。例如，若 JSON 中有屬性 "aa": 1，則 Jackson 會嘗試在目標類中找到名稱為 aa 的成員變數。
- 大小寫敏感：屬性名稱的對應是大小寫敏感的。例如，JSON 中是 "Aa"，但 Java 類中是 aa，Jackson 無法自動識別，需要使用額外的設定來匹配。
2. **Getter 和 Setter 方法的使用**
- Jackson 使用 Java Bean 的慣例來讀取屬性。這意味著如果類中的屬性是私有的 (private)，但提供了 public 的 getter 和 setter 方法，Jackson 也可以正確地進行映射。
- 例如，對於成員變數 private int aa;，Jackson 會使用 getAa() 和 setAa(int aa) 來讀寫該屬性。
- 所以class中一定要設定private
3. **@JsonProperty 註解**
如果 JSON 中的屬性名稱與 Java 類中的屬性名稱不一致，可以使用 @JsonProperty 註解來指示 Jackson 應該如何匹配。例如：
```java=
import com.fasterxml.jackson.annotation.JsonProperty;

class k {
    @JsonProperty("a")
    private int aa;
    @JsonProperty("b")
    private int bb;
    // Getter and Setter
    public int getAa() {
        return aa;
    }
    public void setAa(int aa) {
        this.aa = aa;
    }
    public int getBb() {
        return bb;
    }
    public void setBb(int bb) {
        this.bb = bb;
    }
}
```
在這種情況下，即使 JSON 中的屬性是 "a" 和 "b"，Jackson 也能正確地將其映射到 aa 和 bb 上。

## foreach
一般來說要遍歷串列裡面所有元素要這樣寫
```java=
for (TeamRecord team : seasonData.getTeamRecord()) {
    System.out.println("Team: " + team.getTeam() + ", Wins: " + team.getWins() + ", Losses: " + team.getLosses());
}
```
但Lambda 表達式在 Java 8 中的函數式程式設計特性提高可讀性。
```java=
seasonData.getTeamRecord().forEach(team -> {
    System.out.println("Team: " + team.getTeam() + ", Wins: " + team.getWins() + ", Losses: " + team.getLosses());
});
```
箭頭 -> 表示的是參數和程式碼塊之間的關係。在 Lambda 表達式中，箭頭左邊是參數（在這裡是 team），右邊是用來操作該參數的程式碼。

## sort
### List
```java=
class TeamRecord {
    private String team;
    private int wins;
    private String league;
    private String division;
}
```
```java=
obj.sort(new Comparator<TeamRecord>() {
    @Override
    public int compare(TeamRecord o1, TeamRecord o2) {
        if(o1.getLeague().equals(o2.getLeague()) && o1.getDivision().equals(o2.getDivision()) ){
            return o2.getWins()-o1.getWins();//當大於0時交換 大的會被換到前面
        }else if(o1.getLeague().equals(o2.getLeague())) {
            return divisnioCmp(o1.getDivision())-divisnioCmp(o2.getDivision());
        }else{
            return leagueCmp(o1.getLeague())-leagueCmp(o2.getLeague());
        }
    }
    public int divisnioCmp(String a){
        if(a.equals("East"))return 1;
        else if(a.equals("Central"))return 2;
        else if(a.equals("West"))return 3;
        return 0;
    }
    public int leagueCmp(String a){
        if(a.equals("AL"))return 1;
        else if(a.equals("NL"))return 2;
        else return 0;
    }
});//這裡排序以防得到的數據是亂的
```
輸出：
```
[League] [win] [division] [team]

AL       94    East       NYY
AL       92    Central    CLE
AL       86    Central    KC
AL       86    Central    DET
AL       88    West       HOU
AL       85    West       SEA
NL       95    East       PHI
NL       88    East       ATL
NL       62    East       MIA
NL       93    Central    MIL
NL       83    Central    CHC
NL       98    West       LAD
NL       93    West       SD
```

## code review
### 法一
maven設定中 要加上build那一大包 注意ruleset的位置要加./
```xml=
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>untitled1</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>19</maven.compiler.source>
        <maven.compiler.target>19</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>
    <!--這一大包-->
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-pmd-plugin</artifactId>
                <version>3.25.0</version>
                <configuration>
                    <rulesets>
                        <!-- 指定使用的 PMD 規則集 -->
                        <ruleset>./src/main/resources/pmd/ruleset.xml</ruleset>
                    </rulesets>
                </configuration>

                <executions>
                    <execution>
                        <goals>
                            <!-- 執行 PMD 的 check 工作 -->
                            <goal>check</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
    <!--到這裡-->
</project>
```
![截圖 2024-10-21 晚上7.25.28](https://hackmd.io/_uploads/HJtA5nQgke.png)
接著到resources創建pmd資料夾 裡面再創兩個檔案

**ruleset.xml 自訂的規則集**
```xml=
<?xml version="1.0" encoding="UTF-8"?>

<ruleset name="Custom Rules"
         xmlns="http://pmd.sourceforge.net/ruleset/2.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://pmd.sourceforge.net/ruleset/2.0.0 ./ruleset_2_0_0.xsd">
    <description>Every Java Rule in PMD</description>

    <!-- the rules to be checked -->
    <rule ref="category/java/bestpractices.xml">
        <exclude name="SystemPrintln"/>
        <exclude name="AvoidReassigningParameters"/>
    </rule>
    <rule ref="category/java/codestyle.xml" />
    <rule ref="category/java/design.xml" />
    <rule ref="category/java/documentation.xml" />
    <rule ref="category/java/errorprone.xml" />
    <rule ref="category/java/multithreading.xml" />
    <rule ref="category/java/performance.xml" />
    <rule ref="category/java/security.xml" />

    <!--  override the rules  -->
    <rule ref="category/java/documentation.xml/CommentSize">
        <properties>
            <property name="maxLines" value="6" />
            <property name="maxLineLength" value="80" />
        </properties>
    </rule>
</ruleset>
```

**ruleset_2_0_0.xsd 自己子載入xml** 
```xml=
<?xml version="1.0"?>
<xs:schema
        xmlns:xs="http://www.w3.org/2001/XMLSchema"
        xmlns="http://pmd.sourceforge.net/ruleset/2.0.0"
        targetNamespace="http://pmd.sourceforge.net/ruleset/2.0.0"
        elementFormDefault="qualified">

    <xs:element name="ruleset">
        <xs:complexType>
            <xs:sequence>
                <xs:element name="description" type="xs:string" minOccurs="1" maxOccurs="1" />
                <xs:element name="exclude-pattern" type="xs:string" minOccurs="0" maxOccurs="unbounded" />
                <xs:element name="include-pattern" type="xs:string" minOccurs="0" maxOccurs="unbounded" />
                <xs:element name="rule" type="rule" minOccurs="1" maxOccurs="unbounded" />
            </xs:sequence>
            <xs:attribute name="name" type="xs:string" use="required" />
        </xs:complexType>
    </xs:element>

    <xs:complexType name="rule">
        <xs:sequence>
            <xs:element name="description" type="xs:string" minOccurs="0" maxOccurs="1" />
            <xs:element name="priority" type="xs:int" default="5" minOccurs="0" maxOccurs="1"/>
            <xs:element name="properties" type="properties" minOccurs="0" maxOccurs="1" />
            <xs:element name="exclude" type="exclude" minOccurs="0" maxOccurs="unbounded" />
            <xs:element name="example" type="xs:string" minOccurs="0" maxOccurs="unbounded" />
        </xs:sequence>
        <xs:attribute name="language" type="xs:string" use="optional" />
        <xs:attribute name="minimumLanguageVersion" type="xs:string" use="optional" />
        <xs:attribute name="maximumLanguageVersion" type="xs:string" use="optional" />
        <xs:attribute name="name" type="xs:ID" use="optional" />
        <xs:attribute name="since" type="xs:string" use="optional" />
        <xs:attribute name="ref" type="xs:string" use="optional" />
        <xs:attribute name="message" type="xs:string" use="optional" />
        <xs:attribute name="externalInfoUrl" type="xs:string" use="optional" />
        <xs:attribute name="class" type="xs:NMTOKEN" use="optional" />
        <xs:attribute name="dfa" type="xs:boolean" use="optional" />  <!-- rule uses dataflow analysis -->
        <xs:attribute name="typeResolution" type="xs:boolean" default="false" use="optional" />
        <xs:attribute name="deprecated" type="xs:boolean" default="false" use="optional" />
    </xs:complexType>

    <xs:complexType name="properties">
        <xs:sequence>
            <xs:element name="property" type="property" minOccurs="1" maxOccurs="unbounded" />
        </xs:sequence>
    </xs:complexType>

    <xs:complexType name="property">
        <xs:sequence>
            <xs:element name="value" type="xs:string" minOccurs="0" maxOccurs="1" />
        </xs:sequence>
        <xs:attribute name="name" type="xs:NMTOKEN" use="required" />
        <xs:attribute name="value" type="xs:string" use="optional" />
        <xs:attribute name="description" type="xs:string" use="optional" />
        <xs:attribute name="type" type="xs:string" use="optional" />
        <xs:attribute name="delimiter" type="xs:string" use="optional" />
        <xs:attribute name="min" type="xs:string" use="optional" />
        <xs:attribute name="max" type="xs:string" use="optional" />
    </xs:complexType>

    <xs:complexType name="exclude">
        <xs:attribute name="name" type="xs:NMTOKEN" use="required" />
    </xs:complexType>

</xs:schema>
```

之後按右邊verify之後

![image](https://hackmd.io/_uploads/HJyJp2Xg1l.png)

會在target的report出現pmd.html
![截圖 2024-10-21 晚上7.37.28](https://hackmd.io/_uploads/H1MQThmeJe.png)

會報錯也不要關他(應該啦)
![image](https://hackmd.io/_uploads/r1RahnQeye.png)

### 法二
![image](https://hackmd.io/_uploads/SJZyCh7xye.png)
去下載pmd插件
![image](https://hackmd.io/_uploads/SyHg03mlkl.png)
可以在這邊加ruleset自訂規則
![截圖 2024-10-21 晚上7.41.53](https://hackmd.io/_uploads/H1I7A37xJx.png)
選擇檔案位置
![image](https://hackmd.io/_uploads/B1-p02Qg1e.png)

ruleset.xml
```xml=
<?xml version="1.0" encoding="UTF-8"?>

<ruleset name="Custom Rules"
         xmlns="http://pmd.sourceforge.net/ruleset/2.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://pmd.sourceforge.net/ruleset/2.0.0 ./ruleset_2_0_0.xsd">
    <description>Every Java Rule in PMD</description>

    <!-- the rules to be checked -->
    <rule ref="category/java/bestpractices.xml">
        <exclude name="SystemPrintln"/>
        <exclude name="AvoidReassigningParameters"/>
    </rule>
    <rule ref="category/java/codestyle.xml" />
    <rule ref="category/java/design.xml" />
    <rule ref="category/java/documentation.xml" />
    <rule ref="category/java/errorprone.xml" />
    <rule ref="category/java/multithreading.xml" />
    <rule ref="category/java/performance.xml" />
    <rule ref="category/java/security.xml" />

    <!--  override the rules  -->
    <rule ref="category/java/documentation.xml/CommentSize">
        <properties>
            <property name="maxLines" value="6" />
            <property name="maxLineLength" value="80" />
        </properties>
    </rule>
</ruleset>
```
選這個就可以執行
![image](https://hackmd.io/_uploads/rylK1TXgye.png)

## unit test
用junit5
在要測試的程式中按右鍵
![image](https://hackmd.io/_uploads/ry3IRs8xJx.png)
![截圖 2024-10-24 凌晨1.12.07](https://hackmd.io/_uploads/Hk5K0iLekg.png)

選擇要測試的函式 之後就會多一個test的檔案
![截圖 2024-10-24 凌晨1.12.16](https://hackmd.io/_uploads/Sy9KRi8xyl.png)

Calculator.java
```java=
package demo;

public class Calculator {

    public int add(int a, int b) {
        return a+b;
    }

    public int multiply(int a, int b) {
        return a*b;
    }

    public double divide(int a, int b) throws Exception {
        if(b==0)throw new Exception("divided by zero");
        return (double) a/b;
    }

}

```

CalculatorTest.java
```java=
record CalculatorTest() {

    //test可以有無限多個 其實就是分成很多小區塊去測試函式功能
    //這些區塊都可以隨意增減 想加就加想刪就刪
    @Test
    @DisplayName("Calculator ok 的啦")//可以把名子test_plus改成Calculator ok 的啦
    void test_plus() {
        Calculator cal = new Calculator();
        int expected = 2;
        int real = cal.add(1,1);
        assertEquals(expected, real);
    }

    @Test
    void test_assertAll() {
        Calculator cal = new Calculator();

        //assertEquals(3, cal.add(1,1));直接執行的話會卡在這邊
        //assertEquals(0, cal.add(1, -1));這個就不會執行到
        //要兩個都可以執行就要用assertAll
        assertAll("positive + positive",
                ()-> assertEquals(2, cal.add(1,1)),
                ()-> assertEquals(0, cal.add(1, -1))
        );
        assertAll("positive + negative",
                ()-> assertEquals(3, cal.add(1,1)),
                ()-> assertEquals(0, cal.add(1, -1))
        );
    }

    @org.junit.jupiter.api.Test //這東西與@test一樣
    @DisplayName("Calculator div OK 的啦")
    void test_div() throws Exception {
        Calculator cal = new Calculator();
        int expected = 7;
        double real = cal.divide(4,2);
        assertEquals(expected, real);
    }

    @Test
    public void testAddition() {
        Calculator calculator = new Calculator();
        int result = calculator.add(2, 2);
        assertEquals(5, result, () -> "計算結果錯誤: ");//可以自己加字
    }

    @Test
    void test_div2(){
        Calculator cal =new Calculator();
        Exception exception= assertThrows(Exception.class ,()->cal.divide(2,0));
        //預期他會拋出錯誤 所以沒拋出錯誤反而會出錯
    }

}
```
其他例子
TriangleTest.java
```java=
class TriangleTest {
    @Test
    void cal_triangle() {
        Triangle t = new Triangle();
        String expect="這是正三角形。";
        assertEquals(expect,t.cal_triangle(1,1,1));//可以比較物件 字串 整數 浮點數等等等
        //t.cal_triangle(1,1,1)會回傳字串
    }
}
```
PeopleTest.java
```java=
@Test
void test_bmi_height_change() {
    People jack = new People("Jack", 1.7, 80, 2000);
    //jack.setHeight(1.6);
    assertEquals(27.6, jack.bmi(),0.1);//0.1是誤差 可以有誤差的方法
}
```
