package com.dds.vps.db;

public class TestDbCreate 
{
	public static void main(String[] args) 
	{
		DBTableGenerator tableGenrtor = new DBTableGenerator();
		final String filePath = "E:/data/dbTablescripts";
		final String fileName = "table11.sql";
		boolean genFlag = tableGenrtor.generateSQLCreateScript(filePath , fileName);
		System.out.println("GenFlag ::: "+genFlag);
		
	}

}
