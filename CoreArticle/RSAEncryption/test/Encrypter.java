import java.io.File;
import java.security.PublicKey;

import com.dds.core.security.FileUtil;
import com.dds.core.security.KeyReader;
import com.dds.core.security.SecurityUtil;



public class Encrypter 
{
	public static String encryptContents( String originalString )
	{
		String enString = null;
		try
		{
			SecurityUtil securityUtil = new SecurityUtil();
			String publicString = KeyReader.getPublicKeyString();
			PublicKey pubKey = securityUtil.getPublicKeyFromString(publicString);
			enString = securityUtil.getEncryptedValue(originalString, pubKey);
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return enString;
	}
	
	public static void encrypt()
	{
		try
		{
			String currentDir = System.getProperty("user.dir")+File.separator;
			String originalDir = currentDir+"original";
			String encryptedDir = currentDir+"encrypted";
			File encryptedDirFile = new File( encryptedDir );
			if( !encryptedDirFile.exists() )
				encryptedDirFile.mkdirs();
			File originalDirFile = new File( originalDir );
			File[] allFiles = originalDirFile.listFiles();
			
			for( int i = 0, n = allFiles.length ; i < n ; i++ )
			{
				String filePath = allFiles[i].getAbsolutePath();
				String onlyFileName = allFiles[i].getName();
				String fileContents = FileUtil.getFileContents(filePath);
				String encryptedContents = encryptContents(fileContents);
				FileUtil.writeToFile(encryptedDir+File.separator+onlyFileName, encryptedContents);
			}
			
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) 
	{
		encrypt();
	}
}
