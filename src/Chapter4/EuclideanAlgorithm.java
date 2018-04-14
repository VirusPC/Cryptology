package Chapter4;

import static java.lang.Math.*;

public class EuclideanAlgorithm {
    public static void main(String[] args) {
        int x = 1066;
        int y = 1970;
        int result = gcd(max(x, y), min(x, y));
        System.out.println(result);
    }

    public static int gcd(int x, int y){
        if(y == 0) return x;
        int temp = y;
        y = x % y;
        return gcd(temp, y);
    }
}
