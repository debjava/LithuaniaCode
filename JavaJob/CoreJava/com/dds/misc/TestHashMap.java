package com.dds.misc;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


/**
 * @author PIKU
 * 
 * From this example , it is understood that
 * A HashMap can contain null keys and values.
 * A HashMap does not allow duplicate.
 * A HashMap has method .containsKey() and .containsValue() which return true or false.
 * In a HashMap , if duplicate values are present, it will take the latest value.
 *
 */
class testMap
{
	private transient HashMap hashMap = new HashMap();
	
	public HashMap putData()
	{
		final String key = null;
		final String value = null;
		hashMap.put("aa11", "Deba");
		hashMap.put("ll22", "Lilu");
		hashMap.put("zz33", "arzoo");
		hashMap.put(key, value);
		hashMap.put("aa11", "Deba68779");
		
		return hashMap;
	}
}
public class TestHashMap 
{
	public static void main(String[] args) 
	{
		testMap myMap = new testMap();
		HashMap checkMap = myMap.putData();
		//System.out.println("Values in HashMap ::: "+checkMap);
		Set set = checkMap.entrySet();
		Iterator itr = set.iterator();
		while(itr.hasNext())
		{
			Map.Entry me = (Map.Entry)itr.next();
			System.out.println("Kye ::: "+me.getKey());
			System.out.println("Value ::: "+me.getValue());
			
		}
		
	}

}
