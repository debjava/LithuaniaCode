package com.dds.test;

import java.io.ByteArrayInputStream;
import java.io.RandomAccessFile;
import java.util.zip.InflaterInputStream;

import com.dds.compress.StringCompressor;

public class Testharness 
{
	public static void main(String[] args) 
	{
		String fileName = "D:/TempData/ImplementedCommands.java";
		try
		{
			RandomAccessFile raf = new RandomAccessFile( fileName,"r" );
			int fileLength = ( int ) raf.length();
			byte[] fileBytes = new byte[ fileLength ];
			raf.read(fileBytes);
			raf.close();
			
			String fileString = new String( fileBytes );
//			System.out.println("Total file contents--->>>"+new String( fileBytes ));
			System.out.println("Total file Length in String length------>>>"+fileString.length());
			
			String compressedString = StringCompressor.getCompressedString( fileString );
			System.out.println("Compressed String Length---->>>"+compressedString.length());
//			String fullCompressedString = StringCompressor.dec(compressedString);
//			System.out.println("Uncompressed String Length---->>>"+fullCompressedString.length());
			
			
			ByteArrayInputStream in = new ByteArrayInputStream(compressedString.getBytes());
			InflaterInputStream iis = new InflaterInputStream(in);
			
			byte[] bb = new byte[ compressedString.getBytes().length ];
			iis.read( bb );
			System.out.println(new String(bb));
			
			
			
			
			
			
			
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}
}
