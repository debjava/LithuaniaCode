package com.pm.utility.mail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Flags.Flag;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ExternalMailer implements Mailable 
{
	
	private transient Properties mailProperties = new Properties();
	private transient InputStream fileInputStream = null;
	private Session mailSession = null;
	private String propFilePath = "E:/JavaJob/JavaMailAPI/config/mail.properties";//"D:/JavamailTesting/mail.properties";
	
	
	
	public ExternalMailer()
	{
		super();
		mailProperties = getMailProperties(propFilePath);
		
	}
	
	public ExternalMailer( final String propFilePath_parm )
	{
		super();
		mailProperties = getMailProperties(propFilePath_parm);
		//implement to get the contents of the properties file
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

	public boolean sendAttachmentMail(String from_parm, String[] to_parm,
			String[] cc_parm, String[] bcc_parm, String subject_parm,
			String message_parm, File attchmentFilepath_parm,
			String priority_parm) {
		boolean checkFlag = false;
		
		try
		{
			
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return checkFlag;
	}

	public boolean sendAttachmentMail(String from_parm, String to_parm,
			String cc_parm, String bcc_parm, String subject_parm,
			String message_parm, File attchmentFilepath_parm,
			String priority_parm) {
		boolean checkFlag = false;
		try
		{
			
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return checkFlag;
	}

	public boolean sendTextMail(String from_parm, String[] to_parm,
			String[] cc_parm, String[] bcc_parm, String subject_parm,
			String message_parm, String priority_parm) {
		boolean checkFlag = false;
		try
		{
			//send mail only to
			//send mail only cc 
			//send mail only bcc
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return checkFlag;
	}

	//Main method for implemeting mail facility in Processmate
	public boolean sendTextMail(String from_parm, String to_parm,
			String cc_parm, String bcc_parm, String subject_parm,
			String message_parm, String priority_parm) {
		boolean checkFlag = false;
		String[] toMailIds = {""};
		String[] ccMailIds = {""};
		if( cc_parm == null )
			cc_parm = "";
		if( bcc_parm == null )
			bcc_parm = "";
		System.out.println("cc_parm ::: "+cc_parm);
		try
		{
			if( to_parm == null )
				throw new NullPointerException("Destination mail id is blank,it should not be.");
			else
			{
				if( to_parm.indexOf(",") > 0 )
				{
					toMailIds = getMailIds(to_parm);
				}
				else
				{
					System.out.println("to_parm ::: "+to_parm);
					
				}
			}
			if( subject_parm == null )
				throw new NullPointerException("Subject is blank, it should not be.");
			if( message_parm == null || message_parm.equals( "" ) )
				throw new NullPointerException("Message is blank, it should not be.");
			if( priority_parm == null || priority_parm.equalsIgnoreCase( "Normal" ))
				priority_parm = "Normal";
			/*
			 * It is assumed that to text box can not be blank.
			 * Cc and Bcc may be blank
			 */
			if( to_parm != null && subject_parm != null && message_parm != null )
			{
				//send mail only to
				sendMailAsText(from_parm, toMailIds , subject_parm, message_parm, priority_parm);
				checkFlag = true;
				if( cc_parm != null || !( cc_parm.equals("") ) )
				{
					//send mail only Cc
					checkFlag = true;
				}
				if( bcc_parm != null || !( bcc_parm.equals("") ) )
				{
					//send mail only Bcc
					checkFlag = true;
				}
				
			}
			
		}
		catch( NullPointerException npe )
		{
			npe.printStackTrace();
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return checkFlag;
	}
	
	/**
	 * This method is used to send mail to one person or a number of persons.
	 * This is the actual implementation of the mailing service.
	 * @param from_parm
	 * @param to_parm
	 * @param subject_parm
	 * @param message_parm
	 * @param priority_parm
	 * @return
	 */
	private boolean sendMailAsText(String from_parm, String[] to_parm,
			String subject_parm, String message_parm, String priority_parm) {
		boolean mailFlag = false;
		try
		{
			mailSession = getSession();
			Message mailMessage = new MimeMessage(mailSession);
			InternetAddress fromInetAdrs = new InternetAddress(from_parm);
			mailMessage.setFrom(new InternetAddress(from_parm));
			for( int i = 0 ; i < to_parm.length ; i++ )
			{
				
				//write code to send mail TO
				InternetAddress[] address = { new InternetAddress(to_parm[i]) };
				mailMessage.setRecipients(Message.RecipientType.TO, address);
				
			}
			mailMessage.setSubject(subject_parm);
			mailMessage.setSentDate(new Date());
			mailMessage.setText(message_parm);
			//Set the message flag here ie Normal,High,Low etc
			Transport bus = mailSession.getTransport("smtp");
			bus.send(mailMessage);
		}
		catch (MessagingException mex) 
		{
			System.out.println("Exception relating to Messaging");
			// Prints all nested (chained) exceptions as well
			mex.printStackTrace();
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return mailFlag;
	}
	/*/
	 * This is the ultimate and last way to solve and to send the
	 * single mail id
	 */
	private boolean sendTextMail()
	{
		boolean checkFlag = false;
		
		return checkFlag;
	}
	
	private String[] getMailIds( final String to_parm )
	{
		String[] toMailingList = null;
		try
		{
			String[] tempArray = to_parm.split(",");
			toMailingList = getOnlyMailIds(tempArray);
		}
		catch( NullPointerException npe )
		{
			throw npe;
		}
		return toMailingList;
	}
	
	private String[] getOnlyMailIds( String[] mailIds_parm )
	{
		String[] tempString = new String[ mailIds_parm.length ];
		System.out.println("Mail Id length ::: "+mailIds_parm.length);
		for( int i = 0 ; i < mailIds_parm.length ; i++ )
		{
			System.out.println("Email Ids ::: "+mailIds_parm[i]);
//			list.add( mailIds_parm[i].toLowerCase().trim() );
//			tempString = (String[])list.toArray();
			tempString[i] = mailIds_parm[i].toLowerCase().trim();
		}
		return tempString;
	}
	

	public static void main(String[] args) 
	{
		
		ExternalMailer mailer = new ExternalMailer();
		String toMail = "dd@rediffmail.com";
		String fromMail = "<recipientundisclosed>";
		String ccMail = null;
		String bccMail = null;
		String subject = "Pay Money";
		String message = new StringBuffer().append("Hi, How are u ?").append("Ignore this mail").toString();
		String prioprity = "normal";
		boolean check = mailer.sendTextMail(fromMail, toMail, ccMail, bccMail, subject, message, prioprity);
		System.out.println(" check ::: "+check);
	}
}
