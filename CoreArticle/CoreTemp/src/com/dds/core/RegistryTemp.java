package com.dds.core;

import java.io.File;


public class RegistryTemp {
	
	public static void main(String[] args)
	{
		File[] rootDir = File.listRoots();
		for( int i = 0 ; i < rootDir.length ; i++ )
		{
			System.out.println(rootDir[i].getAbsolutePath());
		}
	}

}
