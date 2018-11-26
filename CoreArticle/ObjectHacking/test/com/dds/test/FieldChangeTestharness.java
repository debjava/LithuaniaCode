package com.dds.test;

import java.lang.reflect.Field;
import com.dds.core.FieldChanger;


/**This testharness class is used for
 * the class FieldChanger to change the
 * value of the field using reflection.
 * @author Debadatta Mishra(PIKU)
 *
 */
public class FieldChangeTestharness 
{
	public static void main(String[] args) 
	{
		try
		{
			Class cls = Class.forName("com.dds.core.FieldChanger");
			Object obj = cls.newInstance();
			Field[] fields = cls.getDeclaredFields();
			fields[0].setAccessible( true );
			System.out.println(FieldChanger.getName());
			fields[0].set( null ,"Horse" );
			System.out.println(FieldChanger.getName());
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}
}
