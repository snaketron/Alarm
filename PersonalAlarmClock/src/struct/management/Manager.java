package struct.management;

import io.XmlParser;

import java.util.List;

import struct.alarm.AtomicAlarm;
import struct.alarm.RecurringAlarm;

public class Manager {
	private XmlParser xmlParser;
	private List<AtomicAlarm> atomicAlarms;
	private List<RecurringAlarm> recurringAlarms;
	private Checker alarmChecker;
	
	public Manager() {
		this.xmlParser = new XmlParser();
		this.atomicAlarms = xmlParser.getAtomicAlarms();
		this.recurringAlarms = xmlParser.getRecurringAlarms();
		this.alarmChecker = new Checker(this.atomicAlarms, this.recurringAlarms);
		new Thread(this.alarmChecker).start();
	}
	
	public void addAtomicAlarm(AtomicAlarm aa) {
		this.alarmChecker.stopChecking();
		this.atomicAlarms.add(aa);
		this.xmlParser.addAtomicAlarm(aa);
		this.alarmChecker.startChecking(this.atomicAlarms, this.recurringAlarms);
	}
	
	public void addRecurringAlarm(RecurringAlarm ra) {
		this.alarmChecker.stopChecking();
		this.recurringAlarms.add(ra);
		this.xmlParser.addRecurringAlarm(ra);
		this.alarmChecker.startChecking(this.atomicAlarms, this.recurringAlarms);
	}
	
	public void removeAtomicAlarm(AtomicAlarm aa) {
		this.alarmChecker.stopChecking();
		this.atomicAlarms.remove(aa);
		this.xmlParser.removeAtomicAlarm(aa);
		this.alarmChecker.startChecking(this.atomicAlarms, this.recurringAlarms);
	}
	
	public void removeRecurringAlarm(RecurringAlarm ra) {
		this.alarmChecker.stopChecking();
		this.recurringAlarms.remove(ra);
		this.xmlParser.removeRecurringAlarm(ra);
		this.alarmChecker.startChecking(this.atomicAlarms, this.recurringAlarms);
	}
	
	public void modifyAtomicAlarm(AtomicAlarm oldAlarm, AtomicAlarm newAlarm) {
		this.alarmChecker.stopChecking();
		this.atomicAlarms.remove(oldAlarm);
		this.xmlParser.removeAtomicAlarm(oldAlarm);
		this.atomicAlarms.add(newAlarm);
		this.xmlParser.addAtomicAlarm(newAlarm);
		this.alarmChecker.startChecking(this.atomicAlarms, this.recurringAlarms);
	}
	
	public void modifyRecurringAlarm(RecurringAlarm oldAlarm, RecurringAlarm newAlarm) {
		this.alarmChecker.stopChecking();
		this.recurringAlarms.remove(oldAlarm);
		this.xmlParser.removeRecurringAlarm(oldAlarm);
		this.recurringAlarms.add(newAlarm);
		this.xmlParser.addRecurringAlarm(newAlarm);
		this.alarmChecker.startChecking(this.atomicAlarms, this.recurringAlarms);
	}
	
	public void cloneAtomicAlarm(AtomicAlarm clonedAlarm) {
		this.alarmChecker.stopChecking();
		this.atomicAlarms.add(clonedAlarm);
		this.xmlParser.addAtomicAlarm(clonedAlarm);
		this.alarmChecker.startChecking(this.atomicAlarms, this.recurringAlarms);
	}
	
	public void cloneRecurringAlarm(RecurringAlarm clonedAlarm) {
		this.alarmChecker.stopChecking();
		this.recurringAlarms.add(clonedAlarm);
		this.xmlParser.addRecurringAlarm(clonedAlarm);
		this.alarmChecker.startChecking(this.atomicAlarms, this.recurringAlarms);
	}
}