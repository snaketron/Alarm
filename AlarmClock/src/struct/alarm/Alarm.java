package struct.alarm;

import java.util.Date;

import struct.core.constants.CoreConst.AlarmType;

public class Alarm {
	private int id;
	private String name;
	private AlarmType alarmType;
	private boolean alive;
	private boolean enabled;
	private String dayOfWeek;
	private Date date;
	private String dateString;
	private String timeString;
	private String audio;
	private String video;
	
	public Alarm() {

	}
	
	public Alarm(int id, String name, AlarmType alarmType, boolean enabled, 
			String dayOfWeek, Date date, String dateString, String timeString, 
			String audio, String video) {
		super();
		this.id = id;
		this.name = name;
		this.alarmType = alarmType;
		this.enabled = enabled;
		this.dayOfWeek = dayOfWeek;
		this.date = date;
		this.dateString = dateString;
		this.timeString = timeString;
		this.audio = audio;
		this.video = video;
	}
	
	public void setCore(int id, String name, AlarmType alarmType, 
			boolean alive, boolean enabled) {
		this.id = id;
		this.name = name;
		this.alarmType = alarmType;
		this.alive = alive;
		this.enabled = enabled;
	}
	
	public void setFire(String dayOfWeek, Date date, String dateString, 
			String timeString) {
		this.dayOfWeek = dayOfWeek;
		this.date = date;
		this.dateString = dateString;
		this.timeString = timeString;
	}
	
	public void setMedium(String audio, String video) {
		this.audio = audio;
		this.video = video;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AlarmType getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(AlarmType alarmType) {
		this.alarmType = alarmType;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}

	public String getTimeString() {
		return timeString;
	}

	public void setTimeString(String timeString) {
		this.timeString = timeString;
	}

	public String getAudio() {
		return audio;
	}

	public void setAudio(String audio) {
		this.audio = audio;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alarm other = (Alarm) obj;
		if (id != other.id)
			return false;
		return true;
	}
}