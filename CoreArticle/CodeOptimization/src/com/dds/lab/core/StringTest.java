package com.dds.lab.core;

import java.util.StringTokenizer;

public class StringTest 
{
	public static void main(String[] args) 
	{
		String str = "java_is_a good_programming_language";
		long startTime = System.currentTimeMillis();
		StringTokenizer st = new StringTokenizer(str,"_");
		while( st.hasMoreTokens())
		{
			System.out.println(st.nextToken());
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Time difference---"+(startTime - endTime));
		
		String[] strs = str.split("[_]");
		for( int i = 0 ; i< strs.length ; i++ )
		{
			System.out.println(strs[i]);
		}
		
		if( str.startsWith("j"))
		{
			
		}
		
		
		
	}
}
