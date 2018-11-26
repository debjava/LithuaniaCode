
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.dnb.ope.security.KeyReader;
import com.dnb.ope.security.KeyUtil;

public class FileEncrypter 
{
	public static void encryptFile()
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
			String secretKeyString = KeyReader.getSecretKey();
			for( int i = 0, n = allFiles.length ; i < n ; i++ )
			{
				String filePath = allFiles[i].getAbsolutePath();
				String onlyFileName = allFiles[i].getName();
				InputStream in = new FileInputStream( filePath );
				OutputStream out = new FileOutputStream( encryptedDir+File.separator+onlyFileName);
				KeyUtil.encryptFile(in, out, secretKeyString);
			}
			
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) 
	{
		encryptFile();
	}
	
}
