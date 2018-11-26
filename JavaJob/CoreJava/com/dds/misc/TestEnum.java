package com.dds.misc;

enum sizeEnum 
{
	Small,
	Big,
	Medium,
	High,
	Large
}

class AEnum
{
	sizeEnum newSize;
}
public class TestEnum 
{
	public static void main(String[] args) 
	{
		AEnum a1 = new AEnum();
		a1.newSize = sizeEnum.Large;
		System.out.println("Value ::: "+a1.newSize);
		
	}

}
