
public class Test 
{
	public static void main(String[] args) 
	{
		String name = "tmpt";
		String searchString = "*";
		StringMatcher sm = new StringMatcher(searchString,false,false);
		
		boolean matchCase = sm.match(name);
		System.out.println(matchCase);
		
	}
}
