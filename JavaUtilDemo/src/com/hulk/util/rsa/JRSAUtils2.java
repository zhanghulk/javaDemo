package com.hulk.util.rsa;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.HashMap;

import javax.crypto.Cipher;

/**
 * RAS用来加密机密数据:密码/转账资金等等，数据不能呢个太大，否则会非常耗费资源.
 * 一般随机生成公钥和私钥，用户只需要保存好对应的密钥对，不用关心密码到底是什么.
 * RAS非对唱加密Java实现：
 * 1．采用分组加密的方式，明文可以比较长，理论上无线长，但是太耗费时间
 * 2. 不采用分组加密，直接整个元数据加密的话，每次最多加 117 bytes, 否则：
 * javax.crypto.IllegalBlockSizeException: Data must not be longer than 117 bytes
 * 
 * 参考：　https://blog.csdn.net/centralperk/article/details/8558678
 * @author hulk 2018-06-09
 *
 */
public class JRSAUtils2 {

	public static void main(String[] args) {
		try {
			testRsa();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 加解密测试２
	 * 采用分组加密的方式，明文可以比较长，理论上无线长，但是太耗费时间
	 * @throws Exception
	 */
	static void testRsa() throws Exception {
		// TODO Auto-generated method stub
		//生成公钥和私钥 (也就是随机生成的密码)
        HashMap<String, Object> map = getKeys();  
        RSAPublicKey publicKey = (RSAPublicKey) map.get("public");  
        RSAPrivateKey privateKey = (RSAPrivateKey) map.get("private");  
          
        //模  
        String modulus = publicKey.getModulus().toString();  
        System.out.println("modulus: " + modulus);
        //公钥指数  
        String public_exponent = publicKey.getPublicExponent().toString(); 
        System.out.println("public_exponent: " + public_exponent);
        //私钥指数  
        String private_exponent = privateKey.getPrivateExponent().toString(); 
        System.out.println("private_exponent: " + private_exponent);
        //明文  
        String plainText = "zhanghao 123456789 "
        		+ "5133626B717667424D45675047434C4E6"
        		+ "E497A62576778474C72554878573161377"
        		+ "730777456654D687349474E4A5A4448347"
        		+ "8737841764E51552B7A3671314E6347686"
        		+ "E53482F765677683772466C7A3550763430"
        		+ "6A644A69662F5044713679516A3657684659"
        		+ "47514444424B6A47323052684E454B5A6A487"
        		+ "2336B58616D3073476A2F2F414A645153326F"
        		+ "35525958644E674C51485A646E4D684D585458"
        		+ "424A6F6461574A77416A71553D";  
        //使用模和指数生成公钥和私钥  
        RSAPublicKey pubKey = getPublicKey(modulus, public_exponent);  
        RSAPrivateKey priKey = getPrivateKey(modulus, private_exponent);  
        //加密后的密文 : 下面采用分组加密的方式，明文可以比较长
        String encrypted = encryptByPublicKey(plainText, pubKey);
        encrypted = Base64.getEncoder().encodeToString(encrypted.getBytes());
        System.err.println("encrypted:\n" + encrypted);  
        
        //通过网络等等传到客户端/服务端.............
        encrypted = new String(Base64.getDecoder().decode(encrypted));
        //解密后的明文  
        plainText = decryptByPrivateKey(encrypted, priKey);
        System.out.println("plainText:\n" + plainText);
	}
	
	/**
	 * 生成公钥和私钥
	 * 可以理解为随机生成密码，用户只需要保存好对应的密钥对，不用关心密码到底是什么.
	 * @throws NoSuchAlgorithmException
	 * 
	 */
	public static HashMap<String, Object> getKeys() throws NoSuchAlgorithmException {
		HashMap<String, Object> meys = new HashMap<String, Object>();
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
		keyPairGen.initialize(1024);
		KeyPair keyPair = keyPairGen.generateKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		meys.put("public", publicKey);
		meys.put("private", privateKey);
		return meys;
	}

	/**
	 * 使用模和指数生成RSA公钥 
	 * 注意：【此代码用了默认补位方式，为RSA/None/PKCS1Padding，
	 * 不同JDK默认的补位方式可能不同，如Android默认是RSA/None/NoPadding】
	 * 
	 * @param modulus
	 *            模
	 * @param exponent
	 *            指数
	 * @return
	 */
	public static RSAPublicKey getPublicKey(String modulus, String exponent) {
		try {
			BigInteger b1 = new BigInteger(modulus);
			BigInteger b2 = new BigInteger(exponent);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			RSAPublicKeySpec keySpec = new RSAPublicKeySpec(b1, b2);
			return (RSAPublicKey) keyFactory.generatePublic(keySpec);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 使用模和指数生成RSA私钥 
	 * 注意：【此代码用了默认补位方式，为RSA/None/PKCS1Padding，
	 * 不同JDK默认的补位方式可能不同，如Android默认是RSA/None/NoPadding】
	 * 
	 * @param modulus
	 *            模
	 * @param exponent
	 *            指数
	 * @return
	 */
	public static RSAPrivateKey getPrivateKey(String modulus, String exponent) {
		try {
			BigInteger b1 = new BigInteger(modulus);
			BigInteger b2 = new BigInteger(exponent);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(b1, b2);
			return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 公钥加密
	 * 注：采用分组加密的方式，明文可以比较长，理论上无线长，但是太耗费时间
	 * @param data
	 * @param publicKey
	 * @return
	 * @throws Exception
	 */
	public static String encryptByPublicKey(String data, RSAPublicKey publicKey) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		// 模长
		int key_len = publicKey.getModulus().bitLength() / 8;
		// 加密数据长度 <= 模长-11
		String[] datas = splitString(data, key_len - 11);
		String mi = "";
		// 如果明文长度大于模长-11则要分组加密
		for (String s : datas) {
			mi += bcd2Str(cipher.doFinal(s.getBytes()));
		}
		return mi;
	}

	/**
	 * 私钥解密
	 * 
	 * @param data
	 * @param privateKey
	 * @return
	 * @throws Exception
	 */
	public static String decryptByPrivateKey(String data, RSAPrivateKey privateKey) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		// 模长
		int key_len = privateKey.getModulus().bitLength() / 8;
		byte[] bytes = data.getBytes();
		byte[] bcd = ASCII_To_BCD(bytes, bytes.length);
		System.err.println(bcd.length);
		// 如果密文长度大于模长则要分组解密
		String ming = "";
		byte[][] arrays = splitArray(bcd, key_len);
		for (byte[] arr : arrays) {
			ming += new String(cipher.doFinal(arr));
		}
		return ming;
	}
	
	/**
	 * byte[]数据加密方式，整体加密，没有分组.
	 * 注：data太长，117 byres以内，否则会出现异常：
	 * javax.crypto.IllegalBlockSizeException: Data must not be longer than 117 bytes
	 * @param data　　max 117 bytes
	 * @param publicKey
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPublicKey(byte[] data, RSAPublicKey publicKey) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		return cipher.doFinal(data);
	}
	
	/**
	 * byte[]数据解密方式，整体解密，没有分组.
	 * 注：data太长，117 byres以内，否则会出现异常：
	 * javax.crypto.IllegalBlockSizeException: Data must not be longer than 117 bytes
	 * @param data
	 * @param privateKey
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPrivateKey(byte[] data, RSAPrivateKey privateKey) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		return cipher.doFinal(data);
	}

	/**
	 * ASCII码转BCD码： ＡASCII码的二进制转换为二进制
	 * 
	 */
	public static byte[] ASCII_To_BCD(byte[] ascii, int asc_len) {
		byte[] bcd = new byte[asc_len / 2];
		int j = 0;
		for (int i = 0; i < (asc_len + 1) / 2; i++) {
			bcd[i] = asc_to_bcd(ascii[j++]);
			bcd[i] = (byte) (((j >= asc_len) ? 0x00 : asc_to_bcd(ascii[j++])) + (bcd[i] << 4));
		}
		return bcd;
	}

	public static byte asc_to_bcd(byte asc) {
		byte bcd;

		if ((asc >= '0') && (asc <= '9'))
			bcd = (byte) (asc - '0');
		else if ((asc >= 'A') && (asc <= 'F'))
			bcd = (byte) (asc - 'A' + 10);
		else if ((asc >= 'a') && (asc <= 'f'))
			bcd = (byte) (asc - 'a' + 10);
		else
			bcd = (byte) (asc - 48);
		return bcd;
	}

	/**
	 * BCD转字符串: 把二进制字节流转换为16进制字符串
	 */
	public static String bcd2Str(byte[] bytes) {
		char temp[] = new char[bytes.length * 2], val;

		for (int i = 0; i < bytes.length; i++) {
			val = (char) (((bytes[i] & 0xf0) >> 4) & 0x0f);
			temp[i * 2] = (char) (val > 9 ? val + 'A' - 10 : val + '0');

			val = (char) (bytes[i] & 0x0f);
			temp[i * 2 + 1] = (char) (val > 9 ? val + 'A' - 10 : val + '0');
		}
		return new String(temp);
	}

	/**
	 * 拆分字符串: 按照给定的长度，把字符串拆分为数组
	 */
	public static String[] splitString(String string, int len) {
		int x = string.length() / len;
		int y = string.length() % len;
		int z = 0;
		if (y != 0) {
			z = 1;
		}
		String[] strings = new String[x + z];
		String str = "";
		for (int i = 0; i < x + z; i++) {
			if (i == x + z - 1 && y != 0) {
				str = string.substring(i * len, i * len + y);
			} else {
				str = string.substring(i * len, i * len + len);
			}
			strings[i] = str;
		}
		return strings;
	}

	/**
	 * 拆分数组
	 */
	public static byte[][] splitArray(byte[] data, int len) {
		int x = data.length / len;
		int y = data.length % len;
		int z = 0;
		if (y != 0) {
			z = 1;
		}
		byte[][] arrays = new byte[x + z][];
		byte[] arr;
		for (int i = 0; i < x + z; i++) {
			arr = new byte[len];
			if (i == x + z - 1 && y != 0) {
				System.arraycopy(data, i * len, arr, 0, y);
			} else {
				System.arraycopy(data, i * len, arr, 0, len);
			}
			arrays[i] = arr;
		}
		return arrays;
	}
	
	/**
     * 字节数组转化为大写16进制字符串
     *
     * @param b
     * @return
     */
    public static String byte2HexStr(byte[] b) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            String s = Integer.toHexString(b[i] & 0xFF);
            if (s.length() == 1) {
                sb.append("0");
            }
            sb.append(s.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 16进制字符串转字节数组
     * 注：一个byte可以存放两个英文字符
     * @param s
     * @return
     */
    public static byte[] str2ByteArray(String s) {
        int byteArrayLength = s.length() / 2;
        byte[] b = new byte[byteArrayLength];
        for (int i = 0; i < byteArrayLength; i++) {
            byte b0 = (byte) Integer.valueOf(s.substring(i * 2, i * 2 + 2), 16)
                    .intValue();
            b[i] = b0;
        }

        return b;
    }
}
