package com.dds.core.io.files;

import java.util.HashMap;

public class TestCloningMap implements Cloneable
{
	public HashMap clearMap( HashMap map_parm)
	{
		HashMap testmap = new HashMap();
		
		map_parm.clear();
		return testmap;
	}
	
	public Object makeClone(HashMap map_parm)
	{
		try
		{
			return (Object)super.clone();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) 
	{
		HashMap myMap = new HashMap();
		myMap.put("piku", "111");
		myMap.put("Lilu", "222");
		
		TestCloningMap cloneMap = new TestCloningMap();
		HashMap hm = (HashMap)cloneMap.makeClone(myMap);
//		cloneMap.clearMap(myMap);
		System.out.println(myMap);
		System.out.println(hm);
		
		
		
	}
}
