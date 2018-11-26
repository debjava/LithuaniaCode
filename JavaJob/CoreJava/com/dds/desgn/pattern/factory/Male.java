package com.dds.desgn.pattern.factory;

public class Male extends Person 
{
	private int age = 25;
	
	public Male( char  gender )
	{
		super();
		System.out.println("Hi Mr");
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
}
