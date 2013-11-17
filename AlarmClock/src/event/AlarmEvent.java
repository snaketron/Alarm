package event;

import java.util.EventObject;

import event.EventConstants.EventType;

public class AlarmEvent extends EventObject {
	private static final long serialVersionUID = 1L;
	private EventType eventType;
	
	public AlarmEvent(EventType eventType, Object source) {
		super(source);
		this.eventType = eventType;
	}

	public EventType getEventType() {
		return eventType;
	}
}
