package edud.cmpe275.termproject.websecurity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

//Credits: http://stackoverflow.com/questions/1132567/encrypt-password-in-configuration-files

public class SecurityConfig {

	private static SecretKeySpec key;
	
	public static void configureWebSecurity () throws GeneralSecurityException, IOException{
		byte[] salt = new String("12345678").getBytes();
		 int iterationCount = 40000;
		 int keyLength = 128;
		 SecretKeySpec key = createSecretKey(System.getProperty("password").toCharArray(),salt, iterationCount, keyLength);
		 String originalPassword = "secret";
	     System.out.println("Original password: " + originalPassword);
	     String encryptedPassword = encrypt(originalPassword, key);
	     System.out.println("Encrypted password: " + encryptedPassword);
	     String decryptedPassword = decrypt(encryptedPassword, key);
	     System.out.println("Decrypted password: " + decryptedPassword);	
		
	}
	
	public static String encrypt(String password) throws UnsupportedEncodingException, GeneralSecurityException{
		byte[] salt = new String("12345678").getBytes();
		 int iterationCount = 40000;
		 int keyLength = 128;
		 SecretKeySpec key = createSecretKey("password".toCharArray(),salt, iterationCount, keyLength);
		return encrypt(password, key);
	}
	
	public static String decrypt(String encryptedPassword) throws GeneralSecurityException, IOException{
		byte[] salt = new String("12345678").getBytes();
		 int iterationCount = 40000;
		 int keyLength = 128;
		 key = createSecretKey("password".toCharArray(),salt, iterationCount, keyLength);
		return decrypt(encryptedPassword, key);
	}
	
	 private static SecretKeySpec createSecretKey(char[] password, byte[] salt, int iterationCount, int keyLength) throws NoSuchAlgorithmException, InvalidKeySpecException {
	        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
	        PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount, keyLength);
	        SecretKey keyTmp = keyFactory.generateSecret(keySpec);
	        return new SecretKeySpec(keyTmp.getEncoded(), "AES");
	    }

	    private static String encrypt(String property, SecretKeySpec key) throws GeneralSecurityException, UnsupportedEncodingException {
	        Cipher pbeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	        pbeCipher.init(Cipher.ENCRYPT_MODE, key);
	        AlgorithmParameters parameters = pbeCipher.getParameters();
	        IvParameterSpec ivParameterSpec = parameters.getParameterSpec(IvParameterSpec.class);
	        byte[] cryptoText = pbeCipher.doFinal(property.getBytes("UTF-8"));
	        byte[] iv = ivParameterSpec.getIV();
	        return base64Encode(iv) + ":" + base64Encode(cryptoText);
	    }
	    
	    private static String base64Encode(byte[] bytes) {
	        return Base64.getEncoder().encodeToString(bytes);
	    }

	    private static String decrypt(String string, SecretKeySpec key) throws GeneralSecurityException, IOException {
	        String iv = string.split(":")[0];
	        String property = string.split(":")[1];
	        Cipher pbeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	        pbeCipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(base64Decode(iv)));
	        return new String(pbeCipher.doFinal(base64Decode(property)), "UTF-8");
	    }

	    private static byte[] base64Decode(String property) throws IOException {
	        return Base64.getDecoder().decode(property);
	    }
}
