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
 * The ScheduleMsgBean class is a message-driven bean.
 * It implements the javax.ejb.MessageDrivenBean and 
 * javax.jms.MessageListener interfaces. It is defined as public 
 * (but not final or abstract).  It defines a constructor and the
 * methods ejbCreate, onMessage, setMessageDrivenContext, and
 * ejbRemove.
 */
public class ScheduleMsgBean implements MessageDrivenBean, 
        MessageListener {

    private transient MessageDrivenContext mdc = null;
    
    private SetupOfficeLocalHome soLocalHome = null;

    /**
     * Constructor, which is public and takes no arguments.
     */
    public ScheduleMsgBean() {
        System.out.println("In " +
            "ScheduleMsgBean.ScheduleMsgBean()");
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
            "ScheduleMsgBean.setMessageDrivenContext()");
        this.mdc = mdc;
    }

    /**
     * ejbCreate method, declared as public (but not final or 
     * static), with a return type of void, and with no 
     * arguments. It looks up the entity bean and gets a handle
     * to its home interface.
     */
    public void ejbCreate() {
        System.out.println("In ScheduleMsgBean.ejbCreate()");
        try {
            Context initial = new InitialContext();
            Object objref = 
       initial.lookup("java:comp/env/ejb/CompositeEjbReference");
            soLocalHome = (SetupOfficeLocalHome)
                PortableRemoteObject.narrow(objref,
                    SetupOfficeLocalHome.class);
        } catch (Exception ex) {
            System.err.println("ScheduleMsgBean.ejbCreate: " +
                 "Exception: " + ex.toString());
        }
    }

    /**
     * onMessage method, declared as public (but not final or 
     * static), with a return type of void, and with one argument
     * of type javax.jms.Message.
     *
     * Casts the incoming Message to a TextMessage, retrieves its
     * handle to the SetupOffice entity bean, and schedules
     * office setup based on information joined in the entity
     * bean. When finished with data, deletes the entity bean.
     *
     * @param inMessage    the incoming message
     */
    public void onMessage(Message inMessage) {
        String key = null;
        SetupOffice setupOffice = null;

        try {
            if (inMessage instanceof TextMessage) {
                System.out.println("  ScheduleMsgBean:" +
                    " Message received.");
                key = ((TextMessage)inMessage).getText();
                System.out.println("  ScheduleMsgBean: " +
                    "Looking up SetupOffice bean by primary " +
                    "key=" + key);
                setupOffice = soLocalHome.findByPrimaryKey(key);

                /* 
                 * Schedule office setup using contents of 
                 * SetupOffice entity bean.
                 */
                System.out.println("  ScheduleMsgBean: " +
                    "SCHEDULE employeeId=" +
                    setupOffice.getEmployeeId() + ", Name=" +
                    setupOffice.getEmployeeName() +
                    " to be set up in office #" +
                    setupOffice.getOfficeNumber() + " with " +
                    setupOffice.getEquipmentList());
                
                // All done. Remove SetupOffice entity bean.
                setupOffice.remove();
            } else {
                System.err.println("Message of wrong type: " +
                    inMessage.getClass().getName());
            }
        } catch (JMSException e) {
            System.err.println("ScheduleMsgBean.onMessage: " +
                "JMSException: " + e.toString());
            mdc.setRollbackOnly();
        } catch (Throwable te) {
            System.err.println("ScheduleMsgBean.onMessage: " +
                "Exception: " + te.toString());
        }
    }
    
    /**
     * ejbRemove method, declared as public (but not final or 
     * static), with a return type of void, and with no 
     * arguments.
     */
    public void ejbRemove() {
        System.out.println("In ScheduleMsgBean.ejbRemove()");
    }
}
