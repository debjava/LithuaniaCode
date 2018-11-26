package com.dds.core.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
//Final and stable
public class StringEncrypter 
{
	public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";

	public static final String DES_ENCRYPTION_SCHEME = "DES";

	public static final String DEFAULT_ENCRYPTION_KEY = "This is a fairly long phrase used to encrypt";

	private KeySpec keySpec;

	private SecretKeyFactory keyFactory;

	private Cipher cipher;

	public StringEncrypter(String encryptionScheme)
	{
		this(encryptionScheme, DEFAULT_ENCRYPTION_KEY);
	}

	public StringEncrypter(String encryptionScheme, String encryptionKey)
	{
		if (encryptionKey == null)
			throw new IllegalArgumentException("encryption key was null");
		if (encryptionKey.trim().length() < 24)
			throw new IllegalArgumentException(
					"encryption key was less than 24 characters");
		try {
			byte[] keyAsBytes = encryptionKey.getBytes("UTF8");
			
			if (encryptionScheme.equals(DESEDE_ENCRYPTION_SCHEME)) 
			{
				keySpec = new DESedeKeySpec(keyAsBytes);
			}
			else if (encryptionScheme.equals(DES_ENCRYPTION_SCHEME)) {
				keySpec = new DESKeySpec(keyAsBytes);
			}
			else 
			{
				throw new IllegalArgumentException(
						"Encryption scheme not supported: " + encryptionScheme);
			}
			keyFactory = SecretKeyFactory.getInstance(encryptionScheme);
			cipher = Cipher.getInstance(encryptionScheme);
		} catch (InvalidKeyException e) 
		{
			e.printStackTrace();
		}
		catch (UnsupportedEncodingException e) 
		{
			e.printStackTrace();
		}
		catch (NoSuchAlgorithmException e) 
		{
			e.printStackTrace();
		} 
		catch (NoSuchPaddingException e) 
		{
			e.printStackTrace();
		}
	}

	public String encrypt(String unencryptedString) 
	{
		if (unencryptedString == null || unencryptedString.trim().length() == 0)
			throw new IllegalArgumentException(
					"unencrypted string was null or empty");
		String encryptedString = null;
		try {
			SecretKey key = keyFactory.generateSecret(keySpec);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] cleartext = unencryptedString.getBytes("UTF8");
			byte[] ciphertext = cipher.doFinal(cleartext);
			BASE64Encoder base64encoder = new BASE64Encoder();
			encryptedString = base64encoder.encode(ciphertext);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return encryptedString;
	}

	public String decrypt(String encryptedString) 
	{
		if (encryptedString == null || encryptedString.trim().length() <= 0)
			throw new IllegalArgumentException(
					"encrypted string was null or empty");
		String decryptedString = null;
		try {
			SecretKey key = keyFactory.generateSecret(keySpec);
			cipher.init(Cipher.DECRYPT_MODE, key);
			BASE64Decoder base64decoder = new BASE64Decoder();
			byte[] cleartext = base64decoder.decodeBuffer(encryptedString);
			byte[] ciphertext = cipher.doFinal(cleartext);
			decryptedString = new String(ciphertext);//bytes2String(ciphertext);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return decryptedString;
	}
	
	public static void main(String[] args) 
	{
		try 
		{
			String key = "0123456fshjh78dffd90123fdgfd456789dgfgf0123utyuy654789";
			StringEncrypter en = new StringEncrypter("DESede",key);
			String ss = en.encrypt("pikupiku");
			System.out.println("SS:::"+ss);
			String deSS = en.decrypt("b6JNnzdz4bwBCzKN3oZnNg==");
			System.out.println("SS:::"+deSS);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}	

