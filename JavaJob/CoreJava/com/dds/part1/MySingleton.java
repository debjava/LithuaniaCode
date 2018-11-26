package com.dds.part1;

public class MySingleton 
{
    private MySingleton() 
    {
        // construct the singleton instance
    	getInstance();
    }
    
    public static MySingleton getInstance() 
    {
        return Holder.instance;
    }
 
   private static class Holder
      {
      static final MySingleton instance = new MySingleton();
      }
   public void show()
   {
	   System.out.println("Hi I am in Singleton");
   }
 
}
