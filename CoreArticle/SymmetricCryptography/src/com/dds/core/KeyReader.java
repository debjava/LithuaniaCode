package com.dds.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**This is a utility class to read the 
 * contents of the secret.key file.
 * @author Debadatta Mishra(PIKU)
 *
 */
public class KeyReader {
	/**This method is used to obtain the 
	 * key String which is stored in the 
	 * file secret.key.
	 * @return the key String
	 */
	public static String getSecretKey() {
		String secretKeyString = null;
		try {
			String keyDirPath = System.getProperty("user.dir") + File.separator
			+ "key";
			String keyPath = keyDirPath + File.separator + "secretkey.key";
			Properties keyProp = new Properties();
			InputStream in = new FileInputStream(keyPath);
			keyProp.load(in);
			secretKeyString = keyProp.getProperty("key");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return secretKeyString;
	}
}
