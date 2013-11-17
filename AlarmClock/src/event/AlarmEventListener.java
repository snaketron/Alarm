package event;

import java.util.EventListener;

public interface AlarmEventListener extends EventListener {
	public void firedAlarmEvent(AlarmEvent alarmEvent);
}