@SecurityPermission("All")
public class AnnotationTest
{
    public AnnotationTest()
    {
        SecurityBlanket.checkPermission();
    }
    @SecurityPermission("All")
    public void unRestrictedMethod(String message)
    {
        SecurityBlanket.checkPermission();
        System.out.println("Message from unRestrictedMethod: " + message);
    }
    @SecurityPermission("None")
    public void fullyRestrictedMethod(String message)
    {
        SecurityBlanket.checkPermission();
        System.out.println("Message from fullyRestrictedMethod: " + message);
    }
    @SecurityPermission("Manager")
    public void partiallyRestrictedMethod(String message)
    {
        SecurityBlanket.checkPermission();
        System.out.println("Message from partiallyRestrictedMethod: " + message);
    }
    @SecurityPermission({"Manager","HR"})
    public void partiallyRestrictedMethod2(String message)
    {
        SecurityBlanket.checkPermission();
        System.out.println("Message from partiallyRestrictedMethod2: " + message);
    }
}


