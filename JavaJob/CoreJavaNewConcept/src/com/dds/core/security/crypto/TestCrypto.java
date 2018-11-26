package com.dds.core.security.crypto;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TestCrypto 
{
	public void sendAuthentication(String user, String password,
			OutputStream outStream) throws IOException,
			NoSuchAlgorithmException 
	{
		DataOutputStream out = new DataOutputStream(outStream);
		long t1 = 1234;
		double q1 = 111;
		
		byte[] protected1 = Protection.makeDigest(user, password, t1, q1);

		out.writeUTF(user);
		out.writeLong(t1);
		out.writeDouble(q1);
		out.writeInt(protected1.length);
		out.write(protected1);
		out.flush();
	}
	
	public void getData(String user , String password,InputStream inStream )throws IOException,NoSuchAlgorithmException
	{
		long t1 = 1234;
		double q1 = 111;
		
		DataInputStream in = new DataInputStream( inStream );
		
		byte[] protected1 = Protection.makeDigest(user, password, t1, q1);
		MessageDigest md = MessageDigest.getInstance("SHA");
		byte[] bb = Protection.makeBytes(t1, q1);//Protection.getDigest(user, password, t1, q1);//md.digest(protected1);
		String sss = bb.toString();
				
		System.out.println("sss  "+sss);
		String s1 = in.readUTF();
		System.out.println("S1 ::: "+s1);
		int val = in.read(protected1);
		System.out.println("Val ::: "+val );
	}

	public static void main(String[] args) 
	{
		TestCrypto cryp = new TestCrypto();
		try
		{
			FileOutputStream out = new FileOutputStream("D:/pass.txt");
			cryp.sendAuthentication("piku", "pikupiku", out);
			
			FileInputStream in = new FileInputStream("D:/pass.txt");
			cryp.getData("piku", "pikupiku", in);
		}
		catch(Exception e )
		{
			e.printStackTrace();
		}
		

	}

}

class Protection 
{
	  public static byte[] makeDigest(String user, String password,
	      long t1, double q1) throws NoSuchAlgorithmException 
	      {
	    MessageDigest md = MessageDigest.getInstance("SHA");
	    md.update(user.getBytes());
	    md.update(password.getBytes());
	    md.update(makeBytes(t1, q1));
	    return md.digest();
	  }
	  
	  public static byte[] getDigest(String user, String password, long t1,
			double q1) throws NoSuchAlgorithmException
	  {
		  MessageDigest md = MessageDigest.getInstance("SHA");
		  return md.digest();
	  }
	 
	  public static byte[] makeBytes(long t, double q) {
	    try {
	      ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
	      DataOutputStream dataOut = new DataOutputStream(byteOut);
	      dataOut.writeLong(t);
	      dataOut.writeDouble(q);
	      return byteOut.toByteArray();
	    }
	    catch (IOException e) {
	      return new byte[0];
	    }
	  }
	}

