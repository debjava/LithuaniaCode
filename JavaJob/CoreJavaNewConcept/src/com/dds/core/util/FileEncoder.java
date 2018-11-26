package com.dds.core.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
//Final and stable
public class FileEncoder 
{
	public static void main(String[] args) 
	{
		try
		{
//			FileInputStream in = new FileInputStream( "D:/aaa.xml" );
//			FileOutputStream out = new FileOutputStream("D:/encode.txt");
//			BASE64Encoder encoder = new BASE64Encoder();
//			encoder.encode(in, out);
//			in.close();
//			out.close();
			
			FileInputStream in = new FileInputStream( "D:/encode.txt" );
			FileOutputStream out = new FileOutputStream("D:/decode.xml");
			BASE64Decoder decoder = new BASE64Decoder();
			decoder.decodeBuffer(in, out);
			in.close();
			out.close();
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}
}
