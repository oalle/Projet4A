package Email;

import javax.mail.Folder;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMessage.RecipientType;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.mail.*;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;

public class recevoirMail{
	public void recevoirMail(){
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.starttls.enable", "true");
	    properties.setProperty("mail.store.protocol", "pop3");
	    properties.setProperty("mail.pop3.host", "pop.gmail.com");
	    properties.setProperty("mail.pop3.user", "bessechantrel@gmail.com");
	    properties.setProperty("mail.pop3.port", "995");
	   // properties.setProperty("mail.pop3.timeout", "6000");
	    //properties.setProperty("mail.pop3.connectiontimeout", "6000");
	    Session session = Session.getInstance(properties);
	    Store store = null;
	    Folder defaultFolder = null;
	    Folder inbox = null;
	   
	    try {
	        store = session.getStore("pop3");
	        System.out.println("lo");
	        store.connect("pop.gmail.com","bessechantrel@gmail.com", "projet4a");
	        System.out.println("connecté");
	        defaultFolder = store.getDefaultFolder();
	        
	        System.out.println("defaultFolder : " + defaultFolder.getName());

	        for (Folder folder : defaultFolder.list()) {
	            System.out.println(folder.getName());
	        }
	        inbox = defaultFolder.getFolder("INBOX");
	        printMessages(inbox);
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally { // Ne pas oublier de fermer tout ça&#160;!
	        close(inbox);
	        close(defaultFolder);
	        try {
	            if (store != null && store.isConnected()) {
	                store.close();
	            }
	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }
	    }
	}
	private static void printMessages(Folder folder) {
	    try {
	        folder.open(Folder.READ_ONLY);
	        int count = folder.getMessageCount();
	        int unread = folder.getUnreadMessageCount();
	        System.out.println("Il y a " + count + " messages, dont " + unread + " non lus.");
	        for (int i = 1; i <= count; i++ ) {

	            Message message = folder.getMessage(i);
	            System.out.println("Message n° " + i);
	            System.out.println("Sujet : " + message.getSubject());

	            System.out.println("Expéditeur : ");
	            Address[] addresses = message.getFrom();
	            for (Address address : addresses) {
	                System.out.println("\t" + address);
	            }

	            System.out.println("Destinataires : ");
	            addresses = message.getRecipients(RecipientType.TO);
	            if (addresses != null) {
	                for (Address address : addresses) {
	                    System.out.println("\tTo : " + address);
	                }
	            }
	            addresses = message.getRecipients(RecipientType.CC);
	            if (addresses != null) {
	                for (Address address : addresses) {
	                    System.out.println("\tCopie : " + address);
	                }
	            }
	            
	            System.out.println("Content : ");
	            for (String line : InputStreamToStrings(message.getInputStream())) {
	                System.out.println(line);
	            }
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	private static String InputStreamToStrings(InputStream inputStream) throws IOException 
	{

		
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {	
			return bufferedReader.lines().collect(Collectors.joining((System.lineSeparator())));
		}
		
	 
		
	}
	private static void close(Folder folder) {
	    if (folder != null && folder.isOpen()) {
	        try {
	            folder.close(false); // false -> On n'efface pas les messages marqués DELETED
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}

	private class Auth extends Authenticator{
		protected PasswordAuthentication getPasswordAuthentication()
		{

			return new PasswordAuthentication( "nathanvictorprojet@gmail.com", "projet4a");

		}
	}
	public static void main(String args[])
	{
		recevoirMail test1= new recevoirMail ();
		test1.recevoirMail();

	}//fin du main

}