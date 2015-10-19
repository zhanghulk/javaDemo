package javax.util.demo;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 一、BufferedReader类 public class BufferedReader extends Reader
 * 从字符输入流中读取文本，缓冲各个字符，从而实现字符、数组和行的高效读取。 可以指定缓冲区的大小，或者可使用默认的大小。大多数情况下，默认值足够大。
 * 通常，Reader 所作的每个读取请求都会导致对底层字符或字节流进行相应的读取请求。因此，建议用 BufferedReader包装所有其 read()
 * 操作可能开销很高的 Reader（如 FileReader和 InputStreamReader）。
 * BufferedReader流能够读取文本行,通过向BufferedReader传递一个Reader对象
 * ,来创建一个BufferedReader对象,之所以这样做是因为FileReader没有提供读取文本行的功能.
 * 
 * 二、InputStreamReader类
 * 
 * InputStreamReader 将字节流转换为字符流。是字节流通向字符流的桥梁。如果不指定字符集编码，该解码过程将使用平台默认的字符编码，如：GBK。
 * 
 * 构造方法：
 * 
 * InputStreamReader isr = new InputStreamReader(InputStream
 * in);//构造一个默认编码集的InputStreamReader类
 * 
 * InputStreamReader isr = new InputStreamReader(InputStream in,String
 * charsetName);//构造一个指定编码集的InputStreamReader类。
 * 
 * 参数 in对象通过 InputStream in = System.in;获得。//读取键盘上的数据。
 * 
 * 或者 InputStream in = new FileInputStream(String fileName);//读取文件中的数据。可以看出
 * FileInputStream 为InputStream的子类。
 * 
 * 主要方法：int read();//读取单个字符。 int read(char []cbuf);//将读取到的字符存到数组中。返回读取的字符数。
 * 
 * 三、FileWriter（少量文字） 和 BufferedWriter（大量文字）实现简单文件写操作
 * @author hulk
 */
public class FileReadWriteUtil {

	// "D:\\demo.txt"
	private static String PATH = "/home/hulk/devtools/adt-bundle-linux-x86_64-20140702/workspace/JavaTest/readme.txt";// or
	private static boolean debugMode = true; 

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// readSystemInputText();//读取键盘输入文字信息
		// testBufferReader();
		String aaaaPath = "/home/hulk/aaaa.txt";
		String aaaPath = "/home/hulk/aaa.txt";
		String text = readResourceFile(aaaaPath).toString();// 读取文件资源
		String appendtext = "\n\nAAAAAAAAAAAAaaaaaaaaaaaaaaaa\nBBBBBBBBBBBBBBBBbbbbbbbbbbbbb";
		writeText(aaaPath, text, false);
		readResourceFile(aaaPath);// 读取文件资源
	}

	private static void testBufferReader() {
		try {
			readFileNoBuffer(PATH, 2048);// 不使用缓冲区
			readFileByBuffer(PATH);// 使用缓冲区,适用于，读取完整文件
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void setDebugMode(boolean debugMode) {
		FileReadWriteUtil.debugMode = debugMode;
	}

	/**
	 * 没有缓冲区，只能使用read()方法，得指定要读取的字符长度
	 * 
	 * @throws IOException
	 */
	public static String readFileNoBuffer(String path, int readChars)
			throws IOException {
		// 读取字节流
		// InputStream in = System.in;//读取键盘的输入。
		InputStream in = new FileInputStream(path);// 读取文件的数据。
		// 将字节流向字符流的转换。要启用从字节到字符的有效转换，
		// 可以提前从底层流读取更多的字节.
		InputStreamReader isr = new InputStreamReader(in);// 读取
		char[] cha = new char[readChars];
		int len = isr.read(cha);
		String text = new String(cha, 0, len);
		log(text);
		isr.close();
		return text;
	}

	/**
	 * 使用缓冲区 可以使用缓冲区对象的 read() 和 readLine()方法。
	 * 
	 * @throws IOException
	 */
	public static StringBuffer readFileByBuffer(String path) throws IOException {
		// 读取字节流
		InputStream in = new FileInputStream(path);// 读取文件上的数据。
		InputStreamReader isr = new InputStreamReader(in);// 将字节流向字符流的转换
		BufferedReader bufr = new BufferedReader(isr);// 创建字符流缓冲区
		StringBuffer buffer = new StringBuffer();
		String line;
		while ((line = bufr.readLine()) != null) {
			buffer.append(line).append("\n");
			log(line);
		}
		isr.close();
		return buffer;
	}

	public static StringBuffer readText(String filePath) {
		BufferedReader br = null;
		StringBuffer buffer = null;
		try {
			br = new BufferedReader(new FileReader(filePath));
			String line = null;
			buffer = new StringBuffer();
			while ((line = br.readLine()) != null) {
				buffer.append(line).append("\n");
				log(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return buffer;
	}

	public static StringBuffer readtextByChar(String filePath) {
		StringBuffer text = null;
		FileReader reader = null;
		try {
			reader = new FileReader(filePath);
			int ch = 0;
			text = new StringBuffer();
			while ((ch = reader.read()) != -1) {
				text.append((char)ch);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return text;
	}

	/**
	 * 使用FileWriter类写文本文件, 仅限制少量文字
	 * Note: //使用这个构造函数时，如果存在filePath.txt文件，则先把这个文件给删除掉，然后创建新的filePath.txt
	 * @param filePath
	 * @param text
	 * @return the FileWriter object, null if throws Exception
	 */
	public static FileWriter writeText(String filePath, String text) {
		return writeText(filePath, text, false);
	}

	/**
	 * 使用FileWriter类写文本文件
	 * @param filePath
	 * @param text
	 * @param append true: 如果存在filePath.txt文件，则直接在后面追加字符串; false: 直接删除原文件
	 * @return the FileWriter object, null if throws Exception
	 */
	public static FileWriter writeText(String filePath, String text, boolean append) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(filePath, append);
			writer.write(text);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return writer;
	}

	/**
	 * 向文件写入字符串。可以时较大字符串.
	 * 注意\n不一定在各种计算机上都能产生换行的效果:
	 * <p>可以用返回的BufferedWriter对象执行一下函数实现换行:
	 * BufferedWriter out = FileReadWriteUtil.writeByBuffer("/demo.txt", "AAA", true);
	 * out.newLine();//实现换行
	 * @param filePath
	 * @param text
	 * @return The BufferedWriter object, null if throws Exception
	 */
	public static BufferedWriter writeBufferText(String filePath, String text, boolean append) {
		BufferedWriter out = null;
		 try {
			out = new BufferedWriter(new FileWriter(filePath, append));
			out.write(text);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return out;
	}

	public static boolean makeFile(String filePath) {
		File file = new File(filePath);
		if(!file.exists()) {
			if(file.isFile()) {
				File p = file.getParentFile();
				if(!p.exists()) {
					makeDir(p.getAbsolutePath());
				}
				try {
					return file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
			} else {
				System.out.print("Not a file path: " + filePath);
				return false;
			}
		} else {
			return true;
		}
	}

	public static boolean makeDir(String dir) {
		File p = new File(dir);
		if(!p.exists()) {
			return p.mkdirs();
		} else {
			return true;
		}
	}

	/**
	 * 键盘输入读取文字信息
	 */
	public static String readSystemInputText() {
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(System.in));
		System.out.println("请输入一系列文字，可包括空格，完成后按回车即可显示出来：");
		System.out.print("请在这里输入文字：");
		String text = null;
		try {
			text = bufferedReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		log("你输入的文字为：" + text);
		return text;
	}

	/**
	 * 按行读取文件信息
	 * 
	 * @param path
	 *            文件存放路径,；如果文件在项目根目录下，直接用文件名即可eg. "readme.txt"
	 */
	public static StringBuffer readResourceFile(String path) {
		StringBuffer buffer = null;
		try {
			// 读取文件，并且以utf-8的形式写出去
			BufferedReader bufread;
			BufferedInputStream bis = getResourceInputStream(path);
			if (bis == null)
				return null;
			String read;
			bufread = new BufferedReader(new InputStreamReader((bis)));
			buffer = new StringBuffer();
			while ((read = bufread.readLine()) != null) {
				buffer.append(read).append("\n");
				log(read);
			}
			bufread.close();
			bis.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return buffer;
	}

	static BufferedInputStream getResourceInputStream(String filePath) {
		try {
			return new BufferedInputStream(new FileInputStream(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static void log(String text) {
		if (debugMode) {
			System.out.println(text);
		}
	}
}
