package com.dds.misc;

public class Test 
{
	public Test()
	{
		super();
	}
	
	public int hashCode()
	{
		return 123;
	}
	
	public boolean equals(Object obj)
	{
		if(obj.hashCode() == 123)
		return true;
		else
		{
			return false;
		}
	}
	
	public static void main(String[] args) 
	{
		Test t1 = new Test();
		Test t2 = new Test();
		System.out.println("t1 hashcode ::: "+t1.hashCode());
		
		if(t1.equals(t2))
		{
			System.out.println("Both are equal.....");
		}
		else
		{
			System.out.println("Not Equal....");
		}
		if(t1 == t2)
		{
			System.out.println("== ok........");
		}
		
	}

}
