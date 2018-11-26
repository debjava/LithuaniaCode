package com.dds.core.io.files;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestFileInout 
{
	private FileInputStream fin = null;
	
	public void readFile(final String FilePath)
	{
		try
		{
			String sx = "";
			fin = new FileInputStream(FilePath);
			int size = fin.available();
			System.out.println("******************** SIZE ********************* "+size);
			for( int i = 0 ; i < size ; i++ )
			{
//				String sx = "";
				char ch = (char)fin.read();
				String ss = String.valueOf(ch);
				if( ss.contains(" "))
				{
//					System.out.println(" *************** It is a blank space ***************");;
					System.out.println(" Now String :: "+sx);
					sx = "";
				}
				else
				{
					sx += ss;
//					System.out.println(sx);
				}
			}
		}
		catch(FileNotFoundException fnfe)
		{
			fnfe.printStackTrace();
		}
		catch(IOException ie)
		{
			ie.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String[] args) 
	{
		
		TestFileInout test = new TestFileInout();
		test.readFile("E:/data/build.xml");
	}
}













