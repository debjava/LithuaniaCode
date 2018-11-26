package com.dds.part1;

public class TestThis 
{
	public static void main(String[] args) 
	{
		UseThis ut1 = new UseThis();
		System.out.println("First hashCode ::: "+ut1.hashCode());
		UseThis ut2 = ut1.getInstance();
		System.out.println("Second ::: "+ut2.hashCode());
		if(ut1.hashCode() == ut2.hashCode())
		{
			System.out.println("Both are equal...");
		}
		else
		{
			System.out.println("Not equal......");
		}
	}

}
