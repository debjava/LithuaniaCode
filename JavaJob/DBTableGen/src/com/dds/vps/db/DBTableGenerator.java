package com.dds.vps.db;

import java.io.PrintWriter;


public class DBTableGenerator 
{
	private transient PrintWriter printWriter = null;
	private transient StringBuilder scriptBuilder = null;
		
	public DBTableGenerator()
	{
		super();
	}
	
	private PrintWriter getPrintWriter(final String filePath_parm , final String fileName_parm)
	{
		PrintWriter pw = null;
		try
		{
			pw = new PrintWriter(filePath_parm+"/"+fileName_parm);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return pw;
	}
	
	private boolean generateFile(final String filePath_parm,final String fileName_parm,final StringBuilder fileBuilder)
	{
		boolean genFlag = false;
		try
		{
			printWriter = getPrintWriter(filePath_parm,fileName_parm);
			printWriter.write(fileBuilder.toString());
			genFlag = true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return genFlag;
	}
	
	public boolean generateSQLCreateScript(final String filePath_parm , final String fileName_parm)
	{
		boolean checkFlag = false;
		try
		{
			scriptBuilder = new StringBuilder();
			scriptBuilder.append("-- *********************TABLE DROPPING *******************************\n");
			for(int i = 0 ; i < 3500 ; i++)
			{
				scriptBuilder.append("drop table AutoGenTab"+i+" ; \n");
			}
			scriptBuilder.append("\n\n\n\n");
			scriptBuilder.append("-- *******************************************************************\n");
			scriptBuilder.append("-- **************************** TABLE CREATION ***********************\n");
			scriptBuilder.append("-- *******************************************************************\n");
			for(int i = 0 ; i < 3500 ; i++)
			{
				scriptBuilder.append("create table AutoGenTab"+i+"\n");
				scriptBuilder.append("( \n");
				scriptBuilder.append("\t\t name varchar2(40),\n");
				scriptBuilder.append("\t\t age number(10),\n");
				scriptBuilder.append("\t\t sal number(10,2), \n");
				scriptBuilder.append("\t\t offAdrs varchar2(40), \n");
				scriptBuilder.append("\t\t homeAdrs varchar2(40) \n");
				scriptBuilder.append("); \n");
			}
			checkFlag = generateFile(filePath_parm,fileName_parm,scriptBuilder);
		}
		catch(Exception e)
		{
			checkFlag = false;
			e.printStackTrace();
		}
		return checkFlag;
	}
	
	
	

}
