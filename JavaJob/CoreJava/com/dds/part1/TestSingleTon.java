package com.dds.part1;

public class TestSingleTon 
{
	public static void main(String[] args) 
	{
		MySingleton single = MySingleton.getInstance();
		System.out.println("single ::: "+single);
		System.out.println("First HashCode ::: "+single.hashCode());
		single.show();
		MySingleton single1 = MySingleton.getInstance();
		System.out.println("single 1 >>>> "+single1);
		System.out.println("Second HashCode ::: "+single1.hashCode());
		single.show();
		
	}

}
