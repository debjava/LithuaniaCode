package com.dds.core.util;
// Final and Stable
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class PWBEncryption 
{
//	Salt
	static byte[] salt = { 
		(byte)0xc7, (byte)0x73, (byte)0x21, (byte)0x8c,
		(byte)0x7e, (byte)0xc8, (byte)0xee, (byte)0x99
	};

//	Iteration count
	static int count = 20;
	
	
	//
	public PWBEncryption() 
	{
		super();
	}

	public static boolean encodeIt(String password,String inputfile,String outputfile)
	{
		
		
		try{
		
			PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());
			PBEParameterSpec pbeParamSpec = new PBEParameterSpec(salt, count);
			System.out.println("pbeParamSpec ::::: "+pbeParamSpec );
			

			SecretKeyFactory keyFac = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
			SecretKey pbeKey = keyFac.generateSecret(pbeKeySpec);

			Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
			pbeCipher.init(Cipher.ENCRYPT_MODE, pbeKey, pbeParamSpec);
//			pbeCipher.init(Cipher.ENCRYPT_MODE, pbeKey);

			FileInputStream fis = new FileInputStream(inputfile);
			byte[] input = new byte[fis.available()];
			fis.read(input);

			byte[] output = pbeCipher.doFinal(input);

			FileOutputStream fos = new FileOutputStream(outputfile);
			fos.write(output);

			return true;
		}
		catch(Exception e)
		{
			
			System.out.println(e.toString());
			return false;
		}
	}

	public static boolean decodeIt(String password,String inputfile,String outputfile)
	{
		try{
			PBEParameterSpec pbeParamSpec = new PBEParameterSpec(salt, count);
			
			PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());

			SecretKeyFactory keyFac = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
			SecretKey pbeKey = keyFac.generateSecret(pbeKeySpec);

			Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
			pbeCipher.init(Cipher.DECRYPT_MODE, pbeKey, pbeParamSpec);
//			pbeCipher.init(Cipher.DECRYPT_MODE, pbeKey);

			FileInputStream fis = new FileInputStream(inputfile);
			byte[] input = new byte[fis.available()];
			fis.read(input);

			byte[] output = pbeCipher.doFinal(input);

			FileOutputStream fos = new FileOutputStream(outputfile);
			fos.write(output);

			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	


	public static void main(String[] args) 
	{
		PWBEncryption.encodeIt("pikupiku", "D:/Turkish.txt", "D:/newTurkish.txt");
//		PWBEncryption.decodeIt("pikupiku",  "D:/newTurkish.txt" , "D:/newDecodeit.txt");

	}

}
