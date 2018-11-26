package com.dds.db.ora;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class TestDatabase 
{
	public static void main(String[] args) 
	{
		final String dbUrl = "jdbc:oracle:thin:@localhost:1521:orcl";
		final String dbUser = "deba";
		final String dbPwd = "deba";
		
		DatabaseConnection dbConn = new DatabaseConnection();
		Connection connection = dbConn.getOraDBConnection(dbUrl, dbUser, dbPwd);
		if(connection == null)
		{
			System.out.println("Not a proper database connection");
			
		}
		else
		{
			System.out.println("Successful Database Connection...............");
			ArrayList<String> tables = (ArrayList<String>) dbConn.getAllDBTables(connection);
			for(int i = 0 ; i<tables.size() ; i++)
			{
				final String tableName = tables.get(i);
				System.out.println(" Table Name ::: "+tableName);
				
				List<TableFieldInfo> fieldInfoList = dbConn.getEachTableFieldInfo(connection, tableName);
				for(int j = 0 ; j<fieldInfoList.size() ; j++)
				{
					TableFieldInfo info = fieldInfoList.get(j);
					System.out.println("FieldName :::"+info.getFieldName());
					System.out.println("FieldType :::"+info.getFieldType());
					System.out.println("Primary Key :::"+info.isPkFieldType());
				}
			}
		}
		System.out.println("**********************************************************************");
		
	}
}
