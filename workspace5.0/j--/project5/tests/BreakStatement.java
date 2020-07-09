import java.lang.Integer;
import java.lang.System;

public class BreakStatement {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        for (int i = 0; true; i++) {
            if (i > n) {
                break;
            }
            System.out.println(i);
        }
    }
}
