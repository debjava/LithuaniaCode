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
import java.io.*;
import java.util.*;
import javax.ejb.*;
import javax.naming.*;
import javax.jms.*;

/**
 * The SetupOfficeBean class implements the business methods of
 * the entity bean.  Because the bean uses version 2.0 of 
 * container-managed persistence, the bean class and the 
 * accessor methods for fields to be persisted are all declared 
 * abstract.
 */
public abstract class SetupOfficeBean implements EntityBean {

    abstract public String getEmployeeId();
    abstract public void setEmployeeId(String id);

    abstract public String getEmployeeName();
    abstract public void setEmployeeName(String name);

    abstract public int getOfficeNumber();
    abstract public void setOfficeNumber(int officeNum);

    abstract public String getEquipmentList();
    abstract public void setEquipmentList(String equip);

    abstract public byte[] getSerializedReplyDestination();
    abstract public void setSerializedReplyDestination(byte[] 
        byteArray);

    abstract public String getReplyCorrelationMsgId();
    abstract public void setReplyCorrelationMsgId(String msgId);

    /*
     * There should be a list of replies for each message being
     * joined.  This example is joining the work of separate
     * departments on the same original request, so it is all
     * right to have only one reply destination.  In theory, this
     * should be a set of destinations, with one reply for each
     * unique destination.
     *
     * Because a Destination is not a data type that can be 
     * persisted, the persisted field is a byte array,
     * serializedReplyDestination, that is created and accessed
     * with the setReplyDestination and getReplyDestination 
     * methods.
     */

    transient private Destination      replyDestination;
    transient private Queue            scheduleQueue;
    transient private QueueConnection  queueConnection;
    private EntityContext context;

    /**
     * The getReplyDestination method extracts the
     * replyDestination from the serialized version that is
     * persisted, using a ByteArrayInputStream and
     * ObjectInputStream to read the object and casting it to a
     * Destination object.
     * 
     * @return    the reply destination
     */
    private Destination getReplyDestination() {
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        byte[] srd = null;

        srd = getSerializedReplyDestination();
        if (replyDestination == null && srd != null) {
            try {
                bais = new ByteArrayInputStream(srd);
                ois = new ObjectInputStream(bais);
                replyDestination = (Destination)ois.readObject();
                ois.close();
            } catch (IOException io) {
            } catch (ClassNotFoundException cnfe) {}
        }

        return replyDestination;
    }

    /**
     * The setReplyDestination method serializes the reply 
     * destination so that it can be persisted.  It uses a 
     * ByteArrayOutputStream and an ObjectOutputStream.
     *
     * @param replyDestination    the reply destination
     */
    private void setReplyDestination(Destination 
            replyDestination) {
        ByteArrayOutputStream baos = null;
        ObjectOutputStream oos = null;

        this.replyDestination = replyDestination;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(replyDestination);
            oos.close();
            setSerializedReplyDestination(baos.toByteArray());
        } catch (IOException io) {
        }
    }

    /**
     * The doEquipmentList method stores the assigned equipment
     * in the database and retrieves the reply destination, then
     * determines if setup is complete.
     *
     * @param list    assigned equipment
     * @param msg     the message received
     */
    public void doEquipmentList(String list, Message msg) 
            throws JMSException {
        setEquipmentList(list);
        setReplyDestination(msg.getJMSReplyTo());
        setReplyCorrelationMsgId(msg.getJMSMessageID());
        System.out.println("  SetupOfficeBean." +
            "doEquipmentList: equipment is " +
            getEquipmentList() + " (office number " +
            getOfficeNumber() + ")");
        checkIfSetupComplete();
    }

    /**
     * The doOfficeNumber method stores the assigned office
     * number in the database and retrieves the reply
     * destination, then determines if setup is complete.
     *
     * @param officeNum    assigned office
     * @param msg          the message received
     */
    public void doOfficeNumber(int officeNum, Message msg) 
            throws JMSException {
        setOfficeNumber(officeNum);
        setReplyDestination(msg.getJMSReplyTo());
        setReplyCorrelationMsgId(msg.getJMSMessageID());
        System.out.println("  SetupOfficeBean." +
            "doOfficeNumber: office number is " +
            getOfficeNumber() + " (equipment " + 
            getEquipmentList() + ")");
        checkIfSetupComplete();
    }

    /**
     * The checkIfSetupComplete method determines whether
     * both the office and the equipment have been assigned.  If
     * so, it sends messages to the schedule queue and the reply
     * queue with the information about the assignments.
     */
    private void checkIfSetupComplete() {
        QueueConnection qCon = null;
        QueueSession    qSession = null;
        QueueSender     qSender = null;
        TextMessage     schedMsg = null;
        MapMessage      replyMsg = null;

        if (getEquipmentList() != null && 
            getOfficeNumber() != -1) {
            System.out.println("  SetupOfficeBean." +
                "checkIfSetupComplete: SCHEDULE" +
                " employeeId=" + getEmployeeId() + ", Name=" +
                getEmployeeName() + " to be set up in office #" +
                getOfficeNumber() + " with " + 
                getEquipmentList());

            try {
                qCon = getQueueConnection();
            } catch (Exception ex) {
                throw new EJBException("Unable to connect to " +
                    "JMS provider: " + ex.toString());
            }

            try {
                /* 
                 * Compose and send message to schedule office
                 * setup queue.
                 */
                qSession = qCon.createQueueSession(true, 0);
                qSender = qSession.createSender(null);
                schedMsg = 
                    qSession.createTextMessage(getEmployeeId());
                qSender.send(scheduleQueue, schedMsg);

                /*
                 * Send reply to messages aggregated by this
                 * composite entity bean.
                 */
                replyMsg = qSession.createMapMessage();
                replyMsg.setString("employeeId", 
                    getEmployeeId());
                replyMsg.setString("employeeName", 
                    getEmployeeName());
                replyMsg.setString("equipmentList", 
                    getEquipmentList());
                replyMsg.setInt("officeNumber", 
                    getOfficeNumber());
        replyMsg.setJMSCorrelationID(getReplyCorrelationMsgId());
                qSender.send((Queue)getReplyDestination(), 
                    replyMsg);
            } catch (JMSException je) {
                System.err.println("SetupOfficeBean." +
                    "checkIfSetupComplete: " + "JMSException: " +
                    je.toString());
            }
        }
    }

    /**
     * ejbCreateLocal method, declared as public (but not final
     * or static).  Stores the available information about the
     * new hire in the database.
     *
     * @param newhireID   ID assigned to the new hire
     * @param name        name of the new hire
     *
     * @return            null (required for CMP 2.0)
     */
    public String ejbCreateLocal(String newhireID, String name)
        throws CreateException {
        
        setEmployeeId(newhireID);
        setEmployeeName(name);
        setEquipmentList(null);
        setOfficeNumber(-1);

        this.queueConnection = null;
        return null;
    }

    public void ejbRemove() {
        closeQueueConnection();
        System.out.println("  SetupOfficeBean.ejbRemove: " +
            "REMOVING SetupOffice bean employeeId=" +
            getEmployeeId() + ", Name=" + getEmployeeName());
    } 

    public void setEntityContext(EntityContext context) {
        this.context = context;
    }

    public void unsetEntityContext() {
        this.context = null;
    }

    public void ejbActivate() {
        setEmployeeId((String) context.getPrimaryKey());
    }

    public void ejbPassivate() {
        setEmployeeId(null);
        closeQueueConnection();
    }

    public void ejbLoad() {}

    public void ejbStore() {}

    public void ejbPostCreateLocal(String newhireID, 
        String name) {}

    /**
     * The getQueueConnection method, called by the 
     * checkIfSetupComplete method, looks up the schedule queue
     * and connection factory and creates a QueueConnection.
     *
     * @return   a QueueConnection object
     */ 
    private QueueConnection getQueueConnection() 
            throws NamingException, JMSException {

        if (queueConnection == null) {
            InitialContext ic = new InitialContext();
            
            QueueConnectionFactory queueConnectionFactory =
                (QueueConnectionFactory)
           ic.lookup("java:comp/env/jms/QueueConnectionFactory");
            scheduleQueue = 
            (Queue) ic.lookup("java:comp/env/jms/ScheduleQueue");
            queueConnection = 
                queueConnectionFactory.createQueueConnection();
        }
        return queueConnection;
    }

    /**
     * The closeQueueConnection method, called by the ejbRemove
     * and ejbPassivate methods, closes the QueueConnection that
     * was created by the getQueueConnection method.
     */
    private void closeQueueConnection() {
        if (queueConnection != null) {
            try {
                queueConnection.close();
            } catch (JMSException je) {
                System.err.println("SetupOfficeBean." +
                    "closeQueueConnection: JMSException: " +
                    je.toString());
            }
            queueConnection = null;
        }
    }
}
