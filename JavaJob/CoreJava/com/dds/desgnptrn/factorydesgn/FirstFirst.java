package com.dds.desgnptrn.factorydesgn;

public class FirstFirst extends Namer 
{
	public FirstFirst(final String s)
	{
		int i = s.indexOf(" ");
		if(i>0)
		{
			first = s.substring(0,i).trim();
			last = s.substring(i+1).trim();
		}
		else
		{
			first = "";
			last = s;
		}
	}
}
