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
import javax.jms.*;
import javax.naming.*;
import java.util.*;

/**
 * The HumanResourceClient class is the client program for this 
 * J2EE application. It publishes a message describing a new
 * hire business event that other departments can act upon. It
 * also listens for a message reporting the completion of the 
 * other departments' actions and displays the results.
 */
public class HumanResourceClient {
    static Object     waitUntilDone = new Object();
    static SortedSet  outstandingRequests = 
        Collections.synchronizedSortedSet(new TreeSet());
    
    public static void main (String[] args) {
        InitialContext          ic = null;
        TopicConnectionFactory  topicConnectionFactory = null;
        TopicConnection         tConnection = null;
        TopicSession            tSession = null;
        Topic                   pubTopic = null;
        TopicPublisher          tPublisher = null;
        MapMessage              message = null;
        QueueConnectionFactory  queueConnectionFactory = null;
        QueueConnection         qConnection = null;
        QueueSession            qSession = null;
        Queue                   replyQueue = null;
        QueueReceiver           qReceiver = null;

        /* 
         * Create a JNDI API InitialContext object.
         */
        try {
            ic = new InitialContext();
        } catch (NamingException e) {
            System.err.println("HumanResourceClient: " +
                "Could not create JNDI API context: " +
                e.toString());
            System.exit(1);
        }

        /* 
         * Look up connection factories and topic.  If any do not
         * exist, exit.
         */
        try {
            topicConnectionFactory = (TopicConnectionFactory)
           ic.lookup("java:comp/env/jms/TopicConnectionFactory");
            pubTopic = 
             (Topic) ic.lookup("java:comp/env/jms/NewHireTopic");
            queueConnectionFactory = (QueueConnectionFactory) 
           ic.lookup("java:comp/env/jms/QueueConnectionFactory");
        } catch (NamingException e) {
              System.err.println("HumanResourceClient: " +
                  "JNDI API lookup failed: " + e.toString());
            System.exit(1);
        }

        /*
         * Create topic and queue connections.
         * Create sessions from connections for the publisher
         *   and receiver; false means session is not
         *   transacted.
         * Create temporary queue and receiver, set message 
         *   listener, and start connection.
         * Create publisher and MapMessage.
         * Publish new hire business events.
         * Wait for all messages to be processed.
         * Finally, close connection.
         */
        try {
            Random rand = new Random();
            int nextHireID = rand.nextInt(100);

            String[] positions = { "Programmer", 
                "Senior Programmer", "Manager", "Director" };
            String[] firstNames = { "Fred", "Robert", "Tom", 
                "Steve", "Alfred", "Joe", "Jack", "Harry",
                "Bill", "Gertrude", "Jenny", "Polly", "Ethel", 
                "Mary", "Betsy", "Carol", "Edna", "Gwen" };
            String[] lastNames = { "Astaire", "Preston", "Tudor",
                "Stuart", "Drake", "Jones", "Windsor", 
                "Hapsburg", "Robinson", "Lawrence", "Wren", 
                "Parrott", "Waters", "Martin", "Blair",
                "Bourbon", "Merman", "Verdon" };

            tConnection = 
                topicConnectionFactory.createTopicConnection();
            tSession = tConnection.createTopicSession(false, 
                Session.AUTO_ACKNOWLEDGE);

            qConnection =
                queueConnectionFactory.createQueueConnection();
            qSession = qConnection.createQueueSession(false, 
                Session.AUTO_ACKNOWLEDGE);
            replyQueue = qSession.createTemporaryQueue();
            qReceiver = qSession.createReceiver(replyQueue);
            qReceiver.setMessageListener(new HRListener());
            qConnection.start();

            tPublisher = tSession.createPublisher(pubTopic);

            message = tSession.createMapMessage();
            message.setJMSReplyTo(replyQueue);
            for (int i = 0; i < 5; i++) {
                int currentHireID = nextHireID++;
                message.setString("HireID", 
                    String.valueOf(currentHireID));
                message.setString("Name", 
                    firstNames[rand.nextInt(firstNames.length)]
                    + " " +
                    lastNames[rand.nextInt(lastNames.length)]);
                message.setString("Position",
                    positions[rand.nextInt(positions.length)]);
                System.out.println("PUBLISHER: Setting hire " +
                    "ID to " + message.getString("HireID") + 
                    ", name " + message.getString("Name") +
                    ", position " +
                    message.getString("Position"));
                tPublisher.publish(message);
             outstandingRequests.add(new Integer(currentHireID));
            }

            System.out.println("Waiting for " + 
                outstandingRequests.size() + " message(s)");
            synchronized (waitUntilDone) {
                waitUntilDone.wait();
            }
        } catch (Exception e) {
            System.err.println("HumanResourceClient: " +
                "Exception: " + e.toString());
        } finally {
            if (tConnection != null) {
                try {
                    tConnection.close();
                } catch (Exception e) {
                    System.err.println("HumanResourceClient: " +
                        "Close exception: " + e.toString());
                }
            }
            if (qConnection != null) {
                try {
                    qConnection.close();
                } catch (Exception e) {
                    System.err.println("HumanResourceClient: " +
                        "Close exception: " + e.toString());
                }
            }
            System.exit(0);
        }
    }

    /**
     * The HRListener class implements the MessageListener
     * interface by defining an onMessage method.
     */
    static class HRListener implements MessageListener {

        /**
         * onMessage method, which displays the contents of a 
         * MapMessage describing the results of processing the
         * new employee, then removes the employee ID from the
         * list of outstanding requests.
         *
         * @param message    the incoming message
         */
        public void onMessage(Message message) {
            MapMessage msg = (MapMessage) message;
            try {
                System.out.println("New hire event processed:");
                Integer id = 
                    Integer.valueOf(msg.getString("employeeId"));
                System.out.println("  Employee ID: " + id);
                System.out.println("  Name: " + 
                    msg.getString("employeeName"));
                System.out.println("  Equipment: " + 
                    msg.getString("equipmentList"));
                System.out.println("  Office number: " +
                    msg.getString("officeNumber"));
                outstandingRequests.remove(id);
            } catch (JMSException je) {
                System.out.println("HRListener.onMessage(): " + 
                    "Exception: " + je.toString());
            }

            if (outstandingRequests.size() == 0) {
                synchronized(waitUntilDone) {
                    waitUntilDone.notify();
                }
            } else {
                System.out.println("Waiting for " + 
                    outstandingRequests.size() + " message(s)");
            }
        }
    }
}
