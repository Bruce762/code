
package xdemo;
public class Triangle {

    String cal_triangle(int a,int b,int c){

        // 判斷是否為有效三角形
        if ((a + b > c) && (a + c > b)) {
            if (a == b && b == c) {
                return "這是正三角形。";
            } else if (a == b || b == c || a == c) {
                return "這是等腰三角形。";
            } else {
                // 判斷是否為直角三角形
                double max = Math.max(a, Math.max(b, c));
                if (Math.abs((max * max) - (a * a + b * b + c * c - max * max)) < 0.0001) {
                    return "這是直角三角形。";
                } else {
                    return "這是一般三角形。";
                }
            }
        } else {
            return "這不是一個有效的三角形。";
        }
    }

    public static void main(String[] args) {
        Triangle t=new Triangle();
        System.out.println(t.cal_triangle(1,1,1));
    }
}
