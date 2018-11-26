package com.dds.core;

/**
 * This is a class which represents a singleton
 * design pattern. However it is a normal singleton
 * pattern.
 * @author Debadatta Mishra(PIKU)
 *
 */
public class MySingleton 
{
	private static MySingleton single = null;
	
	private MySingleton()
	{
		super();
	}
	
	public static MySingleton getInstance()
	{
		if( single == null )
			single = new MySingleton();
		return single;
	}
}
