package com.dds.part.jdk5.vararg;

public class TestVarArg 
{
	public int getValue(int ... value)
	{
		int result = 0;
		for(int i=0 ; i < value.length ; i++)
		{
			result = result + value[i];
		}
		return result;
	}
	public static void main(String[] args) 
	{
		int result = new TestVarArg().getValue(2,4,6,8);
		System.out.println("Result ::: "+result);
		
	}

}
