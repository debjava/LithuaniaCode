import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import sun.misc.BASE64Encoder;


public class TestEncoderDecoder 
{
	private static Cipher ecipher;
    private static Cipher dcipher;
    
    static 
    {
    	try
    	{
    		SecretKey  secretKey = KeyGenerator.getInstance("DES").generateKey();
			ecipher = Cipher.getInstance("DES");
            ecipher.init(Cipher.ENCRYPT_MODE, secretKey );
            dcipher = Cipher.getInstance("DES");
            dcipher.init(Cipher.DECRYPT_MODE, secretKey );
    	}
    	catch( Exception e )
    	{
    		e.printStackTrace();
    	}
    }
    
    public TestEncoderDecoder()
    {
    	try 
    	{
			SecretKey  secretKey = KeyGenerator.getInstance("DES").generateKey();
			ecipher = Cipher.getInstance("DES");
            ecipher.init(Cipher.ENCRYPT_MODE, secretKey );
            dcipher = Cipher.getInstance("DES");
            dcipher.init(Cipher.DECRYPT_MODE, secretKey );
		}
    	catch( InvalidKeyException inke )
    	{
    		inke.printStackTrace();
    	}
    	catch( NoSuchPaddingException  nspe )
    	{
    		nspe.printStackTrace();
    	}
    	catch (NoSuchAlgorithmException e) 
    	{
			e.printStackTrace();
		}
    }

    public TestEncoderDecoder(SecretKey key) 
    {
        try 
        {
            ecipher = Cipher.getInstance("DES");
            ecipher.init(Cipher.ENCRYPT_MODE, key);
            dcipher = Cipher.getInstance("DES");
            dcipher.init(Cipher.DECRYPT_MODE, key);

        }
        catch (javax.crypto.NoSuchPaddingException e) 
        {
        	e.printStackTrace();
        }
        catch (java.security.NoSuchAlgorithmException e) 
        {
        	e.printStackTrace();
        }
        catch (java.security.InvalidKeyException e) 
        {
        	e.printStackTrace();
        }
    }

    public static String encodeString(String str) 
    {
    	String encodedValue = null;
        try 
        {
            // Encode the string into bytes using utf-8
            byte[] utf8 = str.getBytes("UTF8");

            // Encrypt
            byte[] enc = ecipher.doFinal(utf8);

            // Encode bytes to base64 to get a string
            encodedValue = new BASE64Encoder().encode(enc);
        }
        catch (javax.crypto.BadPaddingException e) 
        {
        	e.printStackTrace();
        }
        catch (IllegalBlockSizeException e) 
        {
        	e.printStackTrace();
        } catch (UnsupportedEncodingException e) 
        {
        	e.printStackTrace();
        } catch (java.io.IOException e) 
        {
        	e.printStackTrace();
        }
        catch( Exception e)
        {
        	e.printStackTrace();
        }
        return encodedValue;
    }
    public static String decodeString(String str) 
    {
    	String decodedValue = null;
        try {
            // Decode base64 to get bytes
            byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);

            // Decrypt
            byte[] utf8 = dcipher.doFinal(dec);

            // Decode using utf-8
//            return new String(utf8, "UTF8");
            decodedValue = new String(utf8, "UTF8");
        } 
        catch (Exception e) 
        {
        }
        return decodedValue;
    }

//	public static void main(String[] args) 
//	{
//		SecretKey key;
//		try 
//		{
////			key = KeyGenerator.getInstance("DES").generateKey();
////			TestEncoderDecoder test = new TestEncoderDecoder(key);
//			TestEncoderDecoder tested = new TestEncoderDecoder();
//			String encryptedValue = TestEncoderDecoder.encodeString("Nandu");
//			System.out.println("Encrypted Value ::: "+encryptedValue);
//			String decrypted = TestEncoderDecoder.decodeString(encryptedValue);
//			System.out.println("Decrypted Value ::: "+decrypted);
//		}
//		catch ( Exception e) 
//		{
//			e.printStackTrace();
//		}
//		
//
//	}


}
