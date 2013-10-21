package struct.core.ticker;

import java.util.EventObject;

public class TickEvent extends EventObject {
	private static final long serialVersionUID = 1L;

	public TickEvent(Object o) {
		super(o);
	}
}