package com.dds.core.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.PublicKey;
import java.util.Properties;

/**This class is used to read the
 * keys from the file.
 * @author Debadatta Mishra(PIKU)
 *
 */
public class KeyReader {
	/**This method is used to obtain the
	 * string value of the Public Key
	 * from the file.
	 * @return String of {@link PublicKey}
	 */
	public static String getPublicKeyString() {
		String publicString = null;
		try {
			Properties prop = new Properties();
			String publicKeyPath = System.getProperty("user.dir")
			+ File.separator + "keys" + File.separator + "public.key";
			InputStream in = new FileInputStream(publicKeyPath);
			prop.load(in);
			publicString = prop.getProperty("key");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return publicString;
	}

	/**This method is used to obtain the
	 * String of Private Key from the file.
	 * @return String of private key
	 */
	public static String getPrivateKeyString() {
		String publicString = null;
		try {
			Properties prop = new Properties();
			String publicKeyPath = System.getProperty("user.dir")
			+ File.separator + "keys" + File.separator + "private.key";
			InputStream in = new FileInputStream(publicKeyPath);
			prop.load(in);
			publicString = prop.getProperty("key");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return publicString;
	}
}
