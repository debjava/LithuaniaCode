package com.dds.misc;

class A1
{
	
}
class B1 extends A1
{
	
}
public class TestInstanceOf extends B1
{
	public static void main(String[] args) 
	{
		A1 a1 = new  B1();//TestInstanceOf();
		if(a1 instanceof B1)//TestInstanceOf)
		{
			System.out.println("Yes");
		}
		else
		{
			System.out.println("No");
		}
	}

}
