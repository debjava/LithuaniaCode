package com.dds.core.security;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.sun.crypto.provider.SunJCE;

/**This is a utility class which provides
 * convenient method for security. This 
 * class provides the way where you can
 * encrypt and decrypt the String having
 * more than 117 bytes for RSA algorithm
 * which is an asymmetric one.
 * @author Debadatta Mishra(PIKU)
 *
 */
public class SecurityUtil {
	/**
	 * Object of type {@link KeyPair}
	 */
	private KeyPair keyPair;
	/**
	 * String variable which denotes the algorithm
	 */
	private static final String ALGORITHM = "RSA";
	/**
	 * varibale for the keysize
	 */
	private static final int KEYSIZE = 1024;

	/**
	 * Default constructor
	 */
	public SecurityUtil() {
		super();
		Security.addProvider(new SunJCE());
	}

	/**
	 * This method is used to generate
	 * the key pair.
	 */
	public void invokeKeys() {
		try {
			KeyPairGenerator keypairGenerator = KeyPairGenerator
			.getInstance(ALGORITHM);
			keypairGenerator.initialize(KEYSIZE);
			keyPair = keypairGenerator.generateKeyPair();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**This method is used to obtain the String
	 * representation of the PublicKey.
	 * @param publicKey of type {@link PublicKey}
	 * @return PublicKey as a String
	 */
	public String getPublicKeyString(PublicKey publicKey) {
		return new BASE64Encoder().encode(publicKey.getEncoded());
	}

	/**This method is used to obtain the String
	 * representation of the PrivateKey.
	 * @param privateKey of type {@link PrivateKey}
	 * @return PrivateKey as a String
	 */
	public String getPrivateKeyString(PrivateKey privateKey) {
		return new BASE64Encoder().encode(privateKey.getEncoded());
	}

	/**This method is used to obtain the 
	 * {@link PrivateKey} object from the
	 * String representation.
	 * @param key of type String
	 * @return {@link PrivateKey}
	 * @throws Exception
	 */
	public PrivateKey getPrivateKeyFromString(String key) throws Exception {
		PrivateKey privateKey = null;
		try {
			KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
			EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(
					new BASE64Decoder().decodeBuffer(key));
			privateKey = keyFactory.generatePrivate(privateKeySpec);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return privateKey;
	}

	/**This method is used to obtain the {@link PublicKey}
	 * from the String representation of the Public Key.
	 * @param key of type String
	 * @return {@link PublicKey}
	 * @throws Exception
	 */
	public PublicKey getPublicKeyFromString(String key) throws Exception {
		PublicKey publicKey = null;
		try {
			KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
			EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(
					new BASE64Decoder().decodeBuffer(key));
			publicKey = keyFactory.generatePublic(publicKeySpec);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return publicKey;
	}

	/**This method is used to obtain the 
	 * encrypted contents from the original
	 * contents by passing the {@link PublicKey}.
	 * This method is useful when the byte is more
	 * than 117.
	 * @param text of type String
	 * @param key of type {@link PublicKey}
	 * @return encrypted value as a String
	 * @throws Exception
	 */
	public String getEncryptedValue(String text, PublicKey key)
	throws Exception {
		String encryptedText;
		try {
			byte[] textBytes = text.getBytes("UTF8");
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key);

			int textBytesChunkLen = 100;
			int encryptedChunkNum = (textBytes.length - 1) / textBytesChunkLen
			+ 1;

			// RSA returns 128 bytes as output for 100 text bytes
			int encryptedBytesChunkLen = 128;
			int encryptedBytesLen = encryptedChunkNum * encryptedBytesChunkLen;
			System.out.println("Encrypted bytes length-------"
					+ encryptedBytesChunkLen);
			// Define the Output array.
			byte[] encryptedBytes = new byte[encryptedBytesLen];

			int textBytesChunkIndex = 0;
			int encryptedBytesChunkIndex = 0;

			for (int i = 0; i < encryptedChunkNum; i++) {
				if (i < encryptedChunkNum - 1) {
					encryptedBytesChunkIndex = encryptedBytesChunkIndex
					+ cipher.doFinal(textBytes, textBytesChunkIndex,
							textBytesChunkLen, encryptedBytes,
							encryptedBytesChunkIndex);

					textBytesChunkIndex = textBytesChunkIndex
					+ textBytesChunkLen;
				} else {
					cipher.doFinal(textBytes, textBytesChunkIndex,
							textBytes.length - textBytesChunkIndex,
							encryptedBytes, encryptedBytesChunkIndex);
				}
			}
			encryptedText = new BASE64Encoder().encode(encryptedBytes);
		} catch (Exception e) {
			throw e;
		}
		return encryptedText;
	}

	/**This method is used to decrypt the contents.
	 * This method is useful when the size of the
	 * bytes is more than 117.
	 * @param text of type String indicating the 
	 * encrypted contents.
	 * @param key of type {@link PrivateKey}
	 * @return decrypted value as a String
	 */
	public String getDecryptedValue(String text, PrivateKey key) {
		String result = null;
		try {
			byte[] encryptedBytes = new BASE64Decoder().decodeBuffer(text);
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.DECRYPT_MODE, key);

			int encryptedByteChunkLen = 128;
			int encryptedChunkNum = encryptedBytes.length
			/ encryptedByteChunkLen;
			int decryptedByteLen = encryptedChunkNum * encryptedByteChunkLen;
			byte[] decryptedBytes = new byte[decryptedByteLen];
			int decryptedIndex = 0;
			int encryptedIndex = 0;

			for (int i = 0; i < encryptedChunkNum; i++) {
				if (i < encryptedChunkNum - 1) {
					decryptedIndex = decryptedIndex
					+ cipher.doFinal(encryptedBytes, encryptedIndex,
							encryptedByteChunkLen, decryptedBytes,
							decryptedIndex);
					encryptedIndex = encryptedIndex + encryptedByteChunkLen;
				} else {
					decryptedIndex = decryptedIndex
					+ cipher.doFinal(encryptedBytes, encryptedIndex,
							encryptedBytes.length - encryptedIndex,
							decryptedBytes, decryptedIndex);
				}
			}
			result = new String(decryptedBytes).trim();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**This method is used obtain the
	 * {@link PublicKey}
	 * @return {@link PublicKey}
	 */
	public PublicKey getPublicKey() {
		return keyPair.getPublic();
	}

	/**This method is used to obtain
	 * the {@link PrivateKey}
	 * @return {@link PrivateKey}
	 */
	public PrivateKey getPrivateKey() {
		return keyPair.getPrivate();
	}
}
