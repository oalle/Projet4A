
package email;

import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Session;
import javax.mail.Store;
import com.sun.mail.util.MailSSLSocketFactory;
import java.io.FileOutputStream;
import com.voicerss.tts.AudioCodec;
import com.voicerss.tts.AudioFormat;
import com.voicerss.tts.Languages;
import com.voicerss.tts.VoiceParameters;
import com.voicerss.tts.VoiceProvider;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.mail.Message;
 
public class Email {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         Store store = null;
	        String subject = null;
	        Folder folder = null;
	        try 
	        {
                    Properties props = new Properties();
                    MailSSLSocketFactory sf = new MailSSLSocketFactory();
                    sf.setTrustAllHosts(true); 
                    props.put("mail.smtp.ssl.trust", "*");
                    props.put("mail.smtp.ssl.socketFactory", sf);
  	          props.put("mail.smtp.host", "*");
  	          props.put("mail.smtp.port", "465");
                    Session session = Session.getDefaultInstance(props);

                    store = session.getStore("imaps");
                    store.connect("smtp.gmail.com","trial164error@gmail.com", "Polyt3ch!");

                    folder = store.getFolder("inbox");
                    folder.open(Folder.READ_WRITE);

                    Message[] messages = folder.getMessages();
                    System.out.println("No of Messages : " + folder.getMessageCount());
                    System.out.println("No of Unread Messages : " + folder.getUnreadMessageCount());
                    System.out.println(messages.length);
                    VoiceProvider tts = new VoiceProvider("395261e345dd4051a96a98f0824df473");

                    
	          for (int i=0; i < messages.length;i++) 
	          {

	            System.out.println("*****************************************************************************");
	            System.out.println("MESSAGE " + (i + 1) + ":");
	            Message msg =  messages[i];
                    subject = msg.getSubject();

	            System.out.println("Subject: " + subject);
                    System.out.println("From: " + msg.getFrom()[0]);
                    System.out.println("To: "+msg.getAllRecipients()[0]);
	            System.out.println("Date: "+msg.getReceivedDate());
	            System.out.println(msg.getFlags());
	            System.out.println("Body: \n"+ msg.getContent());
	            System.out.println(msg.getContentType());
                    VoiceParameters params = new VoiceParameters("MESSAGE " + (i + 1) + ":"+"Sujet: " + subject+"De: " + msg.getFrom()[0]+"A: "+msg.getAllRecipients()[0]+"Le: "+msg.getReceivedDate()+ msg.getContent(), Languages.French_France);
                    params.setCodec(AudioCodec.MP3);
                    params.setFormat(AudioFormat.Format_44KHZ.AF_44khz_16bit_stereo);
                    params.setBase64(false);
                    params.setSSML(false);
                    params.setRate(0);
                    byte[] voice = tts.speech(params);

                    FileOutputStream fos = new FileOutputStream("voice.mp3");
                    String soundFile = (System.getProperty("user.dir") + "\\voice.mp3");
                    InputStream in = new FileInputStream(soundFile);
                    fos.write(voice, 0, voice.length);
                    System.out.println(voice.length);
                    Sound s=new Sound(System.getProperty("user.dir") + "\\voice.mp3");
                    s.play();
                    
                    s.close();
                    fos.flush();
                    fos.close();
                   }
                }
                catch(Exception e)
                {
                    System.out.println("Error1" + e);
	        }
	        finally 
	        {
                  try{
	          if (folder != null && folder.isOpen()) { folder.close(true); }
	          if (store != null) { store.close(); }
                  
                  }
                  catch(Exception e)
                  {
                      System.out.println("Error");
                  }
	        }

	    }
}
