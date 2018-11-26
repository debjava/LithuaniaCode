package com.core.cc.handler;

public class AbstractHandler 
{
	public static Handler getHandler( String handlerName )
	{
		Handler handler = null;
		try
		{
			handler = (Handler) Class.forName(
					"com.core.cc.handler." + handlerName + "Handler")
					.newInstance();
		}
		catch( Exception e )
		{
			System.out.println("There is no specific handler");
		}
		return handler;
	}
}
