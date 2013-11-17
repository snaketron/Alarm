package main;

import java.awt.Dimension;

import event.AlarmEventDispatcher;
import struct.core.CoreSystem;
import gui.AlarmGui;

public class AlarmApp {
	private AlarmGui gui;
	private CoreSystem core;
	private AlarmEventDispatcher eventDispatcher;
	
	public AlarmApp() {
		this.eventDispatcher = new AlarmEventDispatcher();
		this.gui = new AlarmGui(new Dimension(400, 350), eventDispatcher);
		this.core = new CoreSystem(eventDispatcher);
	}
	
	public static void main(String[] args) {
		new AlarmApp();
	}

	public AlarmGui getGui() {
		return gui;
	}

	public CoreSystem getCore() {
		return core;
	}

	public AlarmEventDispatcher getEventDispatcher() {
		return eventDispatcher;
	}
}
