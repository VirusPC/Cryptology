package Chapter5;

public class KeyGenerator {

    private static String KEY_STRING = "AABB09182736CCDD";
    private  static long KEY = 0;
    private  static int[] C0 = {57, 49, 41, 33, 25, 17, 9,
                                        1, 58, 50, 42, 34, 26, 18,
                                        10, 2, 59, 51, 43, 35, 27,
                                        19, 11, 3, 60, 52, 44, 36};
    private static  int[] D0 = {63, 55, 47, 39, 31, 33, 15,
                                        7, 62, 54, 46, 38, 30, 22,
                                        14, 6, 61, 53, 45, 37, 29,
                                        21, 13, 5, 28, 20, 12, 4};
    private static  int[] F = {14, 17, 11, 24, 1, 5,
                                    3, 28, 15, 6, 21, 10,
                                    23, 19, 12, 4, 26, 8,
                                    16, 7, 27, 20, 13, 2,
                                    41, 52, 31, 37, 47, 55,
                                    30, 40, 51, 45, 33, 48,
                                    44, 49, 39, 56, 34, 53,
                                    46, 42, 50, 36, 29, 32};
    private static int[] MOVE = {1, 1, 2, 2, 2, 2, 2, 2,
                                    1, 2, 2 ,2, 2, 2, 2, 1};
    private static int TURN = 0;
    private static int BYTE = 4;

    KeyGenerator(){
        KEY = Tools.stringToLong(KEY_STRING);
        permutationChoice1();
    }

    KeyGenerator(String keyString){
        KEY = Tools.stringToLong(keyString);
        permutationChoice1();
    }

    public long getKey(){
        rotateLeft();
        TURN = ++TURN;
        return permutationChoice2();
    }


    //置换选择1
    private static void permutationChoice1() {
        long newKEY = 0;
        for(int i=0; i<28; i++) {
            long num = (KEY>>(64-C0[i]))&1;
            newKEY = newKEY * 2 + num;
        }
        for(int i=0; i<28; i++) {
            long num = (KEY>>(64-D0[i]))&1;
            newKEY = newKEY * 2 + num;
        }
        KEY = newKEY;
    }

    //循环左移
    private static void rotateLeft() {
        long C = KEY>>28;
        long D = KEY&0xFFFFFFF;
        C = (C<<MOVE[TURN%16]) | C>>(28-MOVE[TURN%16]);
        C = C & 0xFFFFFFF;
        D = (D<<MOVE[TURN%16]) | D>>(28-MOVE[TURN%16]);
        D = D & 0xFFFFFFF;
        KEY = (C<<28) | D;
    }

    //置换选择2
    private static long permutationChoice2() {
        long newKey = 0;
        for(int i=0; i<48; i++) {
            long num = (KEY>>(56-F[i]))&1;
            newKey = newKey * 2 + num;
        }
        return newKey;
    }



    public static void main(String[] args) {
        KeyGenerator keyGenerator = new KeyGenerator();
        long key;
        String keyString;
        for (int i=0; i<16; i++) {
            key = keyGenerator.getKey();
            keyString = Tools.longToString(key);
            System.out.println(keyGenerator.TURN+"  "+keyString);
        }
    }

}
