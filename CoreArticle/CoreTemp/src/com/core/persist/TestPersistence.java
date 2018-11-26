package com.core.persist;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
/**
 * This is a test harness class to display the
 * use of XMLEncoder and XMLDecoder.
 * @author Debadatta Mishra(PIKU)
 *
 */
public class TestPersistence 
{
	public static void main(String[] args) 
	{
		Emp emp = new Emp();
		emp.setName("John");
		emp.setAge(23);
		emp.setEmpId("A123");
		try
		{
			/*
			 * The following codes are used to persist the Emp object graph
			 */
			XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(
					new FileOutputStream("C:/emp.xml")));
			encoder.writeObject(emp);
			encoder.flush();
			encoder.close();
			/*
			 * The following codes are used to obtain the Emp object graph
			 * from the XML document
			 */
			XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(
					new FileInputStream("C:/emp.xml")));
			Emp emp1 = ( Emp )decoder.readObject();
			decoder.close();
			System.out.println("Emp Name=>"+emp1.getName());
			System.out.println("Emp Age=>"+emp1.getAge());
			System.out.println("Emp Id=>"+emp1.getEmpId());
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}
}
