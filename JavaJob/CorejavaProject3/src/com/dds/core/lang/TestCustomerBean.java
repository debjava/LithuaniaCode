package com.dds.core.lang;

public class TestCustomerBean 
{
	public static void main(String[] args) 
	{
		CustomerBean cBean = new CustomerBean();
		System.out.println("ObjectList size :::: "+CustomerBean.objectsList.size() );
		cBean.setAdrs("Bangalore");
		cBean.setName("PIKU");
		
		CustomerBean cb1 = ( CustomerBean )CustomerBean.objectsList.get( 0 );
		System.out.println("Name 1 ==>>> "+cb1.getName() );
		
		//For second Bean
		
		CustomerBean cBean2 = new CustomerBean();
		cBean2.setName("Lilu");
		
		CustomerBean liluBean = CustomerBean.getPreviousObject( cBean2 );
		System.out.println("Name 2 ===>>> "+liluBean.getName() );
		System.out.println("ObjectList size :::: "+CustomerBean.objectsList.size() );
		
		CustomerBean cb3 = cBean2;
		System.out.println("ObjectList size :::: "+CustomerBean.objectsList.size() );
		
		CustomerBean cBean3 = new CustomerBean();
		cBean3.setName("Arzoo");
		
		CustomerBean arzooBean = CustomerBean.getPreviousObject( cBean3 );
		System.out.println("Name 2 ===>>> "+arzooBean.getName() );
	}

}
