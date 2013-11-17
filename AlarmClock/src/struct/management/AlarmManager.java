package struct.management;

import java.util.Set;

import struct.alarm.Alarm;

public class AlarmManager {
	
	public AlarmManager() {	
		//TODO connect to db and perform transactions
	}
	
	public Set<Alarm> readAlarms() {
		return null;
	}
	
	public boolean editAlarm(Alarm editedAlarm) {
		return true;
	}
	
	public boolean deleteAlarm(Alarm deletedAlarm) {
		return true;
	}
	
	public boolean createAlarms(Alarm createdAlarm) {
		return true;
	}
	
	public boolean cleanAlarms() {
		return true;
	}
}