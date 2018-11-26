import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import sun.misc.BASE64Encoder;


public class MyTest 
{
	private Cipher ecipher;
    private Cipher dcipher;

    MyTest(SecretKey key) 
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

    public String encodeString(String str) 
    {
    	String encodedValue = null;
        try 
        {
            // Encode the string into bytes using utf-8
            byte[] utf8 = str.getBytes("UTF8");

            // Encrypt
            byte[] enc = ecipher.doFinal(utf8);

            // Encode bytes to base64 to get a string
//            return new sun.misc.BASE64Encoder().encode(enc);
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
        return encodedValue;
    }
    public String decodeString(String str) 
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

	public static void main(String[] args) 
	{
		SecretKey key;
		try 
		{
			key = KeyGenerator.getInstance("DES").generateKey();
			MyTest test = new MyTest(key);
			final String encryptedValue = test.encodeString("Nandu");
			System.out.println("Encrypted Value ::: "+encryptedValue);
			String decrypted = test.decodeString(encryptedValue);
			System.out.println("Decrypted Value ::: "+decrypted);
		}
		catch (NoSuchAlgorithmException e) 
		{
			e.printStackTrace();
		}
		

	}

}
