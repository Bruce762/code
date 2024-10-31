package xdemo;

import org.junit.jupiter.api.DisplayName;
import xdemo.People;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PeopleTest {

    @Test
    void test_bmi_height_change() {
        People jack = new People("Jack", 1.7, 80, 2000);
        //jack.setHeight(1.6);
        assertEquals(27.6, jack.bmi(),0.1);//0.1是誤差 27.6不是準確值
    }
}