package com.dnb.ope.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class KeyReader 
{
	public static String getSecretKey()
	{
		String secretKeyString = null;
		try
		{
			String keyDirPath = System.getProperty("user.dir")+File.separator+"key";
			String keyPath = keyDirPath+File.separator+"secretkey.key";
			Properties keyProp = new Properties();
			InputStream in = new FileInputStream( keyPath );
			keyProp.load(in);
			secretKeyString = keyProp.getProperty("key");
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return secretKeyString;
	}
}
