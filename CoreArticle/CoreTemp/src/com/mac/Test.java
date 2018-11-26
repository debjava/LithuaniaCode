package com.mac;

import java.security.InvalidKeyException;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;

import sun.misc.CRC16;

import com.sun.corba.se.impl.orbutil.HexOutputStream;

public class Test 
{
	public static void main(String[] args) 
	{
		try {
	        // Generate a key for the HMAC-MD5 keyed-hashing algorithm; see RFC 2104
	        // In practice, you would save this key.
	        KeyGenerator keyGen = KeyGenerator.getInstance("HmacMD5");
	        SecretKey key = keyGen.generateKey();
	    
	        // Create a MAC object using HMAC-MD5 and initialize with key
	        Mac mac = Mac.getInstance(key.getAlgorithm());
	        mac.init(key);
	    
	        String str = "abcd1234dsgsgsdgsdgsdgsdgsdgsdsdg";
	    
	        // Encode the string into bytes using utf-8 and digest it
	        byte[] utf8 = str.getBytes("UTF8");
	        byte[] digest = mac.doFinal(utf8);
	    
	        // If desired, convert the digest into a string
	        String digestB64 = new sun.misc.BASE64Encoder().encode(digest);
	        System.out.println(digestB64);
	        System.out.println(new String( digest,"UTF-16"));
	    } catch (Exception e) 
	    {
	    	e.printStackTrace();
	    }

	}
}
