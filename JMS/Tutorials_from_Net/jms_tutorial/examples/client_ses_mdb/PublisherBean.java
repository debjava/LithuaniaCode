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
import java.util.*;
import javax.ejb.*;
import javax.naming.*;
import javax.jms.*;

/**
 * Bean class for Publisher enterprise bean. Defines publishNews
 * business method as well as required methods for a stateless
 * session bean.
 */
public class PublisherBean implements SessionBean {
    SessionContext      sc = null;
    TopicConnection     topicConnection = null;
    Topic               topic = null;
    final static String messageTypes[] = {"Nation/World", 
        "Metro/Region", "Business", "Sports", "Living/Arts", 
        "Opinion"};

    public PublisherBean() {
        System.out.println("In PublisherBean() (constructor)");
    }

    /**
     * Sets the associated session context. The container calls 
     * this method after the instance creation.
     */
    public void setSessionContext(SessionContext sc) {
        this.sc = sc;
    }

    /**
     * Instantiates the enterprise bean.  Creates the 
     * TopicConnection and looks up the topic.
     */
    public void ejbCreate() {
        Context context = null;
        TopicConnectionFactory topicConnectionFactory = null;

        System.out.println("In PublisherBean.ejbCreate()");
        try {
            context = new InitialContext();
            topic = (Topic)
                context.lookup("java:comp/env/jms/TopicName");

            // Create a TopicConnection
            topicConnectionFactory = (TopicConnectionFactory) 
    context.lookup("java:comp/env/jms/MyTopicConnectionFactory");
            topicConnection = 
                topicConnectionFactory.createTopicConnection();
        } catch (Throwable t) {
            // JMSException or NamingException could be thrown
            System.err.println("PublisherBean.ejbCreate:" +
                "Exception: " + t.toString());
        }
    }

    /**
     * Chooses a message type by using the random number
     * generator found in java.util.  Called by publishNews().
     *
     * @return   the String representing the message type
     */
    private String chooseType() {
        int    whichMsg;
        Random rgen = new Random();
       
        whichMsg = rgen.nextInt(messageTypes.length);
        return messageTypes[whichMsg];
    }

    /**
     * Creates TopicSession, publisher, and message.  Publishes 
     * messages after setting their NewsType property and using
     * the property value as the message text. Messages are
     * received by MessageBean, a message-driven bean that uses a
     * message selector to retrieve messages whose NewsType
     * property has certain values.
     */
    public void publishNews() throws EJBException {
        TopicSession   topicSession = null;
        TopicPublisher topicPublisher = null;
        TextMessage    message = null;
        int            numMsgs = messageTypes.length * 3;
        String         messageType = null;

        try {
            topicSession = 
                topicConnection.createTopicSession(true, 0);
            topicPublisher = topicSession.createPublisher(topic);
            message = topicSession.createTextMessage();
            for (int i = 0; i < numMsgs; i++) {
                messageType = chooseType();
                message.setStringProperty("NewsType", 
                    messageType);
                message.setText("Item " + i + ": " + 
                    messageType);
                System.out.println("PUBLISHER: Setting " +
                    "message text to: " + message.getText());
                topicPublisher.publish(message);
            }
        } catch (Throwable t) {
            // JMSException could be thrown
            System.err.println("PublisherBean.publishNews: " +
                 "Exception: " + t.toString());
            sc.setRollbackOnly();
        } finally {
            if (topicSession != null) {
                try {
                    topicSession.close();
                } catch (JMSException e) {}
            }
        }
    }

    /**
     * Closes the TopicConnection.
     */
    public void ejbRemove() throws RemoteException {
        System.out.println("In PublisherBean.ejbRemove()");
        if (topicConnection != null) {
            try {
                topicConnection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void ejbActivate() {}
    public void ejbPassivate() {}
}
