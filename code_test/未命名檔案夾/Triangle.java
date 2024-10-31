package demo;

import java.util.Scanner;

public class Triangle {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 輸入三角形三邊長
        System.out.print("請輸入第一邊長: ");
        double a = scanner.nextDouble();
        System.out.print("請輸入第二邊長: ");
        double b = scanner.nextDouble();
        System.out.print("請輸入第三邊長: ");
        double c = scanner.nextDouble();

        // 判斷是否為有效三角形
        if (isValidTriangle(a, b, c)) {
            if (isEquilateral(a, b, c)) {
                System.out.println("這是正三角形。");
            } else if (isIsosceles(a, b, c)) {
                System.out.println("這是等腰三角形。");
            } else if (isRightTriangle(a, b, c)) {
                System.out.println("這是直角三角形。");
            } else {
                System.out.println("這是一般三角形。");
            }
        } else {
            System.out.println("這不是一個有效的三角形。");
        }

        scanner.close();
    }

    // 驗證三邊長是否能形成一個有效三角形
    public static boolean isValidTriangle(double a, double b, double c) {
        return (a + b > c);
    }

    // 判斷是否為正三角形
    public static boolean isEquilateral(double a, double b, double c) {
        return (a == b) && (b == c) && (c == a);
    }

    // 判斷是否為等腰三角形
    public static boolean isIsosceles(double a, double b, double c) {
        return (a == b) || (b == c) || (a == c);
    }

    // 判斷是否為直角三角形
    public static boolean isRightTriangle(double a, double b, double c) {
        double max = Math.max(a, Math.max(b, c));
        return Math.abs((max * max) - (b * b + c * c)) < 0.0001;
    }
}
