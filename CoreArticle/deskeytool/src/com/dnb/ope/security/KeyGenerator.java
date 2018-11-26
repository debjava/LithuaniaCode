package com.dnb.ope.security;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

public class KeyGenerator 
{
	private static String getKeyFilePath()
	{
		String keyPath = null;
		try
		{
			String keyDirPath = System.getProperty("user.dir")+File.separator+"key";
			File keyDir = new File( keyDirPath );
			if( !keyDir.exists() )
				keyDir.mkdirs();
			keyPath = keyDirPath+File.separator+"secretkey.key";
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return keyPath;
	}
	
	public static void storeKey()
	{
		try
		{
			Properties keyProp = new Properties();
			OutputStream out = new FileOutputStream( getKeyFilePath() );
			keyProp.put("key", KeyUtil.generateSecretKey() );
			keyProp.store(out, "Secret key information, do not modify the key.");
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		
	}
}
