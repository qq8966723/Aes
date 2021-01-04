import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

 
public class Aes {
    private static final String IV_STRING = "9305353d2d79ddjj";
 
	public static String encryptAES(String content, String key) {
		try {
			byte[] byteContent = content.getBytes("UTF-8");
			byte[] enCodeFormat = key.getBytes();
			SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");
			byte[] initParam = IV_STRING.getBytes();
			IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
			byte[] encryptedBytes = cipher.doFinal(byteContent);
			// 同样对加密后数据进行 base64 编码
			Base64.Encoder encoder = Base64.getEncoder();
			return encoder.encodeToString(encryptedBytes);
		} catch (Exception e) {
		}
		return null;
	}
 
	public static String decryptAES(String content, String key) {
		try {
			Base64.Decoder decoder = Base64.getDecoder();
			byte[] encryptedBytes = decoder.decode(content);
			byte[] enCodeFormat = key.getBytes();
			SecretKeySpec secretKey = new SecretKeySpec(enCodeFormat, "AES");
			byte[] initParam = IV_STRING.getBytes();
			IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
			byte[] result = cipher.doFinal(encryptedBytes);
			return new String(result, "UTF-8");
		} catch (Exception e) {
		}
		return null;
    }
    
    public static void main(String[] args) {
		String content = "{\"TEST\":\"TEST\"}";
		String key = "0bc4f2a1895ce342a0ad4200bdb5aabb";
		String s = encryptAES(content, key);
		System.out.println(s);
	}

}
