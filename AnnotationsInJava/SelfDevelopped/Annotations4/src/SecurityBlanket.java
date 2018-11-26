import java.lang.reflect.Method;
import java.util.HashMap;

public class SecurityBlanket
{
    private static HashMap<Thread, String> permissions = new HashMap<Thread, String>();
    public static void addPermission(String s)
    {
        permissions.put(Thread.currentThread(),s);
    }
    public static void removePermission()
    {
        permissions.remove(Thread.currentThread());
    }
    public static void checkPermission()
    {
        StackTraceElement e[]=Thread.currentThread().getStackTrace();
        int i = 0;
        for (i = 0; i <e.length; i++)
        {
            if (e[i].getClassName().equals("SecurityBlanket") 
                && e[i].getMethodName().equals("checkPermission"))
                break;
        }
        if (i == e.length)
        {
            throw new RuntimeException("Unexpected Security Error.");
        }
        System.out.println("Checking security access to " 
                    + e[i+1].getClassName() 
                    + "::" 
                    + e[i+1].getMethodName()); 
        try
        {
            Class c = Class.forName(e[i+1].getClassName());
            if (e[i+1].getMethodName().equals("<init>"))
            {
                SecurityPermission permission = 
                    (SecurityPermission)
                        c.getAnnotation(SecurityPermission.class);
                if (permission != null)
                {
                    String currentRole = 
                        permissions.get(Thread.currentThread());
                    for (String role:permission.value())
                    {
                        if (role.equals("All"))
                            return;
                        else if (role.equals("None"))
                        {
                            throw new RuntimeException(
                                "Unauthorized access to class " 
                                   + e[i+1].getClassName());
                        }
                        if (role.equals(currentRole))
                            return;
                    }
                }
                return;
            }
            Method[] methods = c.getMethods();
            for (Method m:methods)
            {
                if (m.getName().equals(e[i+1].getMethodName()))
                {
                    SecurityPermission permission = 
                        m.getAnnotation(SecurityPermission.class);
                    if (permission != null)
                    {
                        String currentRole = 
                            permissions.get(Thread.currentThread());
                        for (String role:permission.value())
                        {
                            if (role.equals("All"))
                                return;
                            else if (role.equals("None"))
                            {
                                throw new RuntimeException(
                                    "Unauthorized access to " 
                                        + e[i+1].getClassName() 
                                        + "::" 
                                        + e[i+1].getMethodName());
                            }
                            if (role.equals(currentRole))
                                return;
                        }
                    }
                    break;
                }
            }
            throw new RuntimeException("Unauthorized access to " 
                                       + e[i+1].getClassName() 
                                       + "::" 
                                       + e[i+1].getMethodName());
        }
        catch (ClassNotFoundException ex)
        {
        	System.out.println("Got an error: " + ex.getMessage()); 
            throw new RuntimeException("Unexpected Security Error.");
        }
    }
} 


