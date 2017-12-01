package javax.util.demo;

public class TimeTest {

	//基本单位是秒
    public static final int TIME_MINUTE = 60;//60s
    public static final int TIME_HOUR =  TIME_MINUTE * 60;
    public static final int TIME_DAY =  TIME_HOUR * 24;
    
    public static final String TEXT_MINUTE = "分钟";//60s
    public static final String TEXT_HOUR =  "小时";
    public static final String TEXT_DAY =  "天";
    
    public static void main(String[] args) {
		System.out.println(formatTimeInterval(844211));//86400
	}
	
	
	public static String formatTimeInterval(long timeIntervalSeconds) {
        if (timeIntervalSeconds < 60) {
        	return "";
		}
        long minutes = timeIntervalSeconds / 60;
        long hours = minutes / 60;//求商为小时
        minutes = minutes % 60;//求余为剩下分钟数
        long days = hours / 24;//求商为天数
        hours = hours % 24; //求余为剩余小时数
        StringBuffer buffer = new StringBuffer();
        if (days > 0) {
            buffer.append(days + TEXT_DAY);//XX天
        }
        if (hours > 0) {
            buffer.append(hours + TEXT_HOUR);//XX小时
        }
        if (minutes > 0) {
            buffer.append(minutes + TEXT_MINUTE);//XX分钟
        }
        return buffer.toString();//XX天XX小时XX分钟
    }
	
	public static String formatTimeInterval2(long timeIntervalSeconds) {
        String minuteStr = TEXT_MINUTE;
        long minutes = timeIntervalSeconds / TIME_MINUTE;
        if (timeIntervalSeconds < TIME_HOUR) {
            return minutes + minuteStr;//XX分钟,一小时以内
        }
        String hourStr = TEXT_HOUR;
        long hours = minutes / 60;//求商为小时
        minutes = minutes % 60;//求余为分钟数
        if (timeIntervalSeconds < TIME_DAY) {
            StringBuffer buffer = new StringBuffer(hours + hourStr);
            if (minutes > 0) {
                buffer.append(minutes + minuteStr);
            }
            return buffer.toString();//XX小时XX分钟
        }
        String dayStr = TEXT_DAY;
        long days = hours / 24;//求商为天数
        hours = hours % 24; //求余为剩余小时数
        StringBuffer buffer = new StringBuffer(days + dayStr);
        if (hours > 0) {
            buffer.append(hours + hourStr);
        }
        if (minutes > 0) {
            buffer.append(minutes + minuteStr);
        }
        return buffer.toString();//XX小时XX分钟
    }
}
