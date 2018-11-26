package com.dds.desgnptrn.singletondesgn;

class FirstSingle
{
	private static boolean singleFlag = false;
	
	public FirstSingle() throws Exception
	{
		if (singleFlag)
			throw new Exception("Only one spooler allowed");
			else
				singleFlag = true; //set flag for 1 instance
			System.out.println("spooler opened");
	}
	
	public void finalize()
	{
	singleFlag = false; //clear if destroyed
	}
}