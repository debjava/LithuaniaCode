package com.dds.core.security;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Properties;

/**This class is used to generate the
 * Private and Public key and stores
 * them in files.
 * @author Debadatta Mishra(PIKU)
 *
 */
public class KeyGenerator {
	/**This method is used to obtain the
	 * path of the keys directory where
	 * Private and Public key files are
	 * stored.
	 * @return path of the keys directory
	 */
	private static String getKeyFilePath() {
		String keyDirPath = null;
		try {
			keyDirPath = System.getProperty("user.dir") + File.separator
			+ "keys";
			File keyDir = new File(keyDirPath);
			if (!keyDir.exists())
				keyDir.mkdirs();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return keyDirPath;
	}

	/**
	 * This method is used to generate the
	 * Private and Public keys.
	 */
	public static void generateKeys() {
		Properties publicProp = new Properties();
		Properties privateProp = new Properties();
		try {
			OutputStream pubOut = new FileOutputStream(getKeyFilePath()
					+ File.separator + "public.key");
			OutputStream priOut = new FileOutputStream(getKeyFilePath()
					+ File.separator + "private.key");

			SecurityUtil secureUtil = new SecurityUtil();
			secureUtil.invokeKeys();

			PublicKey publicKey = secureUtil.getPublicKey();
			PrivateKey privateKey = secureUtil.getPrivateKey();

			String publicString = secureUtil.getPublicKeyString(publicKey);
			String privateString = secureUtil.getPrivateKeyString(privateKey);

			publicProp.put("key", publicString);
			publicProp.store(pubOut, "Public Key Info");

			privateProp.put("key", privateString);
			privateProp.store(priOut, "Private Key Info");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
