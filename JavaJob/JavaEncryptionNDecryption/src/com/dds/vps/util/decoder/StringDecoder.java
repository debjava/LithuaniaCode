package com.dds.vps.util.decoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import com.dds.vps.util.encoder.StringEncoder;

public class StringDecoder 
{
	final String encodedValue = "DES";
	private Cipher decipher;
	private Cipher ecipher;
	public StringDecoder(SecretKey key)
	{

        try 
        {
        	 ecipher = Cipher.getInstance(encodedValue);
             ecipher.init(Cipher.ENCRYPT_MODE, key);
         
            decipher = Cipher.getInstance(encodedValue);
            decipher.init(Cipher.ENCRYPT_MODE, key);
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
	
	public String decodeString(String encodedString_parm) 
	{
		System.out.println("Encode String :::: "+encodedString_parm);
		String decodedValue = null;
        try 
        {
            byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(encodedString_parm);
            byte[] utf8 = decipher.doFinal(dec);
            decodedValue = new String(utf8, "UTF8");
        } 
        catch (Exception e) 
        {
        	e.printStackTrace();
        }
        return decodedValue ;
    }
	
	public static void main(String[] args) 
	{
		SecretKey key;
		
		try
		{
			key = KeyGenerator.getInstance("DES").generateKey();
			StringDecoder decoder = new StringDecoder(key);
			
			final String encryptedValue = decoder.encodeString("nandu");
			System.out.println("Nandu Encrypted Value :::: "+encryptedValue);
			
//			StringDecoder decoder = new StringDecoder(key);
			final String decryptedValue = decoder.decodeString(encryptedValue);
			System.out.println("Nandu Decrypted value ::: "+decryptedValue);
			
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		
	}

}
