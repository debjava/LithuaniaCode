import java.io.FileWriter;
import java.io.RandomAccessFile;

import com.dds.core.KeyReader;
import com.dds.core.KeyUtil;



public class TestKeyGenerator 
{
	public static String getFileContents( String filePath )
	{
		String contents = null;
		try
		{
			RandomAccessFile raf = new RandomAccessFile(filePath,"r");
			int fileSize = ( int )raf.length();
			byte[] fileByte = new byte[ fileSize ];
			while( fileSize > 0 )
			{
				fileSize -= raf.read( fileByte, fileByte.length - fileSize , fileSize );
			}
			contents = new String( fileByte );
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return contents;
	}
	
	public static void writeContents( String filePath , String contents )
	{
		FileWriter writer = null;
		try
		{
			writer = new FileWriter( filePath );
			writer.write(contents);
			writer.close();
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}
	public static void main(String[] args) 
	{
//		KeyGenerator.storeKey();
		String secretKey = KeyReader.getSecretKey();
		System.out.println("SecretKey--"+secretKey);
		
//		String fileContents = getFileContents("E:/ CoreArticle/deskeytool/original/t1.txt");
//		String encryptedString = KeyUtil.getEncryptedContents(fileContents, secretKey);
//		System.out.println(encryptedString);
//		writeContents("E:/ CoreArticle/deskeytool/original/t1.txt", encryptedString);
		
		String fileContents = getFileContents("E:/ CoreArticle/deskeytool/original/t1.txt");
		String decryptedContents = KeyUtil.getDecryptedContents(fileContents, secretKey);
		System.out.println(decryptedContents);
		writeContents("E:/ CoreArticle/deskeytool/original/t1.txt", decryptedContents);
		
//		try
//		{
//			RandomAccessFile raf = new RandomAccessFile(
//					"E:/ CoreArticle/deskeytool/original/t1.txt", "r");
//			int fileSize = ( int )raf.length();
//			byte[] fileByte = new byte[ fileSize ];
//			while( fileSize > 0 )
//			{
//				fileSize -= raf.read( fileByte, fileByte.length - fileSize , fileSize );
//			}
//			String contents = new String( fileByte );
//			raf.close();
//			System.out.println(contents);
//			
//			String encryptedString = KeyUtil.getEncryptedContents(contents, secretKey);
//			System.out.println(encryptedString);
//			System.out.println("*********************************************");
//			String originalString = KeyUtil.getDecryptedContents(encryptedString, secretKey);
//			System.out.println(originalString);
//			
//		}
//		catch( Exception e )
//		{
//			e.printStackTrace();
//		}
	}
}
