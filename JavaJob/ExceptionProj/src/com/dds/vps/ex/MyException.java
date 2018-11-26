package com.dds.vps.ex;

public class MyException extends Exception
{
	public MyException()
	{
		super();
	}
	public MyException(final String exMsg)
	{
		super();
		System.out.println("From the MyException class.......");
		System.out.println(exMsg);
	}

}
