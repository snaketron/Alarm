package struct.alarm;

public class RecurringAlarm extends Alarm {
	private String dayOfWeek;
	
	public RecurringAlarm(String id, int fireTime, String music, 
			String video, boolean alive, String dayOfWeek) {
		super(id, fireTime, music, video, alive);
		this.dayOfWeek = dayOfWeek;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
}