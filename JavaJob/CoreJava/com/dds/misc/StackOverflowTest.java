package com.dds.misc;

public class StackOverflowTest 
{
	public StackOverflowTest()
	{
		StackOverflowTest test;
		try
		{
			test = new StackOverflowTest();
		}
		catch(StackOverflowError e)
		{
			System.runFinalization();
			test = null;
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) 
	{
		StackOverflowTest test1 = new StackOverflowTest();
		
	}

}
