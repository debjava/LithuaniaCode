package com.dds.desgn.pattern.factory;

public class GenderFact 
{
	
	public GenderFact()
	{
		super();
	}
	
	public Person getPerson( String name , char gender )
	{
		if( gender == 'M')
			return new Male( gender );
		else
			return new Female( gender );
	}
}
