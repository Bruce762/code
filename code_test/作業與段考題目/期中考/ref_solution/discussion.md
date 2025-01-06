

### Testing code == code?

這樣就測不出錯誤了

#### The source code

```java
package demo;

import java.util.Scanner;

public class price {    
    public double check(int a,int b,int c,int d){
        double total = 0;
        if(a==1){
            if(d==1){
                total=250;
            }else {
                if (b > 5) {
                    if (c == 1) {
                        total = 600 * 0.8;
                    } else {
                        total = 600;
                    }
                } else {
                    if (c == 1) {
                        total = 500 * 0.8;
                    } else {
                        total = 500;
                    }
                }
            }
        }else if(a==2){
            if(c==1){
                total=1000*0.8;
            }else{
                total=1000;
            }
        }
        return total;
    }
}
```

#### The testing code
```java
    @ParameterizedTest
    @CsvFileSource(resources = "/money.csv", numLinesToSkip = 1)
    void test1(int a,int b,int c,int d) {
        price p =new price();
        double total=p.check(a,b,c,d);
        double exp=0;
        if(a==1){
            if(d==1){
                exp=250;
            }else {
                if (b > 5) {
                    if (c == 1) {
                        exp = 600 * 0.8;
                    } else {
                        exp = 600;
                    }
                } else {
                    if (c == 1) {
                        exp = 500 * 0.8;
                    } else {
                        exp = 500;
                    }
                }
            }
        }else if(a==2){
            if(c==1){
                exp=1000*0.8;
            }else{
                exp=1000;
            }
        }
        assertEquals(exp,total);
    }
```    

#### The testing data
```
round day vip collect
1,	1,	1,	1,
1,	1,	1,	0,
1,	1,	0,	1,
1,	0,	1,	1,
2,	1,	1,	1,
2,	1,	1,	0,
2,	1,	0,	1,
2,	0,	1,	1,
```

### Testing the `out`?

```java
public class TicketTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        // 重定向標準輸出和錯誤輸出以便測試
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    public void testRegularTicketBasicPrice() {
        // 測試普通票基本價格 (500元)
        Ticket.ticketInfo("Regular", false, false, false);
        assertTrue(outContent.toString().contains("票價 : 500.0"));
    }
    ...

```
