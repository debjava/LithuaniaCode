package com.dds.core.io.files;

public class TestStringArrayy 
{
	public String[] getStringArr( final String value_parm )
	{
		String[] ss = {""};
		try
		{
			if( value_parm == null )
				throw new NullPointerException("passed parameter is null");
			else
			{
				if( value_parm.indexOf( "," ) > 0 )
				{
					ss = value_parm.split(",");
				}
				else
				{
					ss[0] = value_parm;
					if( ss[0] == null )
						throw new NullPointerException("Array Vlue Null ");
					//do nothing
				}
			}
		}
		catch( NullPointerException npe )
		{
			npe.printStackTrace();
		}
		return ss;
	}
	
	public static void main(String[] args) 
	{
		String str = "piku";//,mana,nandu";
		TestStringArrayy test = new TestStringArrayy();
		String[] str1 = test.getStringArr(str);
		for( int i = 0 ; i < str1.length ; i++ )
		{
			System.out.println("Value in the Array ::: "+str1[i] );
		}
		
	}

}
