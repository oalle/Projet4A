package email;
import javazoom.jl.player.advanced.*;
        import java.io.*;
import javazoom.jl.player.Player;
/**
 * <b>Sound gère tous ce qui est rapport avec la lecture du fichier mp3.</b>
 * Sound est caractérisé par les informations suivantes :
 * <ul>
 * <li>Un player pour lire notre fichier audio.</li>
 * </ul>

 * @author nathan besse, victor chantrel
 * @version 1.0
 */
  public class Sound {
                private Player player;
                /**
                 * Créer notre player avec le fichier son préciser dans path
                 * @param path le chemin pour accéder au fichier son
                 * @throws Exception 
                 */
                public Sound(String path) throws Exception 
                {
                        BufferedInputStream in = new BufferedInputStream(new FileInputStream(new File(path)));
                        player = new Player(in);
                }
                
               
                /**
                 * Lance la lecture de notre fichier son avecc le player
                 * @throws Exception 
                 */
                public void play() throws Exception 
                {
                        if (player != null) {
                                
                                isPlaying = true;
                                player.play();
                        }
                }
                /**
                 * Ferme le Player
                 */
                public void close() 
                { 
                    if (player != null) player.close(); 
                }

                /**
                 * Arrete la lecture du fichier son
                 * @throws Exception 
                 */
                public void stop() throws Exception 
                {
                        if (player != null) 
                        {
                                isPlaying = false;
                                
                        }
                }
                /**
                 * Renvoie si le fichier est en cours de lecture ou non
                 * @return le fichier est en cours de lecture ou non
                 */
                public boolean isPlaying() 
                {
                        return isPlaying;
                }

                private boolean isPlaying = false;
                
        }