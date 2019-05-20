/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package email;
import javazoom.jl.player.advanced.*;
        import java.io.*;
import javazoom.jl.player.Player;
/**
 *
 * @author nathan
 */
  public class Sound {
                private Player player;
                public Sound(String path) throws Exception {
                        BufferedInputStream in = new BufferedInputStream(new FileInputStream(new File(path)));
                        player = new Player(in);
                }
                
                public Sound(String path,PlaybackListener listener) throws Exception {
                        InputStream in = (InputStream)new BufferedInputStream(new FileInputStream(new File(path)));
                        
                }
                
                public void play() throws Exception {
                        if (player != null) {
                                
                                isPlaying = true;
                                System.out.println("la");
                                player.play();
                        }
                }
                public void close() { if (player != null) player.close(); }
                public void play(int begin,int end) throws Exception {
                        if (player != null) {
                                isPlaying = true;
                               
                        }
                }
                
                public void stop() throws Exception {
                        if (player != null) {
                                isPlaying = false;
                                
                        }
                }
                
                public boolean isPlaying() {
                        return isPlaying;
                }

                private boolean isPlaying = false;
                
        }