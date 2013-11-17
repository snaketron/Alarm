package gui.viewer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

import event.AlarmEvent;
import event.AlarmEventDispatcher;
import event.EventConstants.EventType;
import struct.alarm.Alarm;

public class AtomicViewer extends JScrollPane {
	private static final long serialVersionUID = 1L;
	private AlarmEventDispatcher eventDispatcher;
	private JPanel view;
	private HashMap<Alarm, JPanel> alarmMap;
	private JPopupMenu menu;
	private JPanel selectedAlarmPanel;
	
	public AtomicViewer(AlarmEventDispatcher eventDispatcher) {
		this.eventDispatcher = eventDispatcher;
		this.alarmMap = new HashMap<>();
		this.view = new JPanel();
		this.menu = new JPopupMenu("Right-Click Menu");
		setMenu();
		view.setLayout(new BoxLayout(view, BoxLayout.Y_AXIS));
		//TODO access db to get alarms
		//TODO loop through the alarms
		for(int x = 0; x < 10; x++) {
//			alarmsColumn.add(createAlarmRow(alarm));
		}
		this.setViewportView(view);
	}
	
	public void addAlarmPanel(Alarm alarm) {
		this.alarmMap.put(alarm, createAlarmPanel(alarm));
		this.view.add(this.alarmMap.get(alarm));
		this.validate();
		this.repaint();
	}
	
	private void removeAlarmPanel(JPanel alarmPanel) {
		for(Alarm a : this.alarmMap.keySet()) {
			if(this.alarmMap.get(a).equals(alarmPanel)) {
				eventDispatcher.fireAlarmEvent(new AlarmEvent(EventType.REMOVE_ALARM, a));
				this.alarmMap.remove(a);
				this.view.remove(alarmPanel);
				break;
			}
		}
		this.menu.setVisible(false);
		this.validate();
		this.repaint();
	}
	
	private void setMenu() {
		JMenuItem removeAlarm = new JMenuItem("Remove Alarm");
		this.menu.add(removeAlarm);
		removeAlarm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				removeAlarmPanel(selectedAlarmPanel);
			}
		});
	}
	
	private JPanel createAlarmPanel(Alarm alarm) {
		JPanel alarmPanel = new JPanel(new BorderLayout());

		JLabel enabled = new JLabel("ON/OFF");
		enabled.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JPanel alarmInfo = new JPanel(new GridLayout(1, 2));
		alarmInfo.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JLabel name = new JLabel(alarm.getName());
		JLabel date = new JLabel(alarm.getTimestamp().toString());
		
		alarmInfo.add(name);
		alarmInfo.add(date);
		
		alarmPanel.add(enabled, BorderLayout.EAST);
		alarmPanel.add(alarmInfo, BorderLayout.CENTER);
		
		alarmPanel.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent a) {
				if(a.getButton() == 3) {
					menu.setVisible(true);
					menu.setLocation(a.getXOnScreen(), a.getYOnScreen());
					selectedAlarmPanel = (JPanel) a.getSource();
				}
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		return alarmPanel;
	}
}