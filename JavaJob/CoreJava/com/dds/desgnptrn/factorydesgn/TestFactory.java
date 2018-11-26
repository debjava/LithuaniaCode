package com.dds.desgnptrn.factorydesgn;

public class TestFactory 
{
	public static void main(String[] args) 
	{
		NameFactory nameFact = new NameFactory();
		Namer name = nameFact.getNamer("Debadatta,Mishra");
		System.out.println("First Name ::: "+name.getFirst());
		System.out.println("Last Name ::: "+name.getLast());
		
	}

}
