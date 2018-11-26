package com.dds.misc;

public class StatNonSta 
{
	public boolean checkFlag = false;
	
//	public static boolean isCheckFlag() 
//	{
//		return checkFlag;
//	}


	public static void show()
	{
		System.out.println("Hell world.....");
//		checkFlag = true;
	}
	
	public static void main(String[] args) 
	{
		StatNonSta check = new StatNonSta();
		check.show();
//		System.out.println("Now boolean Value ::: "+check.isCheckFlag());
		
	}

}
