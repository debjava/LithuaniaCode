package com.dds.desgnptrn.singletondesgn;
//Final Singleton 1
public class MoreThanOneInstaceException extends RuntimeException 
{
	public MoreThanOneInstaceException()
	{
		super();
	}
	
	public MoreThanOneInstaceException(final String exceptionMsg)
	{
		super(exceptionMsg);
		System.out.println("More than one exception created");
		System.out.println("There should be only one instance......");
	}

}
