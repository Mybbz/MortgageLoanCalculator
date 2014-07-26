package com.mercury.service;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Test {
	public static void main(String[] args){
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
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress("cf44@njit.edu"));
            msg.setSubject("Welcome to My online banking"); msg.setSentDate(new Date());
            msg.setText("Thank you for your support");
            Transport.send(msg);
            System.out.println("Message sent successfully!");
        } catch (Exception e) { System.out.println(e); }
	}

}
