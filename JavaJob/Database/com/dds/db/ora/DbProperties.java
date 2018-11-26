package com.dds.db.ora;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DbProperties 
{
	private transient Properties prop = null;
	private transient FileOutputStream fout = null;
	private transient FileInputStream fin = null;
	
	private void loadDBProperty()
	{
		try
		{
			fin = new FileInputStream("E:/data/db.properties");
			prop = new Properties();
			prop.load(fin);
			final String databaseName = (String)prop.get("database");
			System.out.println("database name ::: "+databaseName);
			final String dbUrl = (String)prop.getProperty("dburl");
			System.out.println("dbURL ::: "+dbUrl);
			Map<String,String> databaseMap = new HashMap<String,String>();
			databaseMap.put("databaseName", prop.getProperty("database"));
			databaseMap.put("dbUrl", prop.getProperty("dburl"));
			databaseMap.put("dbUser", prop.getProperty("dbuser"));
			databaseMap.put("dbpwd", "dburl");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) 
	{
		DbProperties prop = new DbProperties();
		prop.loadDBProperty();
		
	}
	

}
