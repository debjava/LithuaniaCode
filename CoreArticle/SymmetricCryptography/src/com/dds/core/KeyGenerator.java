package com.dds.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * This class is used to generate the secrete key and
 * stores the secret key in a file called secret.key.
 * @author Debadatta Mishra(PIKU)
 *
 */
public class KeyGenerator {
	/**This method is used to obtain the 
	 * path of the file secret.key.
	 * @return path of Key
	 */
	private static String getKeyFilePath() {
		String keyPath = null;
		try {
			String keyDirPath = System.getProperty("user.dir") + File.separator
			+ "key";
			File keyDir = new File(keyDirPath);
			if (!keyDir.exists())
				keyDir.mkdirs();
			keyPath = keyDirPath + File.separator + "secretkey.key";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return keyPath;
	}

	/**
	 * This method is used to store the
	 * secret key in a file.
	 */
	public static void storeKey() {
		try {
			Properties keyProp = new Properties();
			OutputStream out = new FileOutputStream(getKeyFilePath());
			keyProp.put("key", KeyUtil.generateSecretKey());
			keyProp
			.store(out,
					"Secret key information, do not modify the key.");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
