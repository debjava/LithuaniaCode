package com.dds.desgnptrn.singletondesgn;
//Final Singleton 1
public class PrintSpooler 
{
	private static boolean instanceFlag = false;
	public PrintSpooler()
	{
		super();
		if(instanceFlag)
		{
			throw new MoreThanOneInstaceException("More Than one Exception");
		}	
		else
		{
			instanceFlag = true;
			System.out.println("First Instance.......");
		}
	}
	public void show()
	{
		System.out.println("I am in a singleton class method");
	}
	

}
