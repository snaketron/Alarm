package struct.core.ticker;

import java.util.EventListener;

public interface TickEventListener extends EventListener {
	public void tickOccurred(TickEvent tickEvent);
}
