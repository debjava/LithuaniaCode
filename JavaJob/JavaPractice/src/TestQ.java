import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.UUID;


public class TestQ 
{
	public static void main(String[] args) 
	{
		List list = new ArrayList();
		for( int i = 0 ; i < 50 ; i++ )
		{
			list.add("Z-"+i);
		}
		System.out.println(list);
		for( int i = 0 ; i < list.size() ; i++ )
		{
			list.remove(i);
			list.add("T->"+i);
			System.out.println(list);
		}
//		System.out.println(list);
	}
}