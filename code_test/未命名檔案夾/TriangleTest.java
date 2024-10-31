package xdemo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {
    @Test
    void cal_triangle() {
        Triangle t = new Triangle();
        String expect="這是正三角形。";
        assertEquals(expect,t.cal_triangle(1,1,1));
    }
}