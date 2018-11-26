package com.pm.utility.mail;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public interface Mailable 
{
	//For sending To mail
//	public boolean sendToMail(String from_parm, String to_parm,
//			String subject_parm, String message_parm);
//	
//	public boolean sendToMail(String from_parm, String[] to_parm,
//			String subject_parm, String message_parm);
//	
//	public void sendToMail(String from_parm, ArrayList to_parm,
//			String subject_parm, String message_parm);
//	
//	//For sending Cc mail
//	public void sendCcMail(String from_parm, ArrayList to_parm,
//			String subject_parm, String message_parm);
//	
//	public boolean sendCcMail(String from_parm, String to_parm,
//			String subject_parm, String message_parm);
//	
//	public boolean sendCcMail(String from_parm, String[] to_parm,
//			String subject_parm, String message_parm);
//	
//	//For sending Bcc mail 
//	public void sendBccMail(String from_parm, ArrayList to_parm,
//			String subject_parm, String message_parm);
//	
//	public boolean sendBccMail(String from_parm, String to_parm,
//			String subject_parm, String message_parm);
//	
//	public boolean sendBccMail(String from_parm, String[] to_parm,
//			String subject_parm, String message_parm);
//	
	//For text mail
	public boolean sendTextMail(String from_parm, String[] to_parm, String[] cc_parm, String[] bcc_parm,
			String subject_parm, String message_parm,String priority_parm);
	
	public boolean sendTextMail(String from_parm, String to_parm, String cc_parm, String bcc_parm,
			String subject_parm, String message_parm,String priority_parm);
	
	//For the timebeing it is kept abeyance
	
//	public boolean sendTextMail(String from_parm, List[] to_parm, List[] cc_parm, List[] bcc_parm,
//			String subject_parm, String message_parm,String priority_parm);
	
	//For attachment
	public boolean sendAttachmentMail(String from_parm, String[] to_parm,String[] cc_parm, String[] bcc_parm,
			String subject_parm, String message_parm, File attchmentFilepath_parm,String priority_parm );
	
	public boolean sendAttachmentMail(String from_parm, String to_parm,String cc_parm, String bcc_parm,
			String subject_parm, String message_parm, File attchmentFilepath_parm,String priority_parm );

	//For the timebeing it is kept abeyance
//	public boolean sendMailAsAttachment(String from_parm, List to_parm, List cc_parm, List bcc_parm,
//			String subject_parm, String message_parm, File attchmentFilepath_parm,String priority_parm );

}
