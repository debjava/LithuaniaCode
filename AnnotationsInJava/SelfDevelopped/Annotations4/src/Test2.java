class Test2 implements Runnable
{
    public void run() 
    {        
        // Add the "Manager" role for this user
        SecurityBlanket.addPermission("Manager");
        try
        {
            AnnotationTest test = new AnnotationTest();
            try
            {
                test.unRestrictedMethod("Hi");
            }
            catch (Exception ex)
            {
                System.out.println(ex.getMessage());
            }
            try
            {
                test.fullyRestrictedMethod("Hi");
            }
            catch (Exception ex)
            {
                System.out.println(ex.getMessage());
            }
            try
            {
                test.partiallyRestrictedMethod("Hi");
            }
            catch (Exception ex)
            {
                System.out.println(ex.getMessage());
            }
            try
            {
                test.partiallyRestrictedMethod2("Hi");
            }
            catch (Exception ex)
            {
                System.out.println(ex.getMessage());
            }
        }
        finally
        {
            SecurityBlanket.removePermission();
        }
    }
}
