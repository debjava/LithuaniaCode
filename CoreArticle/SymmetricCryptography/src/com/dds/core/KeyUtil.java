package com.dds.core;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**This is a utility class for all kinds
 * of useful methods related to symmetric
 * cryptography. This class only provides
 * the use of DES algorithm.You can use any
 * other symmetric algorithm similarly.
 * @author Debadatta Mishra( PIKU )
 *
 */
public class KeyUtil {
	/**
	 * Name of the algorithm
	 */
	private static String algorithm = "DES";

	/**This method is used to obtain the
	 * Secret key as a String. It is useful
	 * you can store the String in a file
	 * for all kinds of encryption and
	 * decryption.
	 * @return the SecretKey as String
	 */
	public static String generateSecretKey() {
		String secretKeyString = null;
		try {
			KeyGenerator keyGen = KeyGenerator.getInstance(algorithm);
			SecretKey secretKey = keyGen.generateKey();
			byte[] secretKeyBytes = secretKey.getEncoded();
			secretKeyString = new sun.misc.BASE64Encoder()
			.encode(secretKeyBytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return secretKeyString;
	}

	/**This method is used to obtain the SecretKey
	 * object by passing the secret key as string.
	 * @param secretKeyString of type String
	 * @return object of {@link SecretKey}
	 */
	private static SecretKey getKeyInstance(String secretKeyString) {
		SecretKey secretKey = null;
		try {
			byte[] b2 = new sun.misc.BASE64Decoder()
			.decodeBuffer(secretKeyString);
			secretKey = new SecretKeySpec(b2, algorithm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return secretKey;
	}

	/**This method is used to encrypt the same file.
	 * This method is useful when you are encrypting
	 * the contents of a file. There is no need to 
	 * create another file with the encrypted contents.
	 * @param filePath of type String indicating the path of the file
	 * @param keyString of type String indicating the secret key
	 */
	public static void encryptFile(String filePath, String keyString) {
		try {
			SecretKey key = getKeyInstance(keyString);
//			Cipher ecipher = Cipher.getInstance(algorithm);
			Cipher ecipher = Cipher.getInstance("DES/CBC/NoPadding");
			ecipher.init(Cipher.ENCRYPT_MODE, key);
			InputStream in = new FileInputStream(filePath);
			byte[] fileBytes = new byte[in.available()];
			in.read(fileBytes);
			in.close();
			OutputStream out = new FileOutputStream(filePath);
			out = new CipherOutputStream(out, ecipher);
			out.write(fileBytes);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**This method is used to decrypt the same file.
	 * This method is useful when you are decrypting
	 * the contents of a file. There is no need to 
	 * create another file with the decrypted contents.
	 * @param filePath of type String indicating the path of the file
	 * @param keyString of type String indicating the secret key
	 */
	public static void decryptFile(String filePath, String keyString) {
		try {
			SecretKey key = getKeyInstance(keyString);
//			Cipher ecipher = Cipher.getInstance(algorithm);
			Cipher ecipher = Cipher.getInstance("DES/CBC/NoPadding");
			ecipher.init(Cipher.DECRYPT_MODE, key);
			InputStream in = new FileInputStream(filePath);
			byte[] fileBytes = new byte[in.available()];
			in.read(fileBytes);
			in.close();
			OutputStream out = new FileOutputStream(filePath);
			out = new CipherOutputStream(out, ecipher);
			out.write(fileBytes);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**This method is used to encrypt the contents
	 * of a file and writing to another file. This 
	 * method is useful when you want to maintain the
	 * original contents and encrypting the contents
	 * and giving to another person.
	 * @param in of type {@link InputStream}
	 * @param out of type {@link OutputStream}
	 * @param keyString of type String indicating the SecretKey
	 */
	public static void encryptFile(InputStream in, OutputStream out,
			String keyString) {

		byte[] buf = new byte[1024];
		try {
			SecretKey key = getKeyInstance(keyString);
			Cipher ecipher = Cipher.getInstance(algorithm);
			ecipher.init(Cipher.ENCRYPT_MODE, key);

			// Bytes written to out will be encrypted
			out = new CipherOutputStream(out, ecipher);
			// Read in the cleartext bytes and write to out to encrypt
			int numRead = 0;
			while ((numRead = in.read(buf)) >= 0) {
				out.write(buf, 0, numRead);
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**This method is used to decrypt the contents
	 * of a file and writing to another file. This 
	 * method is useful when you want to decrypt
	 * the contents and writing to a another file.
	 * @param in of type {@link InputStream}
	 * @param out of type {@link OutputStream}
	 * @param keyString of type String indicating the SecretKey
	 */
	public static void decryptFile(InputStream in, OutputStream out,
			String keyString) {
		byte[] buf = new byte[1024];
		try {
			SecretKey key = getKeyInstance(keyString);
			Cipher dcipher = Cipher.getInstance(algorithm);
			dcipher.init(Cipher.DECRYPT_MODE, key);
			in = new CipherInputStream(in, dcipher);
			int numRead = 0;
			while ((numRead = in.read(buf)) >= 0) {
				out.write(buf, 0, numRead);
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**This method is used to encrypt the String.
	 * @param contents of type String
	 * @param keyString of type String indicating the Secret key as String
	 * @return a String encrypted contents
	 */
	public static String getEncryptedContents(String contents, String keyString) {
		String encryptedString = null;
		try {
			byte[] contentBytes = contents.getBytes();
			SecretKey key = getKeyInstance(keyString);
			Cipher ecipher = Cipher.getInstance(algorithm);
			ecipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] encryptedBytes = ecipher.doFinal(contentBytes);
			System.out.println("Encrypted bytes length--------"+encryptedBytes.length);
			encryptedString = new sun.misc.BASE64Encoder()
			.encode(encryptedBytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encryptedString;
	}

	/**This method is used to decrypt the String.
	 * @param contents of type String
	 * @param keyString of type String indicating the Secret key as String
	 * @return a String encrypted contents
	 */
	public static String getDecryptedContents(String contents, String keyString) {
		String decryptedString = null;
		try {
			byte[] contentBytes = new sun.misc.BASE64Decoder()
			.decodeBuffer(contents);
			SecretKey key = getKeyInstance(keyString);
			Cipher ecipher = Cipher.getInstance(algorithm);
			ecipher.init(Cipher.DECRYPT_MODE, key);
			byte[] encryptedBytes = ecipher.doFinal(contentBytes);
			decryptedString = new String(encryptedBytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decryptedString;
	}
}
