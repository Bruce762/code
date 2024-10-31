package demo;

public class SimpleAdd {

	public int intAdd(int a, int b) {
		return a + b;
	}

	public int doubleAdd(double a, double b) {
		return (int) a + (int) b;
	}

	public int[] arrAdd(int[] a, int[] b) {
		int[] c = new int[a.length];
		for (int i = 0; i < a.length; i++) {
			c[i] = a[i] + b[i];
		}
		return c;
	}
}
