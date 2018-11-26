import com.core.cc.handler.AbstractHandler;
import com.core.cc.handler.Handler;


public class TestDynamicHandler 
{
	public static void main(String[] args) 
	{
		Handler handler = AbstractHandler.getHandler("B");
		handler.handle();
	}

}
