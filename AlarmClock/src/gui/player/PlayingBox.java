package gui.player;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import struct.alarm.Alarm;
import event.AlarmEvent;
import event.AlarmEventDispatcher;
import event.EventConstants.EventType;

public class PlayingBox extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;
	private boolean alive;
	
	public PlayingBox(final Alarm alarm, final AlarmEventDispatcher eventDispatcher) {
		this.setLayout(new BorderLayout());
		this.alive = true;
		JButton stopButton = new JButton("STOP Alarm");
		stopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				stopPlaying();
				eventDispatcher.fireAlarmEvent(new AlarmEvent(EventType.STOP_ALARM, alarm));
			}
		});
		this.add(new JLabel("Playing NOW"), BorderLayout.NORTH); //TODO delete
		this.add(new JPanel(), BorderLayout.CENTER); //TODO video panel
		this.add(stopButton, BorderLayout.SOUTH);
		this.validate();
		this.repaint();
	}
	
	public void stopPlaying() {
		this.alive = false;
	}
	
	@Override
	public void run() {
		while(alive) {
			//play
		}
	}

	public boolean isAlive() {
		return alive;
	} 
}