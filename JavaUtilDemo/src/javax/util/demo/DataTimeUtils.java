package javax.util.demo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataTimeUtils {
	public static String getCurrentTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");//yyyy-MM-dd HH:mm:ss E
        return sdf.format(new Date(System.currentTimeMillis()));
	}
	
	public static String getCurrentTime2() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
        return sdf.format(new Date(System.currentTimeMillis()));
	}
}
