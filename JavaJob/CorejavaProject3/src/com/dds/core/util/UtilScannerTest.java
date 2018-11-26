package com.dds.core.util;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class UtilScannerTest 
{
	public static void main(String[] args) 
	{
		java.util.Scanner fileScanner =  null;
		FileWriter writer = null;
		Map<String , String > nameMailMap = new HashMap<String , String >();
		try
		{
			File mailFile = new File("E:/JavaJob/CorejavaProject3/config/All Collegues Emails Ids.txt");
			fileScanner = new java.util.Scanner( mailFile );
			fileScanner.useDelimiter( System.getProperty( "line.separator" ) );
			while( fileScanner.hasNext() )
			{
				String tempString = fileScanner.next().trim();
				String[] mailIds = tempString.split("[<,>]");
				nameMailMap.put( mailIds[0], mailIds[1] );
//				System.out.println( mailIds[0]+"=========="+mailIds[1] );
			}
			
			Set set = nameMailMap.entrySet();
			Iterator itr = set.iterator();
			writer = new FileWriter("E:/JavaJob/CorejavaProject3/config/mailids.txt");
			while( itr.hasNext() )
			{
				Map.Entry me = ( Map.Entry )itr.next();
				System.out.println( me.getKey()+"=================="+me.getValue() );
				String temp = me.getKey()+"  ==================  "+me.getValue();
				writer.write( temp );
				writer.write("\n");
			}
			
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				fileScanner.close();
				writer.close();
			}
			catch( Exception e )
			{
				e.printStackTrace();
			}
		}
		

	}
}
