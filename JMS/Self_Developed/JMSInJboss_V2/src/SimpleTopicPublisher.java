import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import javax.jms.*;
import javax.naming.*;

public class SimpleTopicPublisher 
{

	public static Properties getJndiProperties()
	{
		Properties jndiProp = new Properties();
		try
		{
			InputStream in = new FileInputStream("config/jbossjndi.properties");
			jndiProp.load(in);
		}
		catch( FileNotFoundException fnfe )
		{
			fnfe.printStackTrace();
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return jndiProp;
	}
	
    public static void main(String[] args) 
    {
        String                  topicName = null;
        Context                 jndiContext = null;
        TopicConnectionFactory  topicConnectionFactory = null;
        TopicConnection         topicConnection = null;
        TopicSession            topicSession = null;
        Topic                   topic = null;
        TopicPublisher          topicPublisher = null;
        TextMessage             message = null;
        int               NUM_MSGS;
        
        topicName = "topic/testTopic";
        System.out.println("Topic name is " + topicName);
        NUM_MSGS = 10;
        
        try 
        {
            jndiContext = new InitialContext(getJndiProperties());
        }
        catch (NamingException e) 
        {
            System.out.println("Could not create JNDI API " +
                "context: " + e.toString());
            e.printStackTrace();
            System.exit(1);
        }
        
        /* 
         * Look up connection factory and topic.  If either does
         * not exist, exit.
         */
        try 
        {
            topicConnectionFactory = (TopicConnectionFactory)
                jndiContext.lookup("TopicConnectionFactory");
            topic = (Topic) jndiContext.lookup(topicName);
        } catch (NamingException e)
        {
            System.out.println("JNDI API lookup failed: " +
                e.toString());
            e.printStackTrace();
            System.exit(1);
        }

        /*
         * Create connection.
         * Create session from connection; false means session is
         * not transacted.
         * Create publisher and text message.
         * Send messages, varying text slightly.
         * Finally, close connection.
         */
        try 
        {
            topicConnection = 
                topicConnectionFactory.createTopicConnection();
            topicSession = 
                topicConnection.createTopicSession(false, 
                    Session.AUTO_ACKNOWLEDGE);
            topicPublisher = topicSession.createPublisher(topic);
            message = topicSession.createTextMessage();
            for (int i = 0; i < NUM_MSGS; i++) {
                message.setText("This is message " + (i + 1));
                System.out.println("Publishing message: " + 
                    message.getText());
                topicPublisher.publish(message);
            }
        } 
        catch (JMSException e) 
        {
            System.out.println("Exception occurred: " + 
                e.toString());
        }
        finally 
        {
            if (topicConnection != null) 
            {
                try 
                {
                    topicConnection.close();
                } catch (JMSException e)
                {
                	e.printStackTrace();
                }
            }
        }
    }
}
