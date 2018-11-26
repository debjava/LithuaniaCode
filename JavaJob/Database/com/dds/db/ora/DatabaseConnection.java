package com.dds.db.ora;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection 
{
	private transient Connection connection = null;
	private Statement stmt = null;
	private ResultSet metaResult = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	
	private Connection getDatabaseConnection(final String dbUrl,final String dbUser,final String dbPwd)
	{
		try
		{
			Class.forName("oracle.jdbc.OracleDriver");
			//DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
			connection  = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
		}
		catch(Exception se)
		{
			se.printStackTrace();
		}
		return connection;
	}
	
	private List<String> getAllUserTables(final Connection connection)
	{
		List<String> tableNamesList = new ArrayList<String>();
		final String queryString = "select * from tab";
		try
		{
			final Statement stmt = connection.createStatement();
			final ResultSet rs = stmt.executeQuery(queryString);
			while(rs.next())
			{
				final String tableName = rs.getString(1);
//				System.out.println("Table Name ::: "+tableName);
				tableNamesList.add(tableName);
			}
		}
		catch(Exception se)
		{
			se.printStackTrace();
		}
		
		return tableNamesList;
	}
	
	public Connection getOraDBConnection(final String dbUrl,final String dbUser,final String dbPwd)
	{
		Connection conn = getDatabaseConnection(dbUrl, dbUser, dbPwd);
		return conn;
	}
	public List<String> getAllDBTables(final Connection conn)
	{
		List<String> tablesList = getAllUserTables(conn);
		
		return tablesList;
	}
	DatabaseMetaData dbMetaData = null;
	private List<TableFieldInfo> getFieldInfo(final Connection conn,final String tableName)
	{
		List<TableFieldInfo> fieldInfoList = new ArrayList<TableFieldInfo>();
		final String queryString = "select * from "+tableName;
		String pkFieldName = new String();
		try
		{
			dbMetaData = conn.getMetaData();
			metaResult = dbMetaData.getPrimaryKeys(null,null,tableName);
			//System.out.println("Primary Key Name ::: "+metaResult.getString("COLUMN_NAME"));
			ArrayList pkList = new ArrayList();
			while(metaResult.next())
			{
				pkFieldName = metaResult.getString("COLUMN_NAME");
				
				System.out.println("*****************Primary key field name ::: "+pkFieldName);
				
				if(! pkList.contains(pkFieldName))
				{
					pkList.add(pkFieldName);
				}
			}//end of while
			statement = conn.createStatement( );
			System.out.println("pkList ::: "+pkList);
			resultSet = statement.executeQuery( queryString );
			ResultSetMetaData resultsetmetaData = resultSet.getMetaData( );
			for( int i = 1; i < resultsetmetaData.getColumnCount( ) + 1; i++ )
			{
				TableFieldInfo fieldInfo = new TableFieldInfo( );
				String fieldName = resultsetmetaData.getColumnName( i );
				fieldInfo.setFieldName(fieldName  );
				fieldInfo
						.setFieldType( resultsetmetaData.getColumnTypeName( i ) );
				
				if(pkList.contains(fieldName))
				{
					fieldInfo.setPkFieldType(true);//to set the primary key field
				}
				else
				{
					fieldInfo.setPkFieldType(false);
				}
				fieldInfoList.add( fieldInfo );
			}
		}
		catch(Exception e)
		{
//			close(metaResult);
//			close(statement);
//			close(resultSet);
			e.printStackTrace();
		}
		finally
		{
			try
			{
				close(metaResult);
				close(statement);
				close(resultSet);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return fieldInfoList;
		
	}
	
	public List<TableFieldInfo> getEachTableFieldInfo(final Connection conn,final String tableName)
	{
		List<TableFieldInfo> fieldInfoList = null;
		fieldInfoList = getFieldInfo(conn, tableName);
		return fieldInfoList;
	}
	
	public void showData(final String name,final Connection conn)
	{
		try
		{
			//final prpeparedStatement stmt1 = conn.createStatement();
			final String queryString = "select * from testtab1 where name = ? ";
			final PreparedStatement pstmt = conn.prepareStatement(queryString);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(conn);
		}
	}
	
	private void close(final Connection conn)
	{
		try
		{
			conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void close(final Statement stmt)
	{
		try
		{
			stmt.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void close(final ResultSet rSet)
	{
		try
		{
			rSet.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
