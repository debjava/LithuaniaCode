package com.dds.lab.core;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ByteConverter {
	
	public static void main(String[] args) throws Exception
	{
		int x = 123345;
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		dos.writeInt(x);
		
//		dos.writeLong(v1);
		byte[] b1 = bos.toByteArray();
		String strVal = new String( b1 );
		System.out.println("String val------"+strVal);
		System.out.println("------->"+b1);
		
		ByteArrayInputStream bis = new ByteArrayInputStream(b1);
		DataInputStream dis = new DataInputStream(bis);
		int val = dis.readInt();
		System.out.println("Now actual value------"+val);
		
		bis = new ByteArrayInputStream(strVal.getBytes());
		dis = new DataInputStream( bis );
		int val1 = dis.readInt();
		System.out.println(val1);
//		long l11 = dis.readLong();
//		System.out.println("<--------"+l11);
	}

}
