import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.dds.core.KeyReader;
import com.dds.core.KeyUtil;

/**This is the test harness class for the
 * encryption and decryption of the file.
 * @author Debadatta Mishra(PIKU)
 *
 */
public class FileEncryptionTest {
	
	public static void main(String[] args) throws Exception {
		String filePath = "C:/output.txt";
		String secretKeyString = KeyReader.getSecretKey();
		/*
		 * Encrypt the fileContents and write to the same file
		 */
		KeyUtil.encryptFile(filePath, secretKeyString);
		/*
		 * Decrypt the file contents and write to the same file
		 */
		KeyUtil.decryptFile(filePath, secretKeyString);
		/*
		 * Encrypt the file contents and write to another
		 * file which contains the encrypted contents.
		 */
		String encryptedFilePath = "C:/en.txt";
		InputStream in = new FileInputStream(filePath);
		OutputStream out = new FileOutputStream(encryptedFilePath);
		KeyUtil.encryptFile(in, out, secretKeyString);
		/*
		 * Decrypt the file contents and write to an another file.
		 */
		in = new FileInputStream(encryptedFilePath);
		out = new FileOutputStream(filePath);
		KeyUtil.decryptFile(in, out, secretKeyString);
	}
}
