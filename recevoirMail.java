package Email;

import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Message;

public class recevoirMail{	

		public static void main(String[] args) throws Exception {
	        Store store = null;
	        String subject = null;
	        Folder folder = null;
	        try 
	        {
	          Properties props = new Properties();
//	          props.setProperty("mail.store.protocol", "imaps");
//	          props.setProperty("mail.imap.port", "993");
	          Session session = Session.getDefaultInstance(props, null);

	          store = session.getStore("imaps");
	          store.connect("stmp.gmail.com","trial164error@gmail.com", "Polyt3ch!");

	          folder = store.getFolder("inbox");
	          folder.open(Folder.READ_WRITE);
//
//	          if(!folder.isOpen())
//	          folder.open(Folder.READ_WRITE);
//	          Message[] messages = folder.getMessages();
//	          System.out.println("No of Messages : " + folder.getMessageCount());
//	          System.out.println("No of Unread Messages : " + folder.getUnreadMessageCount());
//	          System.out.println(messages.length);
//	          for (int i=0; i < messages.length;i++) 
//	          {
//
//	            System.out.println("*****************************************************************************");
//	            System.out.println("MESSAGE " + (i + 1) + ":");
//	            Message msg =  messages[i];
//	            //System.out.println(msg.getMessageNumber());
//	            //Object String;
//	            //System.out.println(folder.getUID(msg)
//
//	            subject = msg.getSubject();
//
//	            System.out.println("Subject: " + subject);
//	            System.out.println("From: " + msg.getFrom()[0]);
//	           System.out.println("To: "+msg.getAllRecipients()[0]);
//	            System.out.println("Date: "+msg.getReceivedDate());
//	            System.out.println("Size: "+msg.getSize());
//	            System.out.println(msg.getFlags());
//	            System.out.println("Body: \n"+ msg.getContent());
//	            System.out.println(msg.getContentType());
//
//	          }
	        }
	        finally 
	        {
	          if (folder != null && folder.isOpen()) { folder.close(true); }
	          if (store != null) { store.close(); }
	        }

	    }

}