/*
 *
 * Copyright 2002 Sun Microsystems, Inc. All Rights Reserved.
 * 
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 * 
 * - Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 * 
 * - Redistribution in binary form must reproduce the above
 *   copyright notice, this list of conditions and the following
 *   disclaimer in the documentation and/or other materials
 *   provided with the distribution.
 * 
 * Neither the name of Sun Microsystems, Inc. or the names of
 * contributors may be used to endorse or promote products derived
 * from this software without specific prior written permission.
 * 
 * This software is provided "AS IS," without a warranty of any
 * kind. ALL EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND
 * WARRANTIES, INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE HEREBY
 * EXCLUDED. SUN AND ITS LICENSORS SHALL NOT BE LIABLE FOR ANY
 * DAMAGES OR LIABILITIES SUFFERED BY LICENSEE AS A RESULT OF OR
 * RELATING TO USE, MODIFICATION OR DISTRIBUTION OF THIS SOFTWARE OR
 * ITS DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE
 * FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT,
 * SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF
 * THE USE OF OR INABILITY TO USE THIS SOFTWARE, EVEN IF SUN HAS
 * BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * You acknowledge that this software is not designed, licensed or
 * intended for use in the design, construction, operation or
 * maintenance of any nuclear facility.
 * 
 */
import java.rmi.RemoteException; 
import javax.rmi.PortableRemoteObject;
import javax.ejb.*;
import javax.naming.*;
import javax.jms.*;
import java.util.Random;

/**
 * The ReserveOfficeMsgBean class is a message-driven bean. It
 * implements the javax.ejb.MessageDrivenBean and 
 * javax.jms.MessageListener interfaces. It is defined as public 
 * (but not final or abstract).  It defines a constructor and the
 * methods ejbCreate, onMessage, setMessageDrivenContext, and
 * ejbRemove.
 */
public class ReserveOfficeMsgBean implements MessageDrivenBean, 
        MessageListener {

    private transient MessageDrivenContext mdc = null;
    private SetupOfficeLocalHome soLocalHome = null;
    private Random processingTime = new Random();
    
    /**
     * Constructor, which is public and takes no arguments.
     */
    public ReserveOfficeMsgBean() {
        System.out.println("In " +
            "ReserveOfficeMsgBean.ReserveOfficeMsgBean()");
    }

    /**
     * setMessageDrivenContext method, declared as public (but
     * not final or static), with a return type of void, and with
     * one argument of type javax.ejb.MessageDrivenContext.
     *
     * @param mdc    the context to set
     */
    public void setMessageDrivenContext(MessageDrivenContext mdc)
    {
        System.out.println("In " +
            "ReserveOfficeMsgBean.setMessageDrivenContext()");
        this.mdc = mdc;
    }

    /**
     * ejbCreate method, declared as public (but not final or
     * static), with a return type of void, and with no 
     * arguments. It looks up the entity bean and gets a handle
     * to its home interface.
     */
    public void ejbCreate() {
        System.out.println("In " +
            "ReserveOfficeMsgBean.ejbCreate()");
        try {
            Context initial = new InitialContext();
            Object objref = 
              initial.lookup("java:comp/env/ejb/MyEjbReference");
            soLocalHome = (SetupOfficeLocalHome)
                PortableRemoteObject.narrow(objref, 
                    SetupOfficeLocalHome.class);
        } catch (Exception ex) {
            System.err.println("ReserveOfficeMsgBean." +
                "ejbCreate: Exception: " + ex.toString());
        }
    }

    /**
     * onMessage method, declared as public (but not final or 
     * static), with a return type of void, and with one argument
     * of type javax.jms.Message.
     *
     * Casts the incoming Message to a MapMessage, retrieves its
     * contents, and assigns the new hire to an office. Calls the
     * compose method to store the information in the entity 
     * bean.
     *
     * @param inMessage    the incoming message
     */
    public void onMessage(Message inMessage) {
        MapMessage msg = null;
        String key = null;
        String name = null;
        String position = null;
        int officeNumber = 0;

        try {
            if (inMessage instanceof MapMessage) {
                msg = (MapMessage) inMessage;
                System.out.println("  ReserveOfficeMsgBean:" +
                    " Message received.");
                key = msg.getString("HireID");
                name = msg.getString("Name");
                position = msg.getString("Position");
                
                officeNumber = new Random().nextInt(300) + 1;

                /* Simulate processing time taking 1 to 10 
                 * seconds.
                 */
                Thread.sleep(processingTime.nextInt(10) * 1000);
                compose(key, name, officeNumber, msg);
            } else {
                System.err.println("Message of wrong type: " + 
                    inMessage.getClass().getName());
            }
        } catch (JMSException e) {
            System.err.println("ReserveOfficeMsgBean." +
                "onMessage: JMSException: " + e.toString());
            mdc.setRollbackOnly();
        } catch (Throwable te) {
            System.err.println("ReserveOfficeMsgBean." +
                "onMessage: Exception: " + te.toString());
        }
    }
    
    /**
     * compose method, helper to onMessage method.
     *
     * Locates the row of the database represented by the primary
     * key and adds the office number allocated for the new hire.
     *
     * @param key           employee ID, primary key
     * @param name          employee name
     * @param officeNumber  office number
     * @param msg           the message received
     */
    void compose (String key, String name, int officeNumber, 
            Message msg) {
        int num = 0;
        SetupOffice so = null;
        
        try {
            try {
                so = soLocalHome.findByPrimaryKey(key);
                System.out.println("  ReserveOfficeMsgBean: " +
                    "Found join entity bean for employeeId " +
                    key);
            } catch (ObjectNotFoundException onfe) {
                System.out.println("  ReserveOfficeMsgBean: " +
                    "Creating join entity bean for " +
                    "employeeId " + key);
                so = soLocalHome.createLocal(key, name);
            }
            so.doOfficeNumber(officeNumber, msg);
            System.out.println("  ReserveOfficeMsgBean: " +
                "employeeId " + key + " (" + 
                so.getEmployeeName() + ") has the following " +
                "office: " + so.getOfficeNumber());
        } catch (Exception ex) {
            System.err.println(" ReserveOfficeMsgBean." + 
                "compose: Exception: " + ex.toString());
            mdc.setRollbackOnly();
        }
    }
    
    /**
     * ejbRemove method, declared as public (but not final or 
     * static), with a return type of void, and with no 
     * arguments.
     */
    public void ejbRemove() {
        System.out.println("In " +
            "ReserveOfficeMsgBean.ejbRemove()");
    }
}
