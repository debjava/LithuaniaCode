
public class KhaliTest 
{
	public String getEncodedValue( final String string_parm )
	{
		return TestEncoderDecoder.encodeString( string_parm );
	}
	
	public String getDecodedString( final String encodeString_parm )
	{
		return TestEncoderDecoder.decodeString( encodeString_parm );
	}
	
	public static void main(String[] args) 
	{
//		final String encodedString = new KhaliTest().getEncodedValue( "piku is a good boy" );
//		System.out.println(" Encoded Value ::: "+encodedString );
//		final String decodedValue = new KhaliTest().getDecodedString( encodedString );
//		System.out.println("Decoded Value ::: "+decodedValue );
		
		final String pmStr = "PROCESSMATEPROCESSMATEPM.";
		System.out.println(" In Hex ::: "+pmStr.getBytes());
		
		
		
	}

}
