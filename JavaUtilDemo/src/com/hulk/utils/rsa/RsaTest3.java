package com.hulk.utils.rsa;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.util.HashMap;

public class RsaTest3 {
	public static void main(String[] args) {
		try {
			testRsa();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 加解密测试　
	 * （私钥加密，公钥解密，　可用于证书签名验证，　　签发证书之人用私钥签名，其他人用公钥也可以解密验证）
	 * 采用分组加密的方式，明文可以比较长，理论上无线长，但是太耗费时间
	 * @throws Exception
	 */
	static void testRsa() throws Exception {
		// TODO Auto-generated method stub
		//生成公钥和私钥 (也就是随机生成的密码)
        HashMap<String, Object> map = RSAUtils3.getKeys();  
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
        String plainText = "zhanghao 123456789 hahahaha";  
        //使用模和指数生成公钥和私钥  
        RSAPublicKey pubKey = RSAUtils3.getPublicKey(modulus, public_exponent);  
        RSAPrivateKey priKey = RSAUtils3.getPrivateKey(modulus, private_exponent);  
        //加密后的密文 : 下面采用分组加密的方式，明文可以比较长
        String encrypted = RSAUtils3.encryptByPublicKey(plainText, priKey);
        encrypted = Base64.getEncoder().encodeToString(encrypted.getBytes());
        System.err.println("encrypted hex str:\n" + encrypted);  
        
        //通过网络等等传到客户端/服务端.............
        encrypted = new String(Base64.getDecoder().decode(encrypted));
        //解密后的明文  
        plainText = RSAUtils3.decryptByPrivateKey(encrypted, pubKey);
        System.out.println("plainText:\n" + plainText);
	}
}
