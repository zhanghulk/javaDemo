
package javax.util.demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CharsetUtils {
    
    public static final String CHINESE_REG_EX = "[\\u4e00-\\u9fa5]";

    public static void main(String[] args) {
        System.out.println(isChineseText("s号"));
        System.out.println(getChineseCount("s"));
    }

    public static boolean isChineseText(final String chineseText) {
        return getChineseMatcher(chineseText).find();
    }

    /**
     * 中文字符编码匹配正则表达式:"[\\u4e00-\\u9fa5]"
     * @param chineseText
     * @return
     */
    public static Matcher getChineseMatcher(String chineseText) {
        Pattern p = Pattern.compile(CHINESE_REG_EX);
        return p.matcher(chineseText);
    }

    /**
     * 计算中文字符串的长度(一个中文字符的长度为1)
     * <p>
     * 中文字符编码匹配正则表达式:"[\\u4e00-\\u9fa5]"
     * 
     * @param chineseText  中文字符串如："好汉歌"， "你好ni"
     * @return
     */
    public static int getChineseCount(final String chineseText) {
        if (chineseText == null || chineseText.isEmpty()) {
            return 0;
        }
        int count = 0;
        Matcher m = getChineseMatcher(chineseText);
        while (m.find()) {
            for (int i = 0; i <= m.groupCount(); i++) {
                count++;
            }
        }
        return count;
    }
}
