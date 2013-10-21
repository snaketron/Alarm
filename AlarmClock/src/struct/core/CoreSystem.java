package struct.core;

import java.sql.Date;
import java.util.Set;

import struct.alarm.Alarm;
import struct.core.ticker.TickEvent;
import struct.core.ticker.TickEventListener;
import struct.core.ticker.Ticker;
import struct.player.AudioPlayer;
import struct.xml.AlarmManager;

public class CoreSystem {
	private boolean systemAlive;
	private AlarmManager alarmManager;
	private AudioPlayer audioPlayer;
	private Ticker ticker;
	private Set<Alarm> alarms;
	
	public CoreSystem() {
		this.alarmManager = new AlarmManager();
		this.audioPlayer = new AudioPlayer();
		this.alarms = this.alarmManager.readAlarms();
		startSystem();
		
		this.ticker.addTickEventListener(new TickEventListener() {
			@Override
			public void tickOccurred(TickEvent tickEvent) {
				checkAlarms();
			}
		});
	}
	
	private void startSystem() {
		this.systemAlive = true;
		if(this.ticker == null) {
			createTicker();
		}
		this.ticker.setAlive(true);
	}
	
	public void stopSystem() {
		this.systemAlive = false;
		ticker.setAlive(false);
	}
	
	private void createTicker() {
		this.ticker = new Ticker();
		Thread thread = new Thread(this.ticker);
		thread.start();
	}

	public boolean isSystemAlive() {
		return systemAlive;
	}
	
	public boolean checkAlarms() {
		Date currentDate = new Date(System.currentTimeMillis());
		for(Alarm alarm : this.alarms) {
			if(alarm.isAlive()) {
				if(alarm.getDate().before(currentDate)) {
					audioPlayer.play(alarm.getAudio());
				}
			}
		}
		return false;
	}
}