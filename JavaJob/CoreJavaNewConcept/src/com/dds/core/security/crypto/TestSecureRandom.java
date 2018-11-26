package com.dds.core.security.crypto;

import java.security.MessageDigest;

public class TestSecureRandom 
{
	public static void main(String[] args) 
	{
		try
		{
			String algorithm = "SHA";
			MessageDigest md = MessageDigest.getInstance( algorithm );
			String name = "PIKU";
			byte[] certificateBytes = name.getBytes();
		    md.update(certificateBytes);
		    byte[] digest = md.digest();
		    
		    System.out.println("digest ::: "+digest );
		    
		    
		    
		    for (int i = 0; i < digest.length; i++) 
		    {
		    	
		        if (i != 0)
		        {
//		        	System.out.print(":");
		        }
		        int b = digest[i] & 0xff;
		        String hex = Integer.toHexString(b);
		        
//		        if (hex.length() == 1) System.out.print("0");
		        System.out.print(hex);
		      }
//		      System.out.println();



		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		
	}

}
