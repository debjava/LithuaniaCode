package com.dds.core.lang;
import java.util.LinkedList;
import java.util.List;

public class CustomerBean implements Cloneable 
{
	private String name;
	private int age;
	private String adrs;
	
	public static List objectsList = new LinkedList();
	
	public CustomerBean()
	{
		super();
		objectsList.add( this );
	}
	public static List getObjectsList() {
		return objectsList;
	}
	public static void setObjectsList(List objectsList) {
		CustomerBean.objectsList = objectsList;
	}
	public String getAdrs() {
		return adrs;
	}
	public void setAdrs(String adrs) {
		this.adrs = adrs;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public static CustomerBean getPreviousObject( CustomerBean  custBean )
	{
		CustomerBean cBean = null;
		try
		{
			if( objectsList.contains( custBean ) )
			{
				int custIndex =  objectsList.indexOf( custBean) ;
				cBean = ( CustomerBean )objectsList.get( custIndex - 1 );
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return cBean;
	}
	public CustomerBean clone()
	{
		try
		{
			return (CustomerBean)super.clone();
		}
		catch( CloneNotSupportedException cne )
		{
			cne.printStackTrace();
		}
		return null;
	}
}
