package com.hulk.utils.rsa;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.util.HashMap;

public class RasTest {

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
        RSAPublicKey pubKey = RSAUtils.getPublicKey(modulus, public_exponent);  
        RSAPrivateKey priKey = RSAUtils.getPrivateKey(modulus, private_exponent);  
        //加密后的密文 : 下面采用分组加密的方式，明文可以比较长
        String encrypted = RSAUtils.encryptByPublicKey(plainText, pubKey);
        encrypted = Base64.getEncoder().encodeToString(encrypted.getBytes());
        System.err.println("encrypted hex str:\n" + encrypted);  
        
        //通过网络等等传到客户端/服务端.............
        encrypted = new String(Base64.getDecoder().decode(encrypted));
        //解密后的明文  
        plainText = RSAUtils.decryptByPrivateKey(encrypted, priKey);
        System.out.println("plainText:\n" + plainText);
	}
}
