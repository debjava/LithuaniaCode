package com.dds.compress;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

public class StringCompressor 
{
	public static String getCompressedString(String uncompressedString)
	{
		try
		{
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			DeflaterOutputStream dos = new DeflaterOutputStream(out);

			dos.write(uncompressedString.getBytes("UTF8"));
			dos.finish();
			dos.flush();
			dos.close();
			return out.toString();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}
	
	public static String dec(String what)
	{
		try
		{
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ByteArrayInputStream in = new ByteArrayInputStream(what.getBytes());
			InflaterInputStream iis = new InflaterInputStream(in);

			
			int len = what.getBytes().length;
			
//			byte[] buffer = new byte[1024];
			byte[] buffer = new byte[len+5555];
			int offset = -1;
			while((offset = iis.read(buffer)) != -1)
			{
				out.write(buffer, 0, offset);
			}
			iis.close();
			return out.toString("UTF8");
		}
		catch(Exception e)
		{
			e.printStackTrace();return "";
		}
	}

}
