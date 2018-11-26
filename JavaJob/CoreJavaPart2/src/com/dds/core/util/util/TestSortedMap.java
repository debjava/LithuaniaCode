package com.dds.core.util.util;

import java.util.HashMap;
import java.util.Map;

public class TestSortedMap 
{
	public static void main(String[] args) 
	{
		Map testMap = new HashMap();
		testMap.put(new Integer(6) , "piku");
		testMap.put(new Integer(10) , "xyz");
		testMap.put(new Integer(2) , "lilu");
		testMap.put(new Integer(4) , "abcd");
		testMap.put(new Integer(3) , "pqrst");
		testMap.put(new Integer(5) , "mana");
		
		System.out.println(" Key :::: "+testMap.get("lilu"));
	}

}
