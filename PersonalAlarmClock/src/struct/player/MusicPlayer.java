package struct.player;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MusicPlayer {
	public static void playMusic() throws UnsupportedAudioFileException, 
	IOException, LineUnavailableException, InterruptedException {
		File soundFile = new File("beep.wav");
	    AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);
	
	    DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
	    Clip clip = (Clip) AudioSystem.getLine(info);
	    clip.open(sound);
	    clip.start();
	    Thread.sleep(1000);
	 }
}        
//clip.addLineListener(new LineListener() {
//    public void update(LineEvent event) {
//      if (event.getType() == LineEvent.Type.STOP) {
//        event.getLine().close();
//        System.exit(0);
//      }
//    }
//  });