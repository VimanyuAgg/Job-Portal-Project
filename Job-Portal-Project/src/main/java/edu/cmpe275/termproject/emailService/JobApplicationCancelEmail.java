package edu.cmpe275.termproject.emailService;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JobApplicationCancelEmail {

	public static void jobCancelEmail(String receiverMail, String firstName, String lastName,
			  String jobId, String jobTitle, String  companyName){
//String emailID = "testmyouth@gmail.com";

//
		String emailID = "springonionscmpe275@gmail.com";
		String emailPassword = ###CONTACT COLLABORATORS ###;

		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		Session session = Session.getInstance(properties,
				new javax.mail.Authenticator() {  
			protected PasswordAuthentication getPasswordAuthentication() {  
				return new PasswordAuthentication(emailID,emailPassword);  
			}  
		});

		try {
			Message message = new MimeMessage(session);
			//message.setFrom(new InternetAddress("testmyouth@gmail.com"));
			message.setFrom(new InternetAddress("springonionscmpe275@gmail.com"));            
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiverMail));

			message.setSubject(jobId+" Position "+jobTitle+" :"+companyName+" no longer available");
			message.setText("\nDear "+firstName+" "+lastName+","
					+ "\n\n This is to inform you that job posting "+jobId+": "+jobTitle+" at "+companyName+" has been removed"
					+"\nYou are receiving this email as you had applied to this job posting!"
					+ "\nThanks for using Spring Onions !"
					+ "\n\nCheers,\n" + "Team Spring-Onions"
					+"\nSpreading tears of happiness!");
			Transport.send(message);
			System.out.println("Your message is sent successfully to "+receiverMail);
		} catch (MessagingException e) { e.printStackTrace();}
}
	
	public static void jobFilledEmail(String receiverMail, String firstName, String lastName,
			  String jobId, String jobTitle, String  companyName){
//String emailID = "testmyouth@gmail.com";
	
//
		String emailID = "springonionscmpe275@gmail.com";
		String emailPassword = ###CONTACT COLLABORATORS ###;

		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		Session session = Session.getInstance(properties,
				new javax.mail.Authenticator() {  
			protected PasswordAuthentication getPasswordAuthentication() {  
				return new PasswordAuthentication(emailID,emailPassword);  
			}  
		});

		try {
			Message message = new MimeMessage(session);
			//message.setFrom(new InternetAddress("testmyouth@gmail.com"));
			message.setFrom(new InternetAddress("springonionscmpe275@gmail.com"));            
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiverMail));

			message.setSubject(jobId+" Position "+jobTitle+" :"+companyName+" no longer available");
			message.setText("\nDear "+firstName+" "+lastName+","
					+ "\n\n This is to inform you that job posting "+jobId+": "+jobTitle+" at "+companyName+" has been filled "
					+"as company decided to move on with other candidate."
					+"\n\nYou are receiving this email as you had applied to this job posting!"
					+ "\nThanks for using Spring Onions !"
					+ "\n\nCheers,\n" + "Team Spring-Onions"
					+"\nSpreading tears of happiness!");
			Transport.send(message);
			System.out.println("Your message is sent successfully to "+receiverMail);
		} catch (MessagingException e) { e.printStackTrace();}
}
}
