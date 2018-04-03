package javax.util.demo;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class PingTest {

	public static void main(String[] args) {
		boolean connected = pingAddress("www.baidu.com", 1, 3);
		System.out.println("Connected: " + connected);
	}

	/**
	 * 需要在Android中使用LInux底层的命令：如执行Ping命令 格式为 ping -c 1 -w 5
	 * <p>
	 * 其中参数-c 1是指ping的次数为1次，-w是指执行的最后期限,单位为秒，也就是执行的时间为5秒，超过5秒则失败.
	 * <p>
	 * Ping命令代码:Process p = Runtime.getRuntime().exec("ping -c 1 -w 5 " + ip);
	 * 
	 * @param address
	 * @param pingCount
	 * @param timeout
	 * @return
	 */
	public static boolean pingAddress(String address, int pingCount, int timeout) {
		Process process = null;
        try {
            // process = Runtime.getRuntime().exec("ping -c 2 -w 5 " + address);
            process = Runtime.getRuntime().exec("ping -c " + pingCount + " -w " + timeout + " " + address);
            InputStreamReader r = new InputStreamReader(process.getInputStream());
            LineNumberReader returnData = new LineNumberReader(r);
            String returnMsg = "";
            String line = "";
            System.out.println("Connecting to " + address + " ...... ");
            //Log.i(TAG, "pingAddress Connecting to " + address + " ...... ");
            while ((line = returnData.readLine()) != null) {
                System.out.println("pingAddress received line: " + line);
                //Log.i(TAG, "pingAddress returnMsg: ");
                returnMsg += line;
            }
            boolean connected = false;
            if (returnMsg.toLowerCase().indexOf("100% loss") != -1) {
                connected = false;
            } else if (returnMsg.toLowerCase().contains("0% packet loss")) {
                connected = true;
            }
            System.out.println("Connect to " + address + " failed ");
            //Log.i(TAG, "pingAddress connected " + connected);
            return connected;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (process != null) {
                System.out.println("process.destroy() ");
                process.destroy();
            }
        }
        return false;
	}
}
