package struct.management;

import java.util.Calendar;
import java.util.List;

import struct.alarm.AtomicAlarm;
import struct.alarm.RecurringAlarm;

import constants.ConstantVariables;


public class AlarmChecker implements Runnable {
	private List<AtomicAlarm> atomicAlarms;
	private List<RecurringAlarm> recurringAlarms;
	private boolean validCheck;
	private int timeOfDay;
	
	public AlarmChecker(List<AtomicAlarm> atomicAlarms, List<RecurringAlarm> recurringAlarms) {
		this.atomicAlarms = atomicAlarms;
		this.recurringAlarms = recurringAlarms;
		this.validCheck = true;
		this.timeOfDay = (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) * 3600
				+ Calendar.getInstance().get(Calendar.MINUTE) * 60
				+ Calendar.getInstance().get(Calendar.SECOND));
	}
	
	@Override
	public void run() {
		while(this.validCheck) {
			try {
				Thread.sleep(ConstantVariables.CHECKING_PERIOD);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.timeOfDay += ConstantVariables.CHECKING_PERIOD/1000;
			checkAtomicAlarms();
			checkRecurringAlarms();
		}
	}
	
	private void checkAtomicAlarms() {
		for(AtomicAlarm aa :this.atomicAlarms) {
			if(aa.getFireTime() > this.timeOfDay) {
				
			}
		}
	}
	
	private void checkRecurringAlarms() {
		for(RecurringAlarm ra :this.recurringAlarms) {
			if(ra.getFireTime() > this.timeOfDay) {
				//fire aa
			}
		}
	}
	
	public void stopChecker() {
		this.validCheck = false;
	}
}
