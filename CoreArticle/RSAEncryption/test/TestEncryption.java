import java.security.PrivateKey;
import java.security.PublicKey;

import com.dds.core.security.KeyReader;
import com.dds.core.security.SecurityUtil;

/**
 * This is a test harness class for encryption and decryption.
 * 
 * @author Debadatta Mishra(PIKU)
 * 
 */
public class TestEncryption {

	public static void main(String[] args) {
		String privateKeyString = KeyReader.getPrivateKeyString();
		SecurityUtil securityUtil = new SecurityUtil();
		String publicKeyString = KeyReader.getPublicKeyString();
		try {
			PublicKey publicKey = securityUtil
			.getPublicKeyFromString(publicKeyString);
			PrivateKey privateKey = securityUtil
			.getPrivateKeyFromString(privateKeyString);
			String originalValue = "ABCD1234";

			String encryptedValue = securityUtil.getEncryptedValue(
					originalValue, publicKey);
			System.out.println("EncryptedValue-----" + encryptedValue);
			String decryptedValue = securityUtil.getDecryptedValue(
					encryptedValue, privateKey);
			System.out.println("Original Value------" + decryptedValue);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
