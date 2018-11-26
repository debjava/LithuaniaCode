package org.deba.utility.mail;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author debadatta.m
 *
 */
public class TestJavaMail {
	private transient Properties mailProperties = new Properties();

	private transient InputStream fileInputStream = null;

	private Session mailSession = null;

	private String propFilePath = "D:/JavamailTesting/mail.properties";

	public TestJavaMail() {
		mailProperties = getMailProperties(propFilePath);
	}

	public TestJavaMail(final String propFilePath_parm) {
		mailProperties = getMailProperties(propFilePath_parm);
	}

	private Properties getMailProperties(final String filePath_parm) {
		Properties mailProp = new Properties();
		try {
			fileInputStream = new FileInputStream(filePath_parm);
			mailProp.load(fileInputStream);
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mailProp;
	}

	private Session getSession() {
		Session session = Session.getInstance(mailProperties);
		return session;
	}

	public void sendMail(String from_parm, String[] to_parm,
			String subject_parm, String message_parm) {
		try {
			mailSession = getSession();
			Message msg = new MimeMessage(mailSession);
			InternetAddress fromInetAdrs = new InternetAddress(from_parm);
			msg.setFrom(new InternetAddress(from_parm));
			for (int i = 0; i < to_parm.length; i++) {
				InternetAddress[] address = { new InternetAddress(to_parm[i]) };
				msg.setRecipients(Message.RecipientType.TO, address);
			}
			msg.setSubject(subject_parm);
			msg.setSentDate(new Date());
			msg.setText(message_parm);
			Transport bus = mailSession.getTransport("smtp");
			bus.send(msg);
		}

		catch (MessagingException mex) {
			System.out.println("Exception relating to Messaging");
			// Prints all nested (chained) exceptions as well
			mex.printStackTrace();
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void sendMail(final String sourceEmailId, final String destnEmailId) {
		try {
			mailSession = getSession();
			Message msg = new MimeMessage(mailSession);
			InternetAddress fromInetAdrs = new InternetAddress(sourceEmailId);
			msg.setFrom(new InternetAddress(sourceEmailId));
			InternetAddress[] address = { new InternetAddress(destnEmailId) };

			System.out.println("************** address ::: " + address);

			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject("Test E-Mail from IdealInvent using Java mail API");
			msg.setSentDate(new Date());
			msg.setText("HAPPY NEW YEAR 2007 "
					+ "MESSAGE FROM IDEAL INVENT TECHNOLOGY\n"
					+ "USING JAVA MAIL.");
			Transport bus = mailSession.getTransport("smtp");
			
			bus.send(msg);
		}

		catch (MessagingException mex) {
			System.out.println("Exception relating to Messaging");
			// Prints all nested (chained) exceptions as well
			mex.printStackTrace();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) 
	{
		 

		TestJavaMail testMail = new TestJavaMail();
		final String sourceEmailId = "balaji.r@idealinvent.com";
//		final String destnEmailId = "aashwina.m@idealinvent.com";
		final String[] destnEmailId = {"mishra.piku@gmail.com",
				"kanumuri_koti@yahoo.com","debadattamishra@aol.com","kalyanimudundi@yahoo.co.in"};
		final String subject = " Test mail from Java";
		final String messages = " Hi All, How are you ?, do not reply"; 
//		testMail.sendMail(sourceEmailId, destnEmailId);
		testMail.sendMail(sourceEmailId, destnEmailId , subject, messages);
	}

}
