package com.dds.desgn.pattern.factory;

public class TestFact 
{
	/* (non-Javadoc)
	 * @see java.lang.Object#finalize()
	 */
	public void finalize()
	{
		System.out.println("It is called");
	}
	public static void main( String[] args ) 
	{
		GenderFact g = new GenderFact();
		Female ff = ( Female ) g.getPerson( "PIKU" , 'F');
		try
		{
			Thread.sleep(1000);
		}catch( Exception e )
		{
			e.printStackTrace();
		}
		new TestFact().finalize();
		System.out.println("Age :::"+ff.getAge());
	}
}
