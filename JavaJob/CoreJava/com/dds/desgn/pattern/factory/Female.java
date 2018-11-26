package com.dds.desgn.pattern.factory;

public class Female extends Person 
{
	private int age = 23;
	
	public Female( char gender )
	{
		super();
		System.out.println("Hi Miss");
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
}
