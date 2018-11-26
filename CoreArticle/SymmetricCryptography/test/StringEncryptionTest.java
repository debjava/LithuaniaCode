import com.dds.core.KeyReader;
import com.dds.core.KeyUtil;

/**This test harness class is used to
 * encrypt and decrypt the String.
 * @author Debadatta Mishra(PIKU)
 * 
 */
public class StringEncryptionTest {
	public static void main(String[] args) {
		String originalString = "12345678";
		String secretKeyString = KeyReader.getSecretKey();
		String encryptedStringContents = KeyUtil.getEncryptedContents(
				originalString, secretKeyString);
		System.out.println("Original String------" + originalString);
		System.out.println("Encrypted String-----" + encryptedStringContents);
		/*
		 * Now get back to your originalString by decryption
		 */
		String decryptedString = KeyUtil.getDecryptedContents(
				encryptedStringContents, secretKeyString);
		System.out.println("Decrypted String-----" + decryptedString);
	}
}
