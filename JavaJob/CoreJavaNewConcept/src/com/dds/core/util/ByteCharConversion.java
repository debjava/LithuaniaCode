package com.dds.core.util;

import java.io.File;
import java.util.Scanner;

public class ByteCharConversion 
{
	 public char byte2Char(byte b)
	    {
	        // This mask is used to filter out the highest bit of a byte
	        byte highestBitMask = (byte) 0x3f;

	        int i = b >= 0 ? b : ((b & highestBitMask) - 128);
	        return (char) i;
	    }

	 public char[] bytes2Chars(byte[] bytes)
    {
        if (bytes == null)
            return null;

        int count = bytes.length;
        if (count % 2 != 0)
            throw new IllegalArgumentException("The number of bytes " + count +
                    " is not even");

        int charsLen = count / 2;
        char[] chars = new char[charsLen];

        for (int i = 0; i < charsLen; i++)
        {
            char highChar = (char) (byte2Char(bytes[2 * i + 1]) << 8);
            char lowChar = byte2Char(bytes[2 * i]);
            chars[i] = (char) (highChar | lowChar);
        }
        return chars;
    }
	
	 public byte[] chars2Bytes(char[] chars)
    {
        // This mask is used to filter out the lower byte of a char
        char highByteMark = '\uff00';

        if (chars == null) return null;
        if (chars.length < 1) return new byte[0];

        byte[] bytes = new byte[chars.length * 2];

        for (int i = 0; i < chars.length; i++)
        {
            // Split the char into two bytes. The high-byte will be stored
            // in the higher order element in the byte array.
            char c = chars[i];
            byte highByte = (byte) ((c & highByteMark) >> 8);
            byte lowByte = (byte) c;

            bytes[i * 2 + 1] = highByte;
            bytes[i * 2] = lowByte;
        }
        return bytes;
    }
	
	public byte[] charToByte( char[] ch )
	{
		String ss = new String( ch );
		return ss.getBytes();
	}
	
	public char[] getByteToChar( byte[] bt )
	{
		String ss = null;
		try
		{
			ss = new String( bt );
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return ss.toCharArray();
	}
	
	
	public static void main(String[] args) 
	{
		ByteCharConversion bc = new ByteCharConversion();
		char[] ch = {'a','b','c','d'};
		byte[] bb = bc.chars2Bytes(ch);//bc.charToByte( ch );
		for( int j = 0 ; j < bb.length ; j++ )
		{
			System.out.println("Byte Val ::: "+bb[j] );
		}
//		char[] ch1 = bc.getByteToChar(bb);
//		for( int i = 0 ; i < ch1.length ; i++ )
//		{
//			System.out.println("Char val ::: "+ch1[i]);
//		}
		try
		{
//			FileOutputStream out_file = new FileOutputStream ("D:/Turkish.txt");
//			OutputStreamWriter file_writer = new OutputStreamWriter (out_file, "8859_1");
//			file_writer.write("PIKU");
//			file_writer.flush();
//			file_writer.close();
			
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}


	}
}
