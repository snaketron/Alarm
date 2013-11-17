package struct.core.ticker;

import java.util.EventListener;

public interface TickListener extends EventListener {
	public void tickOccurred(TickEvent tickEvent);
}
