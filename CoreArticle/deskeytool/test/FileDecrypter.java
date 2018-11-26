

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.dnb.ope.security.KeyReader;
import com.dnb.ope.security.KeyUtil;

public class FileDecrypter 
{
	public static void decryptFile()
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
			String secretKeyString = KeyReader.getSecretKey();
			for( int i = 0, n = allFiles.length ; i < n ; i++ )
			{
				String filePath = allFiles[i].getAbsolutePath();
				String onlyFileName = allFiles[i].getName();
				InputStream in = new FileInputStream( filePath );
				OutputStream out = new FileOutputStream( decryptedDir+File.separator+onlyFileName);
				KeyUtil.decryptFile(in, out, secretKeyString);
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) 
	{
		decryptFile();
	}
	
}
