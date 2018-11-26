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
import javax.ejb.*;
import javax.naming.*;
import javax.jms.*;

/**
 * The MessageBean class is a message-driven bean.  It implements
 * the javax.ejb.MessageDrivenBean and javax.jms.MessageListener 
 * interfaces. It is defined as public (but not final or 
 * abstract).  It defines a constructor and the methods
 * setMessageDrivenContext, ejbCreate, onMessage, and 
 * ejbRemove.
 */
public class MessageBean implements MessageDrivenBean, 
        MessageListener {

    private transient MessageDrivenContext mdc = null;
    private Context context;
    
    /**
     * Constructor, which is public and takes no arguments.
     */
    public MessageBean() {
        System.out.println("In MessageBean.MessageBean()");
    }

    /**
     * setMessageDrivenContext method, declared as public (but 
     * not final or static), with a return type of void, and 
     * with one argument of type javax.ejb.MessageDrivenContext.
     *
     * @param mdc    the context to set
     */
    public void setMessageDrivenContext(MessageDrivenContext mdc)
    {
        System.out.println("In " +
            "MessageBean.setMessageDrivenContext()");
        this.mdc = mdc;
    }

    /**
     * ejbCreate method, declared as public (but not final or 
     * static), with a return type of void, and with no 
     * arguments.
     */
    public void ejbCreate() {
        System.out.println("In MessageBean.ejbCreate()");
    }

    /**
     * onMessage method, declared as public (but not final or 
     * static), with a return type of void, and with one argument
     * of type javax.jms.Message.
     *
     * Casts the incoming Message to a TextMessage and displays 
     * the text.
     *
     * @param inMessage    the incoming message
     */
    public void onMessage(Message inMessage) {
        TextMessage msg = null;

        try {
            if (inMessage instanceof TextMessage) {
                msg = (TextMessage) inMessage;
                System.out.println("MESSAGE BEAN: Message " +
                    "received: " + msg.getText());
            } else {
                System.out.println("Message of wrong type: " +
                    inMessage.getClass().getName());
            }
        } catch (JMSException e) {
            System.err.println("MessageBean.onMessage: " +
                "JMSException: " + e.toString());
            mdc.setRollbackOnly();
        } catch (Throwable te) {
            System.err.println("MessageBean.onMessage: " +
                "Exception: " + te.toString());
        }
    }
    
    /**
     * ejbRemove method, declared as public (but not final or 
     * static), with a return type of void, and with no 
     * arguments.
     */
    public void ejbRemove() {
        System.out.println("In MessageBean.remove()");
    }
}
