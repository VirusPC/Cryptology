package Chapter5;

public class KeyGenerator0 {

    private static String KEY_STRING = "AABB09182736CCDD";
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

    public static void main(String[] args) {

        Integer[] keyArray = stringToIntegerArray(KEY_STRING);

        for (int i=1; i<65; i++) {
            System.out.print(keyArray[i]);
            if(i%4 == 0){
                System.out.print(" ");
            }
            if(i%8 == 0){
                System.out.println();
            }
        }

        System.out.println();

        keyArray = permutationChoice1(keyArray);

        for (int i=1; i<=56; i++) {
            System.out.print(keyArray[i]);
            if(i%7 == 0){
                System.out.println();
            }
        }

        System.out.println();

        keyArray = permutationChoice2(keyArray);

        for (int i=1; i<=56; i++) {
            System.out.print(keyArray[i]);
            if(i%7 == 0){
                System.out.println();
            }
        }

    }

    //将密钥由十六进制字符串转换为二进制数组，并返回数组，下标从1到64
    public static Integer[] stringToIntegerArray(String keyString){
        Integer[] array = new Integer[65];
        for(int i=0; i<16; i++) {
            String s = keyString.substring(i, i+1);
            int num = Integer.parseInt(s, 16);
            for(int j=0; j<4; j++) {
                array[i*4+j+1] = (num>>(3-j))&(1);
            }
        }
        return  array;
    }

    //置换选择1
    public static Integer[] permutationChoice1(Integer[] keyArray) {
        Integer[] temp = new Integer[57];
        for(int i=1; i<=28; i++) {
            temp[i] = keyArray[C0[i-1]];
        }
        for(int i=29; i<=56; i++) {
            temp[i] = keyArray[D0[i-28-1]];
        }
        return temp;
    }

    //置换选择2
    public static Integer[] permutationChoice2(Integer[] keyArray) {

        //C循环左移
        int temp = keyArray[1];
        for(int i=1; i<=27; i++) {
            keyArray[i] = keyArray[i+1];
        }
        keyArray[28] = temp;

        //D循环左移
        temp = keyArray[29];
        for(int i=29; i<=55; i++) {
            keyArray[i] = keyArray[i+1];
        }
        keyArray[56] = temp;


        //选择
        return keyArray;
    }

    //public static Integer[] rotateLeft(Integer[] keyArray) {}

}
