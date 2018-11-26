import java.io.File;
import java.security.PrivateKey;

import com.dds.core.security.FileUtil;
import com.dds.core.security.KeyReader;
import com.dds.core.security.SecurityUtil;



public class Decrypter 
{
	public static String decryptString( String encryptedString )
	{
		String deString = null;
		try
		{
			SecurityUtil securityUtil = new SecurityUtil();
			String privateString = KeyReader.getPrivateKeyString();
			PrivateKey priKey = securityUtil.getPrivateKeyFromString(privateString);
			deString = securityUtil.getDecryptedValue(encryptedString, priKey );
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return deString;
	}
	
	public static void decrypt()
	{
		try
		{
			String currentDir = System.getProperty("user.dir")+File.separator;
			String originalDir = currentDir+"encrypted";
			String decryptedDir = currentDir+"decrypted";
			File decryptedDirFile = new File( decryptedDir );
			if( !decryptedDirFile.exists() )
				decryptedDirFile.mkdirs();
			File originalDirFile = new File( originalDir );
			File[] allFiles = originalDirFile.listFiles();
			for( int i = 0, n = allFiles.length ; i < n ; i++ )
			{
				String filePath = allFiles[i].getAbsolutePath();
				String onlyFileName = allFiles[i].getName();
				String fileContents = FileUtil.getFileContents(filePath);
				String decryptedContents = decryptString(fileContents);
				FileUtil.writeToFile(decryptedDir+File.separator+onlyFileName, decryptedContents);
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		decrypt();
	}

}
