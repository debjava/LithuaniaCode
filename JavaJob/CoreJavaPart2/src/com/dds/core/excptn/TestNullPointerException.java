package com.dds.core.excptn;

public class TestNullPointerException 
{
	public void show( String str_parm )
	{
		try
		{
			if( str_parm == null )
				throw new NullPointerException(" \n str_parm is null ");
		}
		catch( NullPointerException npe )
		{
			npe.printStackTrace();
			npe.getLocalizedMessage();
			npe.getMessage();
			npe.getStackTrace();
			npe.getStackTrace();
		}
	}

	public static void main(String[] args) 
	{
		new TestNullPointerException().show( null );
		
	}
}
