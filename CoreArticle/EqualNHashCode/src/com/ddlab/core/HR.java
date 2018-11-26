package com.ddlab.core;

public class HR extends Emp
{
	private int age ;
	
	public HR( int age )
	{
		super(age);
//		this.age = age;
	}
	
	public int hashCode()
	{
		return age;
	}
	
	public boolean equals( Object obj )
	{
		boolean flag = false;
		HR emp = ( HR )obj;
		if( emp.age == age )
			flag = true;
		return flag;
	}
}
