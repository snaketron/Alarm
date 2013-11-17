package gui;

import event.AlarmEvent;
import event.AlarmEventDispatcher;
import event.AlarmEventListener;
import gui.adder.AlarmAdder;
import gui.player.AlarmPlayer;
import gui.viewer.AlarmViewer;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import struct.alarm.Alarm;

public class AlarmGui extends JFrame {
	private static final long serialVersionUID = 1L;
	private AlarmEventDispatcher eventDispatcher;
	private AlarmAdder adder;
	private AlarmViewer viewer;
	private AlarmPlayer player;
	
	public AlarmGui(Dimension dimension, AlarmEventDispatcher eventDispatcher) {
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setSize(dimension);
		this.eventDispatcher = eventDispatcher;
		
		this.adder = new AlarmAdder(this.eventDispatcher);
		this.viewer = new AlarmViewer(this.eventDispatcher);
		this.player = new AlarmPlayer(this.eventDispatcher);
		setEventDispatcher();
		
		this.setLayout(new BorderLayout());
		this.add(adder, BorderLayout.NORTH);
		this.add(viewer, BorderLayout.CENTER);
		this.add(player, BorderLayout.SOUTH);
		
		this.setVisible(true);
		this.validate();
		this.repaint();
	}
	
	private void setEventDispatcher() {
		this.eventDispatcher.addAlarmEventListener(new AlarmEventListener() {
			@Override
			public void firedAlarmEvent(AlarmEvent alarmEvent) {
				Alarm alarm = (Alarm)alarmEvent.getSource();
				switch(alarmEvent.getEventType()) {
				case CREATE_ALARM:
					viewer.addAlarmPanel(alarm);
					break;
				case REMOVE_ALARM:
					break;
				case START_ALARM:
					player.createPlayingBox(alarm);
					break;
				case STOP_ALARM:
					player.removePlayingBoxes();
					break;
				default:
					break;
				}
			}
		});
	}
}