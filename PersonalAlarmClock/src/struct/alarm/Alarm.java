package struct.alarm;

public abstract class Alarm {
	private int fireTime;
	private String music;
	private String video;
	private boolean alive;
	
	public Alarm(int fireTime, String music, String video, boolean alive) {
		super();
		this.fireTime = fireTime;
		this.music = music;
		this.video = video;
		this.alive = alive;
	}

	public int getFireTime() {
		return fireTime;
	}

	public void setFireTime(int fireTime) {
		this.fireTime = fireTime;
	}

	public String getMusic() {
		return music;
	}

	public void setMusic(String music) {
		this.music = music;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}
}