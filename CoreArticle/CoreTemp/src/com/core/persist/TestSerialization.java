//package com.core.persist;
//
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//
///**
// * @author Debadatta Mishra(PIKU)
// *
// */
//public class TestSerialization 
//{
//	public static void main(String[] args) 
//	{
//		Emp emp = new Emp();
//		emp.setAge(23);
//		emp.setEmpId("A1");
//		emp.setName("John");
//		Project proj = new Project();
//		proj.setProjectId(5555);
//		proj.setPojectName("XYZ");
//		emp.setProj(proj);
//		
//		try
//		{
//			ObjectOutputStream ous = new ObjectOutputStream(
//					new FileOutputStream("D:/test.ser"));
//			ous.writeObject(emp);
//			ous.close();
//			
//			ObjectInputStream oin = new ObjectInputStream(new FileInputStream(
//					"D:/test.ser"));
//			Emp emp1 = ( Emp )oin.readObject();
//			
//			System.out.println("Emp age----"+emp1.getAge());
//			Project proj1 = emp1.getProj();
//			System.out.println("Proj Id-----"+proj1.getProjectId());
//			System.out.println("Proj Name----"+proj1.getPojectName());
//		}
//		catch( Exception e )
//		{
//			e.printStackTrace();
//		}
//	}
//}
