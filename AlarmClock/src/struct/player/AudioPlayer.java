package struct.player;

import jaco.mp3.player.MP3Player;

import java.io.File;

import struct.alarm.Alarm;

public class AudioPlayer implements Runnable {
	private MP3Player player;
	private boolean playing = false;

    public AudioPlayer() {
        this.player = new MP3Player();
        this.player.setRepeat(true);
    }
    
    public void startAlarm(Alarm alarm) {
    	if(!this.player.getPlayList().contains(new File("docs/" + alarm.getAudio()))) {
    		this.player.addToPlayList(new File("docs/" + alarm.getAudio()));
    		this.playing = true;
    	}
    }
    
    public void stopAlarm() {
    	this.playing = false;
    }
    
    private void stopping() {
    	if(!this.player.isStopped()) {
    		this.player.stop();
    	}
    	if(!this.player.getPlayList().isEmpty()) {
    		this.player.getPlayList().clear();
    	}
    	
    	while(!playing) {
    		
    	}
    	playing();
    }
    
    private void playing() {
    	this.player.play();
    	while(playing) {
    		if(player.isStopped()) {
    			this.player.play();
    		}
    	}
    	stopping();
    }
 
	@Override
	public void run() {
		stopping();
	}
}