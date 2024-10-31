package demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;


record CalculatorTest() {

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

        //assertEquals(2, cal.add(1,1));直接執行的話會卡在這邊
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

    @org.junit.jupiter.api.Test
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

    @Test
    void test_div3() {
        Calculator cal = new Calculator();
        Exception exception = assertThrows(Exception.class, () -> cal.divide(2, 0));

        // 驗證異常消息
        assertEquals("divided by zero", exception.getMessage());
    }

    @Test
    void test_div4() {
        Calculator cal = new Calculator();
        Exception exception = assertThrows(Exception.class, () -> cal.divide(2, 0));
        assertEquals("divided by zero", exception.getMessage());
    }

}