package com.dds.part2.rcrsn;

public class Factorial 
{
	private double factorial_value = 1;
	public double findFact(final int fact_num)
	{
		try
		{
			if(fact_num == 0  || fact_num == 1)
			{
//				System.out.println("It is coming to 0");
				factorial_value = factorial_value * 1;
			}
//			else if(fact_num == 1)
//			{
////				System.out.println("It is coming to 1");
//				factorial_value = factorial_value * 1;
//			}
			else
			{
//				System.out.println("It is coming to else part");
				factorial_value = factorial_value * fact_num * findFact(fact_num - 1); 
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return factorial_value;
	}
	
	public static void main(String[] args) 
	{
		Factorial fact = new Factorial();
		double result = fact.findFact(4);
		System.out.println("Result ::: "+result);
		
	}

}
