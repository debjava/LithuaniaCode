package com.pm.cons;

public class TestCon implements CommonConstants 
{
	public TestCon()
	{
		super();
	}
	
	public void show()
	{
		/*
		 * Using interface CommonConstants
		 */
		System.out.println("Age====="+CommonConstants.age);
		/*
		 * Using the Current class TestCon
		 */
		System.out.println("Salary========"+TestCon.salary);
		/*
		 * Without using any Class or interface, since
		 * it is available in the class
		 */
		System.out.println("Name==="+name);
		/*
		 * Without using any Class or interface, since
		 * it is available in the class
		 */
		System.out.println("PI Value====="+pi);
	}
	
	public static void main(String[] args) 
	{
		new TestCon().show();
	}
}
