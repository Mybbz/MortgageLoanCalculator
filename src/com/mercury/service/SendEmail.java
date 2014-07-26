package com.mercury.service;

import java.util.Properties;
import java.util.Date;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;                  // Include Authenticator, Message, PasswordAuthentication, Session, Transport
import javax.mail.internet.*;     // Include InternetAddress, MimeMessage
public class SendEmail {
	
	
	public void sendEmail(String email){
		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";  
        Properties prop = System.getProperties();
        prop.setProperty("mail.smtp.host", "smtp.gmail.com");   
        prop.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);   
        prop.setProperty("mail.smtp.socketFactory.fallback", "false");   
        prop.setProperty("mail.smtp.port", "465");   
        prop.setProperty("mail.smtp.socketFactory.port", "465");   
        prop.put("mail.smtp.auth", "true");   
        final String user = "byfuchao1989@gmail.com";
        final String password = "kenan1945";
        Session session = Session.getDefaultInstance(prop, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() { 
		return new PasswordAuthentication(user, password); 		} 
            });
        try {		
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("cf44@njit.edu"));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            msg.setSubject("Welcome to My online banking"); msg.setSentDate(new Date());
            msg.setText("Thank you for your support");
            Transport.send(msg);
            System.out.println("Message sent successfully!");
        } catch (Exception e) { System.out.println(e); }
		
		
		
		
	}
	

}
