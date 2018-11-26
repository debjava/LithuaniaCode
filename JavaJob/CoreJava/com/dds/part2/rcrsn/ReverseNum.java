package com.dds.part2.rcrsn;

public class ReverseNum 
{
	private transient int reverseNum = 0;
	public int getReverseNum(int num_param)
	{
		try
		{
			if(num_param <= 0)
			{
				reverseNum = reverseNum;
			}
			else
			{
				int x = num_param;
				reverseNum = reverseNum * 10 + x%10 ;//+getReverseNum(num_param/10);
				x = getReverseNum( num_param/10 );
				//reverseNum = reverseNum * 10 + getReverseNum(num_param/10);
			}
//			int x = num_param;
//			System.out.println("X ::: "+x);
//			while(x > 0)
//			{
//				int y = x%10;
//				reverseNum = reverseNum * 10 + y;
//				System.out.println("Reverse num ::: "+reverseNum);
//				x = x/10;
//				System.out.println("X ::: "+x);
//			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return reverseNum;
	}
	public static void main(String[] args) 
	{
		ReverseNum reveNum = new ReverseNum();
		int result = reveNum.getReverseNum(123456789);
		System.out.println("Result ::: "+result);
	}

}
