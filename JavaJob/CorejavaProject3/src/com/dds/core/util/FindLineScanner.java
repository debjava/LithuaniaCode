package com.dds.core.util;

import java.util.Scanner;

public class FindLineScanner 
{
	public void display( String str )
	{
		if( str == null || str.endsWith("qq") || str.startsWith("aa"))
		{
			System.out.println("Yes");
		}
		else
		{
			System.out.println("No");
		}
	}
	public static void main(String[] args) 
	{
		new FindLineScanner().display(null);
		
//		String instr = "Name: Joe Age: 28 ID: 77";
//		Scanner scanner = new Scanner( instr );
//		
//		scanner.findInLine("Age: "); 
//		if( scanner.hasNext() )
//		{
//			 System.out.println( "Value ::: "+scanner.next() );
//		}
	}

}
