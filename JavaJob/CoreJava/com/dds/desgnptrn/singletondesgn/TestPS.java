package com.dds.desgnptrn.singletondesgn;
//Final Singleton 1
public class TestPS 
{
	public static void main(String[] args) 
	{
		PrintSpooler pr1,pr2;
		pr1 = new PrintSpooler();
		pr1.show();
//		pr2 = new PrintSpooler();
//		pr2.show();
		
		Object ob = new PrintSpooler();
		
		
	}

}
