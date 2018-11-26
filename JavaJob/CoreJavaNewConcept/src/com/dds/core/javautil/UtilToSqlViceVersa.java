package com.dds.core.javautil;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UtilToSqlViceVersa 
{
	public java.sql.Date getSQLDateFromString( String dateString )
	{
		return new java.sql.Date( 111 ).valueOf( dateString );
	}
	
	public java.sql.Date getSQLDateFromUtilDate( java.util.Date utilDate )
	{
		java.sql.Date sqlDate = new java.sql.Date( utilDate.getYear() , utilDate.getMonth() , utilDate.getDay() );
		return sqlDate ; 
	}
	
	public java.sql.Date getSQLDateFromUtilDate( java.util.Date utilDate , String dateFormat )
	{
		java.sql.Date sqlDate = null;
		try
		{
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat( dateFormat );
			String strDate = simpleDateFormat.format( utilDate );
			sqlDate = getSQLDateFromString( strDate );
		}
		catch( Exception pe )
		{
			pe.printStackTrace();
		}
		return sqlDate ;
	}
	
	public java.util.Date getUtilDate( java.sql.Date sqlDate , String format )
	{
		java.util.Date utilDaten = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat( format );
		String ss = sqlDate . toString() ;
		try
		{
			utilDaten = dateFormat.parse( ss );
		}
		catch( ParseException  pe )
		{
			pe.printStackTrace();
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return utilDaten;
		
	}
	
	
	public static void main(String[] args) 
	{
		UtilToSqlViceVersa dateUtil = new UtilToSqlViceVersa();
		String format = "yyyy-MM-dd";
		SimpleDateFormat dateFormat = new SimpleDateFormat( format );
		java.util.Date utilDate = new java.util.Date();
		try
		{
			String strDate = dateFormat.format( utilDate );
			System.out.println("Date in Format :::: "+ strDate );
			java.util.Date utilDate1 = dateFormat.parse( strDate );
			System.out.println("utilDate1 :::: "+utilDate1 );
			
			java.sql.Date sqlDate = dateUtil.getSQLDateFromString( strDate );//new java.sql.Date(222).valueOf(strDate);//(00,00,00 );//( java.sql.Date )utilDate1;
			System.out.println("sqlDate ::: "+ sqlDate );
			System.out.println("sqlDate Type ::: "+ sqlDate.getClass().getName() );
			
			
			java.sql.Date sqlDaten = dateUtil.getSQLDateFromUtilDate( utilDate1 );
			
			System.out.println(" util date from Method ::: "+dateUtil.getUtilDate( sqlDaten , format ) );
			System.out.println(" util date Type from Method ::: "+dateUtil.getUtilDate( sqlDaten , format ).getClass().getName() );
			
			
			
			System.out.println("UtilDate To SQL Date :::::   "+  sqlDaten );
			System.out.println("sql date type :::: "+ sqlDaten.getClass().getName());
			
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
//		System.out.println("UtilDate ::: "+ utilDate );
		
		
	}

}
