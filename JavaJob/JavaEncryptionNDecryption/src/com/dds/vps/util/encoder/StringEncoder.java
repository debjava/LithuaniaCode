package com.dds.vps.util.encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class StringEncoder 
{
	final String encodedValue = "DES";
	private Cipher ecipher;
	public StringEncoder(SecretKey key) 
	{
        try 
        {
            ecipher = Cipher.getInstance(encodedValue);
            ecipher.init(Cipher.ENCRYPT_MODE, key);
        }
        catch( Exception e )
        {
        	e.printStackTrace();
        }
	}     
	public String encodeString( String actualString_parm )
	{
		String encodedString = null;
		try
		{
			 byte[] utf8 = actualString_parm.getBytes("UTF8");
	         byte[] enc = ecipher.doFinal(utf8);
	            // Encode bytes to base64 to get a string
	          encodedString = new sun.misc.BASE64Encoder().encode(enc);
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return encodedString;
	}
	
	public static void main(String[] args) 
	{
		SecretKey key;
		try
		{
			key = KeyGenerator.getInstance("DES").generateKey();
			StringEncoder encoder = new StringEncoder(key);
			final String encryptedValue = encoder.encodeString("nandu");
			System.out.println("Encrypted value ::: "+encryptedValue);
			
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		
	}

}
