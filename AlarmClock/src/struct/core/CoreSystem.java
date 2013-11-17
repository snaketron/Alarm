package struct.core;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import event.AlarmEvent;
import event.AlarmEventDispatcher;
import event.AlarmEventListener;
import event.EventConstants.EventType;
import struct.alarm.Alarm;
import struct.core.ticker.TickEvent;
import struct.core.ticker.TickListener;
import struct.core.ticker.Ticker;
import struct.management.AlarmManager;
import struct.player.AudioPlayer;

public class CoreSystem {
	private boolean systemAlive;
	private AlarmManager alarmManager;
	private AudioPlayer audioPlayer;
	private Ticker ticker;
	private Set<Alarm> alarms;
	private AlarmEventDispatcher eventDispatcher;
	
	public CoreSystem(AlarmEventDispatcher eventDispatcher) {
		this.alarmManager = new AlarmManager();
		this.audioPlayer = new AudioPlayer();
		this.alarms = new HashSet<>();
//		this.alarms = this.alarmManager.readAlarms();
		this.eventDispatcher = eventDispatcher;
		this.eventDispatcher.addAlarmEventListener(new AlarmEventListener() {
			@Override
			public void firedAlarmEvent(AlarmEvent alarmEvent) {
				Alarm alarm = (Alarm)alarmEvent.getSource();
				switch(alarmEvent.getEventType()) {
				case CREATE_ALARM:
					alarms.add(alarm);
					alarmManager.createAlarms(alarm);
					break;
				case REMOVE_ALARM:
					alarms.remove(alarm);
					alarmManager.deleteAlarm(alarm);
					break;
				case START_ALARM:
					break;
				case STOP_ALARM:
					audioPlayer.stopAlarm();
					break;
				default:
					break;
				}
			}
		});
		startSystem();
		
		this.ticker.addTickListener(new TickListener() {
			@Override
			public void tickOccurred(TickEvent tickEvent) {
				checkAlarms();
			}
		});
	}
	
	
	/**
	 * Starts the core system by starting the ticker.
	 */
	public void startSystem() {
		this.systemAlive = true;
		
		//creates player on a new thread
		Thread thread = new Thread(this.audioPlayer);
		thread.start();
		
		if(this.ticker == null) {
			createTicker();
		}
		this.ticker.setAlive(true);
	}
	
	
	public void stopSystem() {
		this.systemAlive = false;
		ticker.setAlive(false);
	}
	
	
	/**
	 * Creates the alarm ticker.
	 */
	private void createTicker() {
		this.ticker = new Ticker();
		Thread thread = new Thread(this.ticker);
		thread.start();
	}


	public boolean isSystemAlive() { 
		return this.systemAlive; 
	} 
	
	
	/**
	 * Checks the current alarms. If an alarm expires it is fired.
	 */
	public boolean checkAlarms() {
		Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
		for(Alarm alarm : this.alarms) {
			if(alarm.isAlive()) {
				if(alarm.getTimestamp().before(currentTimestamp)) {
					eventDispatcher.fireAlarmEvent(new AlarmEvent(EventType.START_ALARM, alarm));
					this.audioPlayer.startAlarm(alarm);
					alarm.setAlive(false);
				}
			}
			else {
				if(alarm.getTimestamp().after(currentTimestamp)) {
					alarm.setAlive(true);
				}
			}
		}
		return false;
	}
}