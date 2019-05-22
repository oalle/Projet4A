package email;

import com.sun.mail.util.MailSSLSocketFactory;
import com.voicerss.tts.AudioCodec;
import com.voicerss.tts.AudioFormat;
import com.voicerss.tts.Languages;
import com.voicerss.tts.VoiceParameters;
import com.voicerss.tts.VoiceProvider;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;

import javax.swing.JButton;
import javax.swing.JPanel;
/**
 * <b>MonPanneau nous permet d'afficher les mail sur notre Fenêtre ainsi que nos boutons.</b>
 * Un Panneau est caractérisé par les informations suivantes :
 * <ul>
 * <li>La position x du premier caractère.</li>
 * <li>La position y du premier caractère.</li>
 * <li>La taille des caractères.</li>
 * <li>Le numéro du mail afficher.</li>
 * <li>Le nombre de mail.</li>
 * <li>Le tableau de tous nos mail.</li>
 * <li>L'identifiant pour le VoiceRSS qui nous sert pour la lecture à la voix.</li>
 * </ul>
 * @see MaFenetre
 * @see Sound
 * @author nathan besse, victor chantrel
 * @version 1.0
 */
public class MonPanneau extends JPanel {
	static final long serialVersionUID = 1L;
	
	private float x ;	// position en x du premier caract�re
	private float y ;	// "         " y "                  " 
	private int size;	// Taille des chaines de caract�res
        protected int i=0;
        protected int imax;
	private Message[]messages;
        VoiceProvider tts = new VoiceProvider("395261e345dd4051a96a98f0824df473");
        /**
         * Crée un nouveau panneau avec nos différents boutons et récupère tous nos mails
         * tout d'abord en initialisant la connexion puis on récupère nos mail dans une boite mail spécifié
         */
	public MonPanneau() {
		JButton suivant = new JButton("Suivant");
                JButton son = new JButton("Son");
		JButton precedent = new JButton("Precedent");
                
		Bouton suiv = new Bouton(this, "suivant");
                Bouton so = new Bouton(this, "son");
		Bouton prec = new Bouton(this, "precedent");
		suivant.addActionListener(suiv);
                son.addActionListener(so);
		precedent.addActionListener(prec);
		add(precedent);
                add(son);
		add(suivant);
		Properties props = new Properties();
                Store store = null;
	        
	        Folder folder = null;
                try{
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

                    messages = folder.getMessages();
                    System.out.println("No of Messages : " + folder.getMessageCount());
                    System.out.println("No of Unread Messages : " + folder.getUnreadMessageCount());
                    imax=messages.length-1;
                    
                }
                catch(Exception e)
                {
                    
                }
		
	}
        /**
         * Récupère le message n°i, le met sous forme audio dans un format mp3 puis renvoie le message
         * @return msg message n°i
         */
        public Message email()
        {
            String subject = null;
            Message msg=null;
                    try{
	            msg =  messages[i];
                    subject = msg.getSubject();
                    VoiceParameters params = new VoiceParameters("MESSAGE " + (i + 1)+"\n" +"Sujet " +"\n" + subject+"\n" +"De " +"\n" + msg.getFrom()[0]+"\n" +"A "+"\n" +msg.getAllRecipients()[0]+"\n" +"Le "+"\n" +msg.getReceivedDate()+"\n" + msg.getContent(), Languages.French_France);
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
                    
                    fos.flush();
                    fos.close();
                    
                    }
                    catch(Exception e)
                    {
                        
                    }  
            return msg;
        }
	/**
         * Lance la lecture du fichier mp3 créer dans la fonction email
         */
        public void ecoute()
        {
            try{
            Sound s=new Sound(System.getProperty("user.dir") + "\\voice.mp3");
                    s.play();
                    
                    s.close();
            }
            catch(Exception e)
            {
                
            }
        }
        /**
         * Affiche notre interface avec le contenu du mail n°i ainsi que nos boutons.
         * @param g Les graphiques utilisé
         */
	public void paintComponent(Graphics g) {
                 
	        try 
	        {
                    x=20;
                    y=60;

                Message msg=email();
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		size = 15;
		Font font = new Font("Helvetica", Font.BOLD, size);
		Font font2 = new Font("Helvetica", Font.CENTER_BASELINE, 13);
		FontMetrics fm = g2.getFontMetrics();
		g2.setFont(font);
		
		g2.drawString("Sujet : ", x, y);
		int ecart = fm.stringWidth("Sujet : ");
		int haut = fm.getHeight();
		x = x + (int)(ecart*1.5);
		g2.drawString(msg.getSubject(), x, y);
		x = 20;
		y  = y + 2*haut;
		
		g2.drawString("Expéditeur : ", x, y);
		ecart = fm.stringWidth("Expéditeur : ");
		x = x + (int)(ecart*1.5);
                
                
		g2.drawString(msg.getFrom()[0].toString(), x, y);
		x = 20;
		y  = y + 2*haut;
		
		g2.drawString("Contenu : ", x, y);
		ecart = fm.stringWidth("Contenu : ");
		x = x + (int)(ecart*1.5);
		g2.setFont(font2);
                
                final String SEPARATEUR = "\n";
                final String SEPARATEUR2 = "<br/>";
                String conte = msg.getContent().toString();
 
                String mots[] = conte.split(SEPARATEUR);
                for (int j = 0; j < mots.length; j++) 
                {
                    String ligne[] = mots[j].split(SEPARATEUR2);
                    for(int k=0;k<ligne.length;k++)
                    {
                        if(x+fm.stringWidth(ligne[k])<1200)
                        {
                            
                            g2.drawString(ligne[k], x, y);
                            
                            x=20;
                            y  = y + 2*haut;
                        }
                        else
                        {
                            
                            g2.drawString(ligne[k], x, y);
                            
                            x=20;
                            y  = y + 2*haut;
                        }
                    }
                    
                }
                
                
                }
                catch(Exception e)
                {
                    System.out.println("Error1" + e);
	        }
	        finally 
	        {
                  try{
	          
                  
                  }
                  catch(Exception e)
                  {
                      System.out.println("Error");
                  }
	        }
	}
}
