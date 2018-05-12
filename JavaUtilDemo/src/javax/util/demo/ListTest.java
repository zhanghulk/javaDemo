
package javax.util.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListTest {

    public static void main(String[] args) {
        List<String> l = new ArrayList<>(4);
        l.add("aa");
        l.add("bb");
        l.add("cc");
        l.add("dd");
        System.out.println(l);
        l.add(2, "ee");//插入一个元素，后面的自动依次后移
        Object[] lArr = l.toArray();
        printlnArr(lArr);
        System.out.println("=============================================");
        String[] newArr = new String[l.size() + 2];
        String[] lArr1 = l.toArray(newArr);
        printlnArr(lArr1);
        System.out.println("=================---------------===========================");
    }
    
    static void printlnArr(Object[] arr) {
    	System.out.println(Arrays.toString(arr));
    }
}
