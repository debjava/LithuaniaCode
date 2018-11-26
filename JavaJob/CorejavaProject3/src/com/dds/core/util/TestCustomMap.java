package com.dds.core.util;

import java.util.List;

public class TestCustomMap 
{
	public static void main(String[] args) 
	{
		CustomMap map = new CustomMap();
		map.put("piku1" , "piku1");
		map.put("piku1" , "piku11");
		map.put("piku2" , "piku222");
		map.put("piku1" , "piku1234");
		map.put("piku3" , "Arzoo");
		
		List list = ( List )map.get("piku1");
		System.out.println("List :::: "+list );
	}
}
