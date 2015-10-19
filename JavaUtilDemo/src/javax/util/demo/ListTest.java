
package javax.util.demo;

import java.util.ArrayList;
import java.util.List;

public class ListTest {

    public static void main(String[] args) {
        List<String> l = new ArrayList<>();
        l.add("aa");
        l.add("bb");
        l.add("cc");
        l.add("dd");
        System.out.println(l);
        Object[] lArr = l.toArray();
        for (Object object : lArr) {
            System.out.println(object);
        }
        System.out.println("=============================================");
        String[] newArr = new String[l.size() + 2];
        String[] lArr1 = l.toArray(newArr);
        for (Object object : lArr1) {
            System.out.println(object);
        }
        System.out.println("=================---------------===========================");
        for (Object object : newArr) {
            System.out.println(object);
        }
    }
}
