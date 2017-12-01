package javax.util.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
    	//testTextEquels();
    	testSubString();
    }
    
    private static void testSubString() {
		String path = "D:/file/docs/haoge.docx";
		System.out.println("dir: " + path.substring(0, path.lastIndexOf("/")));
		System.out.println("filename: " + path.substring(path.lastIndexOf("/") + 1));
	}
    
    private static void testTextEquels() {
    	String text = new String("SUCCESS");
    	testTextEquels(text); //false, new 一个String对象
    	
    	String text2 = "SUCCESS";
    	testTextEquels(text2);//true , 直接出入一盒String常量
	}
    
    private static void testTextEquels(String text) {
    	System.out.println(text=="SUCCESS");
	}

    private static void testSpecialCharacters() {
    	String ss = "dsakgjfds\nalfgfagfajg";
        //System.out.println(patternInvalidFileName(ss));
        String str = "aa\naaa123a";
        //System.out.println(str.startsWith("aaaaaa"));
        String aaa = "qqq";
        //System.out.println(testFun(str));
//        regexFileName();
        //regexFileParentPath();
        System.out.println("\\p{C}: " +  str.replaceAll("\\p{C}", ""));
        System.out.println("\\p{Alpha}: " + str.replaceAll("\\p{Alpha}", ""));
        System.out.println("\\p{Digit}: " + str.replaceAll("\\p{Digit}", ""));
        //System.out.println(str.replaceAll("\\p{\n}", ""));
	}
    private static  void listTest() {
    	LinkedList<String> argvlist = new LinkedList<String>();
        List<String> list = new ArrayList<String>();
        Map<String, String> map = new HashMap<>();
        //Collections.addAll(argvlist, map);
	}
    
    public static boolean patternInvalidFileName(String fileName) {
        if(fileName == null) {
            return false;
        }
        Pattern p1 = Pattern.compile("|/\\>*<?\":"); 
        Matcher m1 = p1.matcher(fileName);
        return m1.find();
    }

    private static void regexFileName() {
    	String fullName = "/home/misJ/tmp/May/data/MyTest.xml";  
        Pattern pattern = Pattern.compile("[^/\\\\]+$");  
        Matcher matcher = pattern.matcher(fullName);  
        if(matcher.find()) {  
            System.out.println(matcher.group());  
        } 
	}

    private static void regexFileParentPath() {
    	String fullName = "/home/misJ/tmp/May/data/MyTest.xml";  
        Pattern pattern = Pattern.compile("^[^/\\\\]+");  
        Matcher matcher = pattern.matcher(fullName);  
        if(matcher.find()) {  
            System.out.println(matcher.group());  
        } 
	}

    private static void regexFileExtension() {
    	String fullName = "/home/misJ/tmp/May/data/MyTest.xml";  
        Pattern pattern = Pattern.compile("[^.]+$");  
        Matcher matcher = pattern.matcher(fullName);  
        if(matcher.find()) {  
            System.out.println(matcher.group());  
        } 
	}
    
    public static String testFun(String s, String...args) {
    	if(args == null || args.length == 0) {
    		return s;
    	}
    	StringBuffer sb = new StringBuffer(s);
    	for (String s1 : args) {
    		sb.append("_").append(s1);
		}
		return sb.toString();
	}
}

