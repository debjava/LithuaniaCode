package com.dds.core.string.misc;

import java.util.Properties;
import com.pm.utility.EncryptionUtility;
public class TestOnBytes 
{
	public static void main(String[] args) 
	{
		EncryptionUtility eu = new EncryptionUtility();
		String ss = eu.encrypt("Hi How are u ?");
		System.out.println("ss ::: "+ss);
//		DESedeKeySpec kk = null;
//		String testStr = "PROCESSMATE";
//		for( int i = 0 ; i < testStr.length() ; i++ )
//		{
//			char ch = testStr.charAt( i );
//			int j = ( int )ch;
//			String ss = Integer.toHexString( 0x1000 | j ).substring( 1 ).toUpperCase();
//			System.out.println( " Value ::: "+ss);
//		}
//		String s1 = "R";
//		try
//		{
//			byte[] b = s1.getBytes( "8859_1" );
//			System.out.println("Hi ::: "+b);
//			String t = new String( b , "Cp1252" );
//			System.out.println("t ::: "+t);
//		}
//		catch( Exception e )
//		{
//			e.printStackTrace();
//		}
	}

}
