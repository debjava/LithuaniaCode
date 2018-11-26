package com.dds.core.lang;

import java.io.File;

public class TestSystemProperty 
{
	public static void main(String[] args) 
	{
		System.setProperty("deba.home", "C:/");
		String home = System.getProperty("user.home");
		System.out.println("home :::: "+home );
		File file = new File("/");
		String path = file.getAbsolutePath();
		System.out.println("path ::: "+path );
	}
}
