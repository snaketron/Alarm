package struct.core.ticker;

import javax.swing.event.EventListenerList;

import struct.core.constants.CoreConst;

public class Ticker implements Runnable {
	private boolean alive;
	protected EventListenerList listenerList = new EventListenerList();

	public void addTickListener(TickListener listener) {
		listenerList.add(TickListener.class, listener);
	}
	  
	public void removeTickListener(TickListener listener) {
	    listenerList.remove(TickListener.class, listener);
	}

	private void fireTickEvent(TickEvent tickEvent) {
		TickListener[] listeners = listenerList.getListeners(TickListener.class);
	    for(TickListener listener : listeners) {
	    	listener.tickOccurred(tickEvent);
	    }
	}

	public Ticker() {
		this.alive = true;
	}
	
	@Override
	public void run() {
		aliveTicking();
	}
	
	private void aliveTicking() {
		while(this.alive) {
			try {
				Thread.sleep(CoreConst.CHECK_PERIOD_MILLI);
				this.fireTickEvent(new TickEvent(this));
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			deadTicking();
		}
	}
	
	private void deadTicking() {
		while(!this.alive) {
			try {
				Thread.sleep(CoreConst.CHECK_PERIOD_MILLI);
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		aliveTicking();
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}
}