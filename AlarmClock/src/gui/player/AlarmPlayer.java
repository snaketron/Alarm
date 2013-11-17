package gui.player;

import java.awt.GridLayout;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;

import struct.alarm.Alarm;
import event.AlarmEventDispatcher;

public class AlarmPlayer extends JPanel {
	private static final long serialVersionUID = 1L;
	private AlarmEventDispatcher eventDispatcher;
	private Set<PlayingBox> playingBoxes;
	
	public AlarmPlayer(AlarmEventDispatcher eventDispatcher) {
		this.setLayout(new GridLayout(1, 10));
		this.eventDispatcher = eventDispatcher;
		this.playingBoxes = new HashSet<>();
	}
	
	public void createPlayingBox(Alarm alarm) {
		PlayingBox pb = new PlayingBox(alarm, this.eventDispatcher);
		new Thread(pb).start();
		playingBoxes.add(pb);
		this.add(pb);
		this.validate();
		this.repaint();
	}
	
	public void removePlayingBoxes() {
		for(PlayingBox pb : playingBoxes) {
			this.remove(pb);
		}
		this.validate();
		this.repaint();
	}
}
