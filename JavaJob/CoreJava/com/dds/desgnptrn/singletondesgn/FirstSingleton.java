package com.dds.desgnptrn.singletondesgn;

public class FirstSingleton 
{
	public static void main(String[] args) 
	{
		FirstSingle pr1,pr2;
		try
		{
			pr1 = new FirstSingle();
			System.out.println("Now HashCode ::: "+pr1.hashCode());
//			pr2 = new FirstSingle();
//			System.out.println("Second Singleton ::: "+pr2.hashCode());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
