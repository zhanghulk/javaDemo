package com.hulk.utils.rsa;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.util.HashMap;

/**
 * RAS用来加密机密数据:密码/转账资金等等，数据不能呢个太大，否则会非常耗费资源.
 * 一般随机生成公钥和私钥，用户只需要保存好对应的密钥对，不用关心密码到底是什么.
 * 注：公钥私钥是成对出现的，通常公钥加密，私钥解密，但是，也可以私钥加密，公钥解密，可用于证书签名验证.
 * RAS非对唱加密Java实现：
 * 1．采用分组加密的方式，明文可以比较长，理论上无线长，但是太耗费时间
 * 2. 不采用分组加密，直接整个元数据加密的话，每次最多加 117 bytes, 否则：
 * javax.crypto.IllegalBlockSizeException: Data must not be longer than 117 bytes
 * 
 * 参考：　https://blog.csdn.net/centralperk/article/details/8558678
 * @author hulk 2018-06-09
 *
 */
public class RasTest2 {

	public static void main(String[] args) {
		try {
			testRsa2();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 加解密测试２
	 * 明文最长不超过 117 bytes
	 * @throws Exception
	 */
	static void testRsa2() throws Exception {
		// TODO Auto-generated method stub
		//生成公钥和私钥 (也就是随机生成的密码)
        HashMap<String, Object> map = RSAUtils.getKeys();  
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
        //明文  明文最长不超过 117 bytes
        String plainText = "zhanghao 1234567890 "
        		+ "5133626B717667424D45675047434C4 "
        		+ "5133626B717667424D45675047434C4 "
        		+ "5133626B717667424D45675047434C4 5";  
        //使用模和指数生成公钥和私钥  
        RSAPublicKey pubKey = RSAUtils.getPublicKey(modulus, public_exponent);  
        RSAPrivateKey priKey = RSAUtils.getPrivateKey(modulus, private_exponent);  
        //加密后的密文 最长不能呢个超过 117 bytes
        byte[] encrypted = RSAUtils.encryptByPublicKey(plainText.getBytes(), pubKey);
        encrypted = Base64.getEncoder().encode(encrypted);
        System.err.println("encrypted hex str:\n" + RSAUtils.byte2HexStr(encrypted));  
        
        //通过网络等等传到客户端/服务端.............
        encrypted = Base64.getDecoder().decode(encrypted);
        //解密后的明文  
        plainText = new String(RSAUtils.decryptByPrivateKey(encrypted, priKey));
        System.out.println("plainText:\n" + plainText);
	}
}
