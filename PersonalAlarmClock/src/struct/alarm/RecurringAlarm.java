package struct.alarm;

public class RecurringAlarm extends Alarm {
	private String timeOfDay;
	
	public RecurringAlarm(int fireTime, String music, String video,
			boolean alive, String timeOfDay) {
		super(fireTime, music, video, alive);
		this.timeOfDay = timeOfDay;
	}

	public String getTimeOfDay() {
		return timeOfDay;
	}

	public void setTimeOfDay(String timeOfDay) {
		this.timeOfDay = timeOfDay;
	}
}