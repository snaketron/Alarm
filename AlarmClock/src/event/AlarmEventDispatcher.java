package event;

import javax.swing.event.EventListenerList;

public class AlarmEventDispatcher {
	private EventListenerList listenerList;
	
	public AlarmEventDispatcher() {
		this.listenerList = new EventListenerList();
	}
	
	public void addAlarmEventListener(AlarmEventListener listener) {
		listenerList.add(AlarmEventListener.class, listener);
	}
	 
	public void removeAlarmEventListener(AlarmEventListener listener) {
	    listenerList.remove(AlarmEventListener.class, listener);
	}
	
	public void fireAlarmEvent(AlarmEvent alarmEvent) {
		AlarmEventListener[] listeners = listenerList.getListeners(AlarmEventListener.class);
	    for(AlarmEventListener listener : listeners) {
	    	listener.firedAlarmEvent(alarmEvent);
	    }
	}
}
