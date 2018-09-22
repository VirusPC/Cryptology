package chapter5;

public class Tools {
    //将密钥由十六进制字符串转换为数值类型
    protected static long stringToLong(String KEYString){
        long KEY = 0;
        for(int i=0; i<KEYString.length(); i++) {
            String s = KEYString.substring(i, i+1);
            int num = Integer.parseInt(s, 16);
            KEY = 16 * KEY + num;
        }
        return  KEY;
    }

    //打印输出key对应的二进制
    protected static void printBinary(long key, int length){
        System.out.println();
        for(int i=length-1; i>=0; i--){
            System.out.print((key>>i)&1);
            if(i%4 == 0){
                System.out.print(" ");
            }
            if(i%8 == 0){
                System.out.println();
            }
        }
        System.out.println();
    }

    //将密钥由数值类型转换为十六进制字符串
    protected static String longToString(long key){
        StringBuilder sb = new StringBuilder();
 //       int count = 0;
        //计数
//        for(int i=15; i>=0; i--) {
//            long num = key>>(i*4) & 0xF;
//            if(num != 0) break;
//            count++;
//        }
        //
        for(int i=15; i>=0; i--) {
            long num = key>>(i*4) & 0xF;
            sb.append(Integer.toHexString((int)num));
        }
        return sb.toString().toUpperCase();
    }
}
