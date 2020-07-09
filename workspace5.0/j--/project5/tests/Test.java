public class Test {

    public static int add(int a, int b) {
        int c = 0;
        c = a + b;
        return c;
    }

    public static int factorial(int n) {
        if (n <= 0) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

}