package struct.player;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class AudioPlayer {
    private Player player; 
    private final String DOCS = "docs" + File.separator;

    public AudioPlayer() {
    	
    }

    public void stop() { 
    	if(player != null) {
    		player.close(); 
    	}
    }

    public void play(String audio) {
        try {
            FileInputStream fis = new FileInputStream(DOCS + audio);
            BufferedInputStream bis = new BufferedInputStream(fis);
            player = new Player(bis);
        }
        catch (Exception e) {
            System.out.println("Problem playing file " + DOCS + audio);
            System.out.println(e);
        }

        new Thread() {
            public void run() {
                try { 
                	player.play(); 
                	System.out.println("playing started");
                }
                catch (Exception e) { 
                	System.out.println(e); 
                }
            }
        }.start();
    }
}