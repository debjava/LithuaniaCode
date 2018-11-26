package com.dds.core.file;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.nio.channels.FileChannel;

/**
 * @author <a href="mailTo:debadattamishra@aol.com?subject=Writing file in append mode">Debadatta Mishra<a>
 *
 */
public class FileWritingInAppendMode 
{
	/**
	 * This method is used to write the contents of the file in 
	 * append mode. Whenever you are trying to run the program,
	 * the contents will be written to the same file.
	 * @param filePath of type String indicating the complete file path
	 * @param fileContents of type indicating the contents of the file.
	 * @author <a href="mailTo:debadattamishra@aol.com">Debadatta Mishra<a>
	 */
	public static void writeFile( String filePath , String fileContents )
	{
		FileWriter fileWriter = null;
		try
		{
			fileWriter = new FileWriter( filePath , true );
			BufferedWriter buffWriter = new BufferedWriter( fileWriter );
			buffWriter.write( fileContents );
			/*
			 * Please note that, the following
			 * two lines are very significant.
			 * Freshers or novice developers
			 * make mistake and screem that
			 * file is not properly written.
			 */
			buffWriter.flush();
			buffWriter.close();
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				fileWriter.close();
			}
			catch( Exception e )
			{
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * This method is used to copy the file from source to destination.
	 * This is a very faster method of copying file from source to destination.
	 * @param source of type String indicating the full path of the source file
	 * @param destination of type String indicating the full path of the destination file
	 * @author <a href="mailTo:debadattamishra@aol.com">Debadatta Mishra<a>
	 */
	public static void copyFile( String source , String destination )
	{
		FileInputStream inStream = null;
		FileOutputStream outStream = null;
		try
		{
			inStream = new FileInputStream( source );
			outStream = new FileOutputStream( destination );
			FileChannel fromChannel = inStream.getChannel();
			FileChannel toChannel = outStream.getChannel();
			fromChannel.transferTo( 0, fromChannel.size(), toChannel );
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) 
	{
//		String fileContents = "Hello world.\n He is a man.";
//		FileWritingInAppendMode.writeFile("C:/something.txt", fileContents);
		
		//C:\Documents and Settings\PIKU\My Documents\My Pictures\Plugin_Dev_Images
		
		FileWritingInAppendMode
				.copyFile(
						"C:/Documents and Settings/PIKU/My Documents/My Pictures/Plugin_Dev_Images/fifth_Img.bmp",
						"C:/t.bmp");
	}

}
