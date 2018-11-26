package com.dds.part1;

import java.util.HashMap;


public class test 
{
	static Object ob = null;
	
	public test( String ss )
	{
		super();
		
	}
	public test()
	{
		super();
		ob = this;
	}
	
	public static void show1()
	{
		System.out.println( ob );
//		System.out.println( this );
		
	}
	
	public void show()
	{
		System.out.println( this );
	}
	
	public static void main(String[] args) 
	{
		new test().show();
		test.show1();
		
		HashMap hm = new HashMap();
		hm.put( new test("ss") , "1");
		hm.put( new test("ss") , "2");
		hm.put( new test("ss") , "3");
		hm.put( new test("ss") , "4");
		hm.put( new test("ss") , "5");
		System.out.println(hm);
	}

}
