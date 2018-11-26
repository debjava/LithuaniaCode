import java.lang.annotation.Annotation;
import java.lang.reflect.Field;


public class Emp 
{
	@MinLength(10)
	@MaxLength(20)
	private String name;
	

	public String getName()
	{
		return name;
	}
	@MinLength(10)
	@MaxLength(20)
	public void setName(String name)
	{
		try
		{
//			Field[] fields = getClass().getDeclaredFields();
//			for( Field f : fields )
//				System.out.println(f.getName());
			Annotation an = getClass().getDeclaredField("name").getAnnotation(MaxLength.class);
			System.out.println(an);
			
			Field field = getClass().getDeclaredField("name");
			Annotation[] anotations = field.getAnnotations();
			for (Annotation a : anotations)
			{
				if( a instanceof MaxLength )
				{
					MaxLength max = ( MaxLength )a;
					if( name.length() > max.value()  )
						throw new Exception("Max value can not be greater than 20 chars");
				}
				if( a instanceof MinLength )
				{
					MinLength min = ( MinLength )a;
					if( name.length() < min.value() )
						throw new Exception("Min value can not be less than 10 chars");
				}
					
			}
			
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		
		this.name = name;
	}
	
}
