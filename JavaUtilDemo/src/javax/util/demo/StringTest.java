package javax.util.demo;

import java.io.File;
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
    	//testSubString();
    	//compareTo();
    	String s1 = new String("aaa");
    	String s2 = new String("aaa");
    	System.out.println(s1 == s1);
    	
    	System.out.println("ssssdsd".length());
    	System.out.println( "我是帐号程序员顶顶顶的搜".length());
    	System.out.println(("ssssdsdaaaasfdsdDD".length() * 2 ) / 3);
    	
    	System.out.println("abc".getBytes().length * 3);
    	System.out.println("程序员".getBytes().length);
    }
    
    private static void compareTo() {
    	File f1 = new File("/logs/20180117_1511.txt");
    	File f2 = new File("/logs/20180117_1512.txt");
    	System.out.println(f1.compareTo(f2));
    	System.out.println(f1.getName());
    	System.out.println(f1.getName().compareTo(f2.getName()));
    }
    
    private static void testSubString() {
		String path = "D:/file/docs/haoge.docx";
		System.out.println("dir: " + path.substring(0, path.lastIndexOf("/")));
		System.out.println("filename: " + path.substring(path.lastIndexOf("/") + 1));
	}
    
    private static void testTextEquels() {
    	String text = new String("SUCCESS");
    	testTextEquels(text); //false, new һ��String����
    	
    	String text2 = "SUCCESS";
    	testTextEquels(text2);//true , ֱ�ӳ���һ��String����
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

