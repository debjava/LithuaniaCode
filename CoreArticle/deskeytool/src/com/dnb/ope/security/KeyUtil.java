package com.dnb.ope.security;

import java.io.InputStream;
import java.io.OutputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class KeyUtil 
{
	public static String generateSecretKey()
	{
		String secretKeyString = null;
		try
		{
			KeyGenerator keyGen = KeyGenerator.getInstance("DES");
			SecretKey secretKey = keyGen.generateKey();
			byte[] secretKeyBytes = secretKey.getEncoded();
			secretKeyString = new sun.misc.BASE64Encoder()
			.encode(secretKeyBytes);
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return secretKeyString;
	}

	public static String generateSecretKey( String algorithm )
	{
		String secretKeyString = null;
		try
		{
			KeyGenerator keyGen = KeyGenerator.getInstance( algorithm );
			SecretKey secretKey = keyGen.generateKey();
			byte[] secretKeyBytes = secretKey.getEncoded();
			secretKeyString = new sun.misc.BASE64Encoder()
			.encode(secretKeyBytes);
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return secretKeyString;
	}

	private static SecretKey getKeyInstance( String secretKeyString )
	{
		SecretKey secretKey = null;
		try
		{
			byte[] b2 = new sun.misc.BASE64Decoder().decodeBuffer(secretKeyString);
			secretKey = new SecretKeySpec(b2,"DES");
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return secretKey;
	}
	
	private static SecretKey getKeyInstance( String secretKeyString , String algorithm )
	{
		SecretKey secretKey = null;
		try
		{
			byte[] b2 = new sun.misc.BASE64Decoder().decodeBuffer(secretKeyString);
			secretKey = new SecretKeySpec( b2,algorithm );
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return secretKey;
	}

	public static void encryptFile(InputStream in, OutputStream out , String keyString )
	{

		byte[] buf = new byte[1024];
		try 
		{
			SecretKey key = getKeyInstance(keyString);
			Cipher ecipher = Cipher.getInstance("DES");
			ecipher.init(Cipher.ENCRYPT_MODE, key);

			// Bytes written to out will be encrypted
			out = new CipherOutputStream(out, ecipher);
			// Read in the cleartext bytes and write to out to encrypt
			int numRead = 0;
			while ((numRead = in.read(buf)) >= 0) {
				out.write(buf, 0, numRead);
			}
			out.close();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public static void decryptFile(InputStream in, OutputStream out , String keyString ) 
	{
		byte[] buf = new byte[1024];
		try
		{
			SecretKey key = getKeyInstance(keyString);
			Cipher dcipher = Cipher.getInstance("DES");
			dcipher.init(Cipher.DECRYPT_MODE, key);

			// Bytes read from in will be decrypted
			in = new CipherInputStream(in, dcipher);

			// Read in the decrypted bytes and write the cleartext to out
			int numRead = 0;
			while ((numRead = in.read(buf)) >= 0) 
			{
				out.write(buf, 0, numRead);
			}
			out.close();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static String getEncryptedContents( String contents , String keyString )
	{
		String encryptedString = null;
		try
		{
			byte[] contentBytes = contents.getBytes();
			SecretKey key = getKeyInstance(keyString);
			Cipher ecipher = Cipher.getInstance("DES");
			ecipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] encryptedBytes = ecipher.doFinal( contentBytes );
			encryptedString = new sun.misc.BASE64Encoder()
					.encode(encryptedBytes);
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return encryptedString;
	}
	
	public static String getDecryptedContents( String contents , String keyString )
	{
		String decryptedString = null;
		try
		{
			byte[] contentBytes = new sun.misc.BASE64Decoder().decodeBuffer( contents );
			SecretKey key = getKeyInstance(keyString);
			Cipher ecipher = Cipher.getInstance("DES");
			ecipher.init(Cipher.DECRYPT_MODE, key);
			byte[] encryptedBytes = ecipher.doFinal( contentBytes );
//			decryptedString = new sun.misc.BASE64Encoder()
//					.encode(encryptedBytes);
			decryptedString = new String( encryptedBytes );
			
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return decryptedString;
	}


}
