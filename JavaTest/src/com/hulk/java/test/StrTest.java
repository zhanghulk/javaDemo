package com.hulk.java.test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Random;

/**
 * Java中无论是汉字还是英文字母都是用Unicode编码来表示的，一个Unicode码是16位，每字节是8位，所以一个Unicode码占两字节。但是英文字母比较特殊，源自于8位（1字节）的ASCII吗，于是在Unicode码仅使用了低8位（1字节）就可以表示，高8位的话不使用也无所谓。所以
char c='a';
System.out.println(c.getBytes().lenth()),得到的是1（字节）
但汉字就完整地使用了16位（2字节）的Unicode，所以
char c='中';
System.out.println(c.getBytes().lenth()),得到的是2（字节）
综上，c='a'在内存中确实只占1字节，但这不意味着String s="abc"在内存中只占3字节。应该这么说，String s="abc"至少在内存中占3字节。这是因为char是基本数据类型，而String确是对象类型。对象是一种很复杂的数据类型，你要看一个对象具体占多少字节，可以把这个对象序列化后存入一个文本文件来看它具体占用了多少字节，当然这也不是精确的，因为序列化需要写入少量系统信息，但大致是对的。
 * @author zhanghao
 *
 */
public class StrTest {

	static final int MAX = 1000 * 10000 * 1;
	static final String[] arr = new String[MAX];
	
	static char[] chs = new char[]{'a', 'b', 'f', 'b', 'f'};
	char ch1 = 'd';
	
	public StrTest() {
	}

	public static void main(String[] args) {
		//testItern();
		StrTest test = new StrTest();
		//test.testStrSpace();
		
		System.out.println("testStrSpace: " + Arrays.toString(chs) + ", length: " + chs.length);
		
		String b="aass";
		System.out.println("b byte length=" + b.getBytes().length + ", b.length()=" +b.length());
		
		String a="啊中国";
		System.out.println("a byte length=" + a.getBytes().length + ", a.length()=" +a.length());
		byte[] bytes = a.getBytes();
		int byteLen = bytes.length;
		String str;
		try {
			//utf-8 : str byte length=6, str.length()=2
			//GB2312 : str byte length=12, str.length()=4
			//ASCII : str byte length=18, str.length()=6
			str = new String(bytes, 0, byteLen, "utf-8");
			System.out.println("str byte length=" + str.getBytes().length + ", str.length()=" +str.length());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * Java虚拟机规范中字符串对象允许的最大长度为u2， 即两个字节，为2^16此次方: 65536个字节，除开2个字节保存null，因此只能保存65534个字节(utf-8时为65534个字符)
	 */
	public String testStrSpace() {
		int MAX_LENGTH = (int) Math.pow(2, 10);//2^16;
		StringBuffer buff = new StringBuffer();
		System.out.println("testStrSpace: MAX_LENGTH= " + MAX_LENGTH);
		for(int i = 0; i < MAX_LENGTH; i++) {
			buff.append("a");
			//sStr += "a";
		}
		String str = buff.toString();
		System.out.println("testStrSpace: str.length= " + str.length());
		System.out.println("testStrSpace: " + str);
		return str;
	}
	
	public static void testItern() {
		String s = new String("1");
	    String s2 = "1";
	    s.intern();
	    System.out.println(s == s2);

	    String s3 = new String("1") + new String("1");
	    s3.intern();//intern把s3字符串放进字符串常量池，即常量池中与"11"
	    String s4 = "11";
	    s3.intern();//intern放在s4后面，s3和s4就是两个对象
	    System.out.println(s3 == s4);
	    
	    
	    //测试intern用法
	    System.out.println("Start test intern or not spent time.");
	    Integer[] DB_DATA = new Integer[10];
	    Random random = new Random(10 * 10000);
	    for (int i = 0; i < DB_DATA.length; i++) {
	        DB_DATA[i] = random.nextInt();
	    }
		long t = System.currentTimeMillis();
	    for (int i = 0; i < MAX; i++) {
	        //arr[loopCount] = new String(String.valueOf(DB_DATA[loopCount % DB_DATA.length]));  //spent 4576ms, 10000 * 10000条,程序会死掉:OOM
	        arr[i] = new String(String.valueOf(DB_DATA[i % DB_DATA.length])).intern();//spent 2271ms, 10000 * 10000条耗时21448ms
	    }

		System.out.println((System.currentTimeMillis() - t) + "ms");
	    System.gc();
	}
	
	public static void testStrLength() {
		
	}

}
