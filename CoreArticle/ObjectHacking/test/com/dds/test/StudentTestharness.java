package com.dds.test;

import java.lang.reflect.Constructor;

/**This is a testharness class for the class Student.
 * This class shows you how to create an instance of
 * a class whose constructor is private.
 * 
 * @author Debadatta Mishra(PIKU)
 *
 */
public class StudentTestharness 
{
	public static void main(String[] args) 
	{
		try
		{
			Class cls = Class.forName("com.dds.core.Lover");
			Constructor[] constructors = cls.getDeclaredConstructors();
			System.out.println("Constructor Name--->>>"+constructors[0].getName());
			constructors[0].setAccessible(true);
			System.out.println("Object creation--->>>"+constructors[0].newInstance( ));
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}
}
