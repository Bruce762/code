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
