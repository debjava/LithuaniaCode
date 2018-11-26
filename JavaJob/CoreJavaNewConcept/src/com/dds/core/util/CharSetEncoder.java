package com.dds.core.util;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

public class CharSetEncoder 
{
	public static void main(String[] args) 
	{
		Charset charset = Charset.forName("ISO-8859-1");
		CharsetEncoder encoder = charset.newEncoder();
		try {
	        // Convert a string to ISO-LATIN-1 bytes in a ByteBuffer
	        // The new ByteBuffer is ready to be read.
	        ByteBuffer bbuf = encoder.encode(CharBuffer.wrap("Hi how are you"));
	    
	        // Convert ISO-LATIN-1 bytes in a ByteBuffer to a character ByteBuffer and then to a string.
	        // The new ByteBuffer is ready to be read.
//	        CharBuffer cbuf = decoder.decode(bbuf);
	        bbuf.flip();
	        String s = bbuf.toString();
	        System.out.println("SSSS :::: "+s);
//	      Create a direct ByteBuffer.
	        // This buffer will be used to send and recieve data from channels.
//	        ByteBuffer bbuf = ByteBuffer.allocateDirect(1024);
//	        
//	        // Create a non-direct character ByteBuffer
//	        CharBuffer cbuf = CharBuffer.allocate(1024);
//	        
//	        // Convert characters in cbuf to bbuf
//	        encoder.encode(cbuf, bbuf, false);
//	        
//	        // flip bbuf before reading from it
//	        bbuf.flip();

	    } catch (CharacterCodingException e) {
	    }



	}
}
