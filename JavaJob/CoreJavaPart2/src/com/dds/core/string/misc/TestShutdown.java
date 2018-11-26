package com.dds.core.string.misc;

public class TestShutdown 
{
	public static void main(String[] args) 
	{
		try
		{
			Runtime.getRuntime().exec("cmd/c shutdown -r -f");
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		
	}

}
