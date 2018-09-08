package com.v0ex.Collection;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zbj
 * @date 17/9/1
 */
public class HashMapTest {

    static final int MAXIMUM_CAPACITY = 1 << 30;

    public static void main(String[] args) {
        System.out.println(16 & 17);
        System.out.println(32 & 17);
        System.out.println(15 & 3737573);
        Map<String,String> map = new HashMap();
        String put = map.put("2", "1");
        String put1 = map.put("2", "2");
        System.out.println(put);
        System.out.println(put1);



//        int n = 5;
//        System.out.println(n >>> 1);
//        n |= n >>> 1;
//        System.out.println(n);
//        int a = 3,b=5;
//        a = a^b;
//        b = b^a;
//        a = a^b;
//        System.out.println("a="+a);
//        System.out.println("b="+b);
//        int j = -1;
//        System.out.println(j>>>2);

    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
