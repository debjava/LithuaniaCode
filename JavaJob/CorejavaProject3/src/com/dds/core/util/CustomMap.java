package com.dds.core.util;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CustomMap implements Map 
{
	Map objMap = new LinkedHashMap(); 
	
	public CustomMap()
	{
		super();
	}
	public void clear() 
	{
		
	}

	public boolean containsKey(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean containsValue(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public Set entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object get(Object arg0) 
	{
		return objMap.get(arg0);
//		return null;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public Set keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object put(Object arg0, Object arg1) 
	{
		if( objMap.containsKey( arg0 ) )
		{
			List list = ( List )objMap.get( arg0 );
			list.add( arg1 );
			objMap.put( arg0 , list );
		}
		else
		{
			List dataList = new LinkedList();
			dataList.add( arg1 );
			objMap.put( arg0 , dataList );
		}
		return objMap;
	}

	public void putAll(Map arg0) 
	{
		// TODO Auto-generated method stub
		
	}

	public Object remove(Object arg0) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	public int size() 
	{
		// TODO Auto-generated method stub
		return 0;
	}

	public Collection values() 
	{
		// TODO Auto-generated method stub
		return null;
	}

}
