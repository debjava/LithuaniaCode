package com.dds.core.io.files;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


/**
 * @author PIKU
 * created by PIKU on Dec 16, 2006.
 * Modfied by :
 * <p>
 * 	This class is used writer the contents
 * to a file. The file must have name
 * nandu.txt.
 * </p>
 *
 */
public class TestFileWriter 
{
	/**
	 * Object of type FileWriter.
	 * @see java.io.FileWriter
	 */
	private transient FileWriter fileReader;// Creating a reference of type FileWriter
	/**
	 * Object of type BufferedReader.
	 */
	private transient BufferedWriter buffWriter;
	
	/**This method is used to write the
	 * contents.
	 * @param filePath
	 * @return a boolean value true if the 
	 * file is generated successfully, otherwise false.
	 */
	public boolean fileWrite(final String filePath)
	{
		boolean writeFlag = false;
		try
		{
			fileReader = new FileWriter(filePath);
			buffWriter = new BufferedWriter(fileReader);
			final String name = "Nandu-Mana";
			buffWriter.write(name);
		}
		catch(FileNotFoundException fnfe)
		{
			System.out.println("Error- Actual File path Not Found............");
			fnfe.printStackTrace();
			File file = new File(filePath);
			file.mkdirs();
			fileWrite(filePath);
		}
		catch(IOException ie)
		{
			System.out.println("Error- Error in file Stream..........");
			ie.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				buffWriter.flush();
				buffWriter.close();
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		writeFlag = true;
		return writeFlag;
	}
	
	public static void main(String[] args) 
	{
		TestFileWriter writer = new TestFileWriter();
		boolean checkFlag = writer.fileWrite("E:/data/nandu.txt");
	}

}
