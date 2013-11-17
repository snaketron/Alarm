package struct.alarm;

import java.sql.Timestamp;

import struct.core.constants.CoreConst.AlarmType;

public class Alarm {
	private String name;
	private AlarmType alarmType;
	private boolean alive;
	private boolean enabled;
	private String dayOfWeek;
	private Timestamp timestamp;
	private String audio;
	private String video;
	
	public Alarm() {
		
	}
	
	public Alarm(String name, AlarmType alarmType, boolean enabled, 
			String dayOfWeek, Timestamp timestamp, String audio, 
			String video) {
		super();
		this.name = name;
		this.alarmType = alarmType;
		this.enabled = enabled;
		this.dayOfWeek = dayOfWeek;
		this.timestamp = timestamp;
		this.audio = audio;
		this.video = video;
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

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp Timestamp) {
		this.timestamp = Timestamp;
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
		result = prime * result
				+ ((alarmType == null) ? 0 : alarmType.hashCode());
		result = prime * result + (alive ? 1231 : 1237);
		result = prime * result + ((audio == null) ? 0 : audio.hashCode());
		result = prime * result
				+ ((dayOfWeek == null) ? 0 : dayOfWeek.hashCode());
		result = prime * result + (enabled ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result + ((video == null) ? 0 : video.hashCode());
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
		if (alarmType != other.alarmType)
			return false;
		if (alive != other.alive)
			return false;
		if (audio == null) {
			if (other.audio != null)
				return false;
		} else if (!audio.equals(other.audio))
			return false;
		if (dayOfWeek == null) {
			if (other.dayOfWeek != null)
				return false;
		} else if (!dayOfWeek.equals(other.dayOfWeek))
			return false;
		if (enabled != other.enabled)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		if (video == null) {
			if (other.video != null)
				return false;
		} else if (!video.equals(other.video))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Alarm [name=" + name + ", alarmType=" + alarmType + ", alive="
				+ alive + ", enabled=" + enabled + ", dayOfWeek=" + dayOfWeek
				+ ", timestamp=" + timestamp + ", audio=" + audio + ", video="
				+ video + "]";
	}
}