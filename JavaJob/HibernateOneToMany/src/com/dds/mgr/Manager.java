package com.dds.mgr;

import org.hibernate.Session;

import com.dds.hib.Child;
import com.dds.hib.Parent;
import com.dds.util.HibernateUtil;

public class Manager 
{
	Session session = null ;
	
	public void storeDataInDatabase()
    {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Child c = new Child();
		c.setName("Child two");
		Child c1 = new Child();
		c1.setName("Deba");
		Parent p = new Parent();
		p.setName("Parent");
		p.getChildren().add(c1);
		p.getChildren().add(c);
		c.setParent(p);
		session.save(p);
		session.flush();
		session.getTransaction().commit();
        
//        session.save(obj);
//        session.save(obj2);
//        session.getTransaction().commit();
        
    }
	
	public static void main(String[] args) 
	{
		Manager mgr = new Manager();
		mgr.storeDataInDatabase();
		
	}

}
