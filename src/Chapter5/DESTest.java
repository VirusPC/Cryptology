package Chapter5;

import org.junit.Test;

public class DESTest {
    @Test
    public void testKeyGenerator() {
        String key_string = "AABB09182736CCDD";
        KeyGenerator keyGenerator = new KeyGenerator(key_string);
        long key;
        String keyString;
        for (int i=0; i<16; i++) {
            key = keyGenerator.getKey();
            keyString = Tools.longToString(key);
            System.out.println((i+1)+"  "+keyString);
        }
    }

    @Test
    public void testGetKeyList() {
        String key_string = "AABB09182736CCDD";
        KeyGenerator keyGenerator = new KeyGenerator(key_string);

        long[] keyList = keyGenerator.getKeyList();
        int i = 0;
        for (long turnKey: keyList) {
            System.out.print("Turn:"+(++i)+"   ");
            System.out.println(Tools.longToString(turnKey));
        }
    }

    @Test
    public void testDEC() {
        String text = "123456ABCD132536";
        String key = "AABB09182736CCDD";
        long textBit = Tools.stringToLong(text);
        DES des = new DES();
        long newTextBit = des.encrypt(textBit, key);
        String newText = Tools.longToString(newTextBit);
        System.out.println(newText);
    }

    @Test
    public void testKeyGenerator2() {
        String key_string = "AABB09182736CCDD";
        KeyGenerator keyGenerator = new KeyGenerator(key_string);
        long key;
        String keyString;
        for (int i=0; i<2; i++) {
            key = keyGenerator.getKey();
            System.out.println("置换选择2");
            Tools.printBinary(key, 48);
            keyString = Tools.longToString(key);
            System.out.println((i+1)+"  "+keyString);
        }
    }

}
