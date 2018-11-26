package com.dds.vps.ex;

public class TestMyException 
{
	public void show(final String msg) throws MyException
	{
		if( msg == null )
			throw new MyException("Hi..........");
	}
	
	public static void main(String[] args) 
	{
		try
		{
			new TestMyException().show(null);
		}
		catch(Throwable t)
		{
			t.printStackTrace();
		}
		
	}

}
