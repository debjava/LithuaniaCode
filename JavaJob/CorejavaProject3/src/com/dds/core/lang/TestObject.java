package com.dds.core.lang;

import java.util.HashMap;

class Person
{
	String name;
	
	public Person( String name )
	{
		super();
		this.name = name;
	}
	
	public boolean equals( Object obj )
	{
		if( obj instanceof Person )
		{
			Person p = ( Person )obj;
			return name.equals( p.name );
		}
		else
		{
			System.out.println("ccc");
			return false;
		}
	}
	
	public int hashCode()
	{
		return name.hashCode();
	}
	
	
}

class Score
{
	int val ;
	public Score( int val )
	{
		super();
		this.val = val;
	}
}

public class TestObject 
{
	public static void main(String[] args) 
	{
		HashMap< Person , Score > hm = new HashMap<Person, Score>();
		hm.put( new Person("deba"), new Score(1) );
		hm.put( new Person("deba22"), new Score(11) );
		hm.put( new Person("deba2"), new Score(12) );
		hm.put( new Person("deba1"), new Score(13) );
		System.out.println("==="+hm.containsKey( new Person( "deba22" )));
		System.out.println("Get Values==="+hm.get( new Person( "deba22" )));
		
		
		
		
		
		
//		HashMap< Integer , String > hm = new HashMap<Integer, String>();
//		hm.put( new Integer(1) , "ss1");
//		hm.put( new Integer(2) , "ss2");
//		hm.put( new Integer(13) , "ss3");
//		hm.put( new Integer(4) , "ss4");
//		System.out.println("==="+hm.containsKey( new Integer( 13 )));
//		System.out.println("Values===="+hm.containsValue( new String("ss3") ));
		
	}
}
	
