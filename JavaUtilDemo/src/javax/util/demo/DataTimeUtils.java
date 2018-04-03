package javax.util.demo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataTimeUtils {
	
	public static void main(String[] args) {
		System.out.println(getCurrentTime());
		System.out.println(getCurrentTime4());
		System.out.println(getCurrentTime2());
	}
	
	public static String getCurrentTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String time = sdf.format(new Date(System.currentTimeMillis()));
		return time;
	}
	
	public static long getCurrentTime4() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = sdf.format(new Date(System.currentTimeMillis()));
		return Long.valueOf(time);
	}
	
	public static String getCurrentTime3() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");//yyyy-MM-dd HH:mm:ss E
        return sdf.format(new Date(System.currentTimeMillis()));
	}
	
	public static String getCurrentTime2() {
		//2018-01-17 14:32:52 Wed
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");//E表示周几
        return sdf.format(new Date(System.currentTimeMillis()));
	}
}
