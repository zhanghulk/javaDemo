package javax.util.demo;

public class TimeTest {

	//������λ����
    public static final int TIME_MINUTE = 60;//60s
    public static final int TIME_HOUR =  TIME_MINUTE * 60;
    public static final int TIME_DAY =  TIME_HOUR * 24;
    
    public static final String TEXT_MINUTE = "����";//60s
    public static final String TEXT_HOUR =  "Сʱ";
    public static final String TEXT_DAY =  "��";
    
    public static void main(String[] args) {
		System.out.println(formatTimeInterval(844211));//86400
	}
	
	
	public static String formatTimeInterval(long timeIntervalSeconds) {
        if (timeIntervalSeconds < 60) {
        	return "";
		}
        long minutes = timeIntervalSeconds / 60;
        long hours = minutes / 60;//����ΪСʱ
        minutes = minutes % 60;//����Ϊʣ�·�����
        long days = hours / 24;//����Ϊ����
        hours = hours % 24; //����Ϊʣ��Сʱ��
        StringBuffer buffer = new StringBuffer();
        if (days > 0) {
            buffer.append(days + TEXT_DAY);//XX��
        }
        if (hours > 0) {
            buffer.append(hours + TEXT_HOUR);//XXСʱ
        }
        if (minutes > 0) {
            buffer.append(minutes + TEXT_MINUTE);//XX����
        }
        return buffer.toString();//XX��XXСʱXX����
    }
	
	public static String formatTimeInterval2(long timeIntervalSeconds) {
        String minuteStr = TEXT_MINUTE;
        long minutes = timeIntervalSeconds / TIME_MINUTE;
        if (timeIntervalSeconds < TIME_HOUR) {
            return minutes + minuteStr;//XX����,һСʱ����
        }
        String hourStr = TEXT_HOUR;
        long hours = minutes / 60;//����ΪСʱ
        minutes = minutes % 60;//����Ϊ������
        if (timeIntervalSeconds < TIME_DAY) {
            StringBuffer buffer = new StringBuffer(hours + hourStr);
            if (minutes > 0) {
                buffer.append(minutes + minuteStr);
            }
            return buffer.toString();//XXСʱXX����
        }
        String dayStr = TEXT_DAY;
        long days = hours / 24;//����Ϊ����
        hours = hours % 24; //����Ϊʣ��Сʱ��
        StringBuffer buffer = new StringBuffer(days + dayStr);
        if (hours > 0) {
            buffer.append(hours + hourStr);
        }
        if (minutes > 0) {
            buffer.append(minutes + minuteStr);
        }
        return buffer.toString();//XXСʱXX����
    }
}
