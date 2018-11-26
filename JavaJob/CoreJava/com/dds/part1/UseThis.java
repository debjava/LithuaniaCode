package com.dds.part1;

public class UseThis 
{
	UseThis ut = null;
	public UseThis getInstance()
	{
		if(ut == null)
		{
			ut = this;
		}
		else
		{
			//do nothing
		}
		return ut;
	}
	
	public void show()
	{
		System.out.println("Hi How are you....");
	}

}
