package gui.viewer;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import event.AlarmEventDispatcher;
import struct.alarm.Alarm;
import struct.core.constants.CoreConst.AlarmType;

public class AlarmViewer extends JPanel {
	private static final long serialVersionUID = 1L;
	private AlarmEventDispatcher eventDispatcher;
	private JTabbedPane view;
	private AtomicViewer atomicViewer;
	private RecurringViewer recurringViewer;
	
	public AlarmViewer(AlarmEventDispatcher eventDispatcher) {
		this.setLayout(new BorderLayout());
		this.eventDispatcher = eventDispatcher;
		this.view = new JTabbedPane();
		this.atomicViewer = new AtomicViewer(this.eventDispatcher);
		this.recurringViewer = new RecurringViewer(this.eventDispatcher);
		view.addTab("Atomic", this.atomicViewer);
		view.addTab("Recurring", this.recurringViewer);
		this.add(view);
		this.validate();
		this.repaint();
	}
	
	public void addAlarmPanel(Alarm alarm) {
		if(alarm.getAlarmType().equals(AlarmType.ATOMIC)) {
			this.atomicViewer.addAlarmPanel(alarm);
		}
		else {
			this.recurringViewer.addAlarmPanel(alarm);
		}
	}
}