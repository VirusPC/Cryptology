package Chapter5;

import static java.lang.Math.pow;

public class KeyGenerator1 {

    private static String KEY_STRING = "AABB09182736CCDD";
    //private  static long KEY = 0;
    private  static int[] C0 = {57, 49, 41, 33, 25, 17, 9,
                                        1, 58, 50, 42, 34, 26, 18,
                                        10, 2, 59, 51, 43, 35, 27,
                                        19, 11, 3, 60, 52, 44, 36};
    private static  int[] D0 = {63, 55, 47, 39, 31, 33, 15,
                                        7, 62, 54, 46, 38, 30, 22,
                                        14, 6, 61, 53, 45, 37, 29,
                                        21, 13, 5, 28, 20, 12, 4};
    private static  int[] F = {};
    private static int[] MOVE = {1, 1, 2, 2, 2, 2, 2, 2,
                                    1, 2, 2 ,2, 2, 2, 2, 1};
    private static int TURN = 0;

    public static void main(String[] args) {
        long key = 0;

        key = stringToInt(KEY_STRING);
        Tools.printBinary(key, 64);

        key = permutationChoice1(key);
        Tools.printBinary(key, 64);

        key = permutationChoice2(key);
        Tools.printBinary(key, 64);

    }


    //将密钥由十六进制字符串转换为二进制数值类型
    private static long stringToInt(String keyString){
        long key = 0;
        for(int i=0; i<keyString.length(); i++) {
            String s = keyString.substring(i, i+1);
            int num = Integer.parseInt(s, 16);
            key = 16 * key + num;
        }
        return  key;
    }


    //置换选择1
    private static long permutationChoice1(long key) {
        long newKey = 0;
        for(int i=0; i<28; i++) {
            long num = (key>>(64-C0[i]))&1;
            newKey = newKey * 2 + num;
        }
        for(int i=0; i<28; i++) {
            long num = (key>>(64-D0[i]))&1;
            newKey = newKey * 2 + num;
        }
        return newKey;
    }


    //置换选择2
    private static long permutationChoice2(long key) {
        long C = key>>28;
        long D = key&0xFFFFFFF;
        System.out.println( (C<<1) | C>>(27));
        C = C<<MOVE[TURN] | C>>(28-MOVE[TURN]) ;
        C = C & 0xFFFFFFF;
        D = D<<MOVE[TURN] | D>>(28-MOVE[TURN]);
        key = (C<<28) | D;
        TURN++;
        return key;
    }


//    private static void printBinary(long key, int length){
//        for(int i=length-1; i>=0; i--){
//            System.out.print((key>>i)&1);
//            if(i%4 == 0){
//                System.out.print(" ");
//            }
//            if(i%8 == 0){
//                System.out.println();
//            }
//        }
//        System.out.println();
//    }

}
