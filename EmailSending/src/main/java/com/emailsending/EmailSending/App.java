package com.emailsending.EmailSending;


import java.io.File;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Preparing to send email..........." );
        
        String message = "Hello, Dear this is message for security check";
        String subject = "Testing : Conformation";
        String to = "nayakankit128@gmail.com";
        String from = "sharmanama15@gmail.com";
        
       // sendEmail(message,subject,to,from);  
        sendAttach(message,subject,to,from);
    }

    private static void sendAttach(String message, String subject, String to, String from) {
		
    	//Variable for email
    			String host = "smtp.gmail.com";
    			
    			//access system properties
    			Properties properties = System.getProperties();
    			System.out.println("System Properties.."+properties);
    			
    			
    			//important information for properties object
    			
    			//host set
    			properties.put("mail.smtp.host", host);
    			properties.put("mail.smtp.port", "465");
    			properties.put("mail.smtp.ssl.enable", "true");
    			properties.put("mail.smtp.auth", "true");

    			//get the session object
    			Session session = Session.getInstance(properties, new Authenticator() {
    				
    				protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
    					return new javax.mail.PasswordAuthentication("sharmanama15@gmail.com", "7247348316");
    				}			
    			});
    			
    			session.setDebug(true);
    			
    			//compose the message
    			MimeMessage m = new MimeMessage(session);
    			
    			try {
    				//from email
    				m.setFrom(from);
    				
    				//adding recipient to message
    				m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
    				
    				//adding subject to message
    				m.setSubject(subject);
    				
    				//adding text to message
    				m.setText(message);
    				
    				//file path
    				String path = "C:\\Users\\Hp\\Pictures\\High_resolution_wallpaper_background_ID_77700335097";
    				
    				MimeMultipart mimemultipart = new MimeMultipart();
    				
    				MimeBodyPart textmine = new MimeBodyPart();
    				
    				MimeBodyPart filemine = new MimeBodyPart();
    				
    				try {
    					
    					textmine.setText(message);
    					
    					File file = new File(path);
    					filemine.attachFile(file);
    					
    					mimemultipart.addBodyPart(textmine);
    					mimemultipart.addBodyPart(filemine);
    					
    				}catch(Exception e) {
    					e.printStackTrace();
    				}
    				
    				m.setContent(mimemultipart);
    				
    				//sent the message using transport class
    				Transport.send(m);
    				
    				System.out.println("Send Successfully..................");
    				
    				
    			}catch(Exception e) {
    				e.printStackTrace();
    			}
		
	}
}
