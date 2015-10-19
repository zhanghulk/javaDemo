
package javax.util.demo;

import java.util.HashMap;
import java.util.Map;

public class MapTest {

    public static void main(String[] args) {
        Map<String, Aa> map1 = getMap();
        Map<String, Aa> map2 = getMap2();
        System.out.println(map1.equals(map2));
    }

    private static Map<String, Aa> getMap() {
        Map<String, Aa> map1 = new HashMap<String, Aa>();
        Aa aa1 = new Aa();
        aa1.id = 1;
        aa1.ss = "aaaa";
        map1.put("a1", aa1);
        Aa aa2 = new Aa();
        aa2.id = 1;
        aa2.ss = "aaaa";
        map1.put("a2", aa2);
        Aa aa3 = new Aa();
        aa3.id = 1;
        aa3.ss = "aaaa";
        map1.put("a3", aa3);
        return map1;
    }

    private static Map<String, Aa> getMap2() {
        Map<String, Aa> map = new HashMap<String, Aa>();
        Aa aa1 = new Aa();
        aa1.id = 1;
        aa1.ss = "aaaa";
        map.put("a1", aa1);
        Aa aa2 = new Aa();
        aa2.id = 1;
        aa2.ss = "aaaa";
        map.put("a2", aa2);
        return map;
    }

    static class Aa {
        public int id;
        public String ss;

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Aa))
                return false;
            Aa a = (Aa) obj;
            return id == a.id && ss.equals(a.ss);
        }
    }
}
