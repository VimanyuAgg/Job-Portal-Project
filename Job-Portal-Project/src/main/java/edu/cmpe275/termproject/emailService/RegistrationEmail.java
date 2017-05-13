package edu.cmpe275.termproject.emailService;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;

import edu.cmpe275.termproject.dao.JobSeekerDAO;


public class RegistrationEmail {
	


	public static void registrationEmailTrigger(String receiverMail, String authenticationCode){
		String emailID = "testmyouth@gmail.com";
		String emailPassword = "Testouth1@";		
		
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
            message.setFrom(new InternetAddress("testmyouth@gmail.com"));            
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiverMail));
            
            message.setSubject("Spring Onion almost there..");
            message.setText("Your Authentication Code is "+authenticationCode+"\n\nCheers,\n" + "Team Spring-Onions");
            Transport.send(message);
            System.out.println("Your message is sent successfully to "+receiverMail);
        } catch (MessagingException e) { e.printStackTrace();}
	}
}
