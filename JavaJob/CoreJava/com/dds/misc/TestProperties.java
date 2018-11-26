package com.dds.misc;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Properties;

public class TestProperties {

	public static void main(String[] args) 
	{
		TestProperties prop = new TestProperties();
		Properties prop1 = new Properties();

		 try
		 {
			 FileInputStream fin = new FileInputStream("E:/data/db.properties");
			 prop1.load(fin);
			 final String ss = prop1.getProperty("className");
			 System.out.println(" class Name ::: "+ss);
			 Class cls = Class.forName(ss);
			 Method[] methods = cls.getMethods();
			 for( int i = 0 ; i < methods.length ; i++ )
			 {
				 System.out.println("methods ::: "+methods[i].getName());
			 }
		 }
		 catch(Exception e)
		 {
		 e.printStackTrace();
		 }

	}

}
