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
    public void testDEC() {
        String text = "123456ABCD132536";
        String key = "AABB09182736CCDD";
        DES DES = new DES(text, key);
        System.out.println(DES.getText());
    }
}
