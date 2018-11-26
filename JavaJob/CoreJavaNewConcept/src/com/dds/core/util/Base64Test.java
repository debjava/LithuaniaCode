package com.dds.core.util;

import java.io.*;

import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;

public class Base64Test
{
//	private String str = "";
	public boolean encodeFile( final String filePath )
	{
		System.out.println("It is coming here.......");
//		String str = "";
		String encFileName = "bbb" + ".enc";
		boolean checkFile = false;
		try
		{
			FileInputStream in = new FileInputStream( "D:/aaa.xml" );
			FileOutputStream out = new FileOutputStream(encFileName);
			BASE64Encoder encoder = new BASE64Encoder();
			FileReader reader = new FileReader( "D:/aaa.xml" );
			BufferedReader buff = new BufferedReader( reader );
			String text = buff.readLine();
			String str = text;
//			String encodedStr = encoder.encodeBuffer(str.getBytes());
			encoder.encodeBuffer(in, out);
			in.close();
			out.close();
			checkFile = true;
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return checkFile;
	}
	public static void main (String[] args)
	{
		new Base64Test().encodeFile(null);
		String str = "";
		String fileName = "";
		
		
		try 
		{
			FileReader reader = new FileReader("D:/quesries.sql.enc");
			BufferedReader buff = new BufferedReader(reader);
			String text = buff.readLine();
			str = text;
			
			fileName = "D:/quesries.sql";
			BASE64Encoder encoder = new BASE64Encoder();
			BASE64Decoder decoder = new BASE64Decoder();
			
			String encodedStr = encoder.encodeBuffer(str.getBytes());
			byte[] decodedStr = decoder.decodeBuffer(encodedStr);
			
				String encFileName = "quesries" + ".enc";
				String decFileName = fileName+"1111" + ".dec";
				
				FileInputStream in = new FileInputStream( "D:/quesries.sql.enc" );
				FileOutputStream out = new FileOutputStream(encFileName);
//				encoder.encodeBuffer(in, out);
//				in.close();
//				out.close();
				
				in = new FileInputStream("D:/quesries.sql.enc");
				out = new FileOutputStream(decFileName);
				System.out.println("It is trying to decode..........");
				decoder.decodeBuffer(in, out);
				in.close();
				out.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}

