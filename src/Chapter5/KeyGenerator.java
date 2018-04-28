package Chapter5;

public class KeyGenerator {
    private long initKey; //密钥
    private long key; //当前轮密钥
    private int turn; //当前轮数

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


    KeyGenerator(String keyString){
        this.initKey = Tools.stringToLong(keyString);
        init();
    }

    //每次调用将获取到下一个轮密钥
    public long getKey(){
        rotateLeft();
        this.turn += 1;
        return permutationChoice2();
    }

    //获取全部轮密钥的集合
    public long[] getKeyList(){
        init();

        long[] keyList = new long[16];
        for(int i=0; i<16; i++) {
            keyList[i] = getKey();
        }

        return keyList;
    }

    //初始化
    public void init() {
        this.key = this.initKey;
        this.turn = 0;
        permutationChoice1();
    }


    //置换选择1
    private void permutationChoice1() {
        int len_input = 64;
        int len_output = 28;
        long newKEY = 0;
        for(int i=0; i<len_output; i++) {
            long num = (this.key>>(len_input-C0[i]))&1;
            newKEY = newKEY * 2 + num;
        }
        for(int i=0; i<len_output; i++) {
            long num = (this.key>>(len_input-D0[i]))&1;
            newKEY = newKEY * 2 + num;
        }
        this.key = newKEY;
    }

    //循环左移
    private void rotateLeft() {
        int len = 28;
        int maxTurn = 16;
        long C = this.key>>len;
        long D = this.key&0xFFFFFFF;
        C = (C<<MOVE[this.turn%maxTurn]) | C>>(len-MOVE[this.turn%maxTurn]);
        C = C & 0xFFFFFFF;
        D = (D<<MOVE[this.turn%maxTurn]) | D>>(len-MOVE[this.turn%maxTurn]);
        D = D & 0xFFFFFFF;
        this.key = (C<<len) | D;
    }

    //置换选择2
    private long permutationChoice2() {
        int len_input = 56;
        int len_output = 48;
        long newKey = 0;
        for(int i=0; i<len_output; i++) {
            long num = (this.key>>(len_input-F[i]))&1;
            newKey = newKey * 2 + num;
        }
        return newKey;
    }

}
