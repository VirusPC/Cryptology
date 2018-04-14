package Chapter4;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class ExtendedEuclidean {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("please enter x");
        int x = input.nextInt();
        System.out.println("please enter y");
        int y = input.nextInt();
        int[] ax = {1, 0, max(x, y)};
        int[] ay = {0, 1, min(x, y)};
        int[] result = exgcd(ax, ay);
        System.out.println("最大公约数为： " + result[0]);
        if(result.length == 1) {
            System.out.println("无逆元");
        } else {
            System.out.println("逆元为： " + result[1]);
        }
    }

    public static int[] exgcd(int[] ax, int[] ay){
        System.out.println(Arrays.toString(ax) + Arrays.toString(ay));
        if(ay[2] == 0) {
            int[] result = {gcd(ax[2], ay[2])};
            return  result;
        } else if (ay[2] == 1) {
            int[] result = {gcd(ax[2], ay[2]), ay[1]};
            return result;
        }
        int Q = ax[2]/ay[2];
        int[] temp = {ax[0] - Q * ay[0], ax[1] - Q * ay[1], ax[2] - Q * ay[2]};
        return exgcd(ay, temp);
    }

    public static int gcd(int x, int y){
        if(y == 0) return x;
        int temp = y;
        y = x % y;
        return gcd(temp, y);
    }
}
