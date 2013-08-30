package struct.management;

import io.XmlParser;

import java.util.List;

import struct.alarm.AtomicAlarm;
import struct.alarm.RecurringAlarm;

public class AlarmManager {
	private List<AtomicAlarm> atomicAlarms;
	private List<RecurringAlarm> recurringAlarms;
	private AlarmChecker alarmChecker;
	
	public AlarmManager() {
		XmlParser xmlParser = new XmlParser();
		this.atomicAlarms = xmlParser.getAtomicAlarms();
		this.recurringAlarms = xmlParser.getRecurringAlarms();
		this.alarmChecker = new AlarmChecker(this.atomicAlarms, this.recurringAlarms);
		startChecker();
	}

	private void startChecker() {
		Thread thread = new Thread(this.alarmChecker);
		thread.start();
	}
}
