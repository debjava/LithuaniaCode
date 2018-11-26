package com.dds.misc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class TestEnumArra 
{
	public static void main(String[] args) 
	{
		try {
			FileInputStream fin = new FileInputStream("D:/data.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
