package gui.adder;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import event.AlarmEvent;
import event.AlarmEventDispatcher;
import event.EventConstants.EventType;
import struct.alarm.Alarm;
import struct.core.constants.CoreConst.AlarmType;

public class AlarmAdder extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField alarmName;
	private JComboBox<AlarmType> alarmType;
	private JComboBox<String> dayOfWeek;
	private JComboBox<Integer> year;
	private JComboBox<String> month;
	private JComboBox<Integer> day;
	private JComboBox<Integer> hour;
	private JComboBox<Integer> minute;
	private JComboBox<Integer> second;
	private JComboBox<String> audio;
	private JComboBox<String> video;
	private JButton createAlarmButton;
	private AlarmEventDispatcher eventDispatcher;

	/**
	 * AlarmAdder is a Panel that is used to add/create new alarms. 
	 * It is a composite part of the Alarm Gui.
	 */
	public AlarmAdder(AlarmEventDispatcher eventDispatcher) {
		this.setLayout(new BorderLayout());
		this.add(createFieldPanel(), BorderLayout.CENTER);
		this.add(createButton(), BorderLayout.SOUTH);
		
		this.eventDispatcher = eventDispatcher;
		this.validate();
		this.repaint();
	}
	
	private JPanel createButton() {
		JPanel buttonPanel = new JPanel(new BorderLayout());
		this.createAlarmButton = new JButton("Create Alarm");
		this.createAlarmButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//creates an alarm from the panel fields
				String name = alarmName.getText();
				AlarmType type = (AlarmType)alarmType.getSelectedItem();
				String dOW = dayOfWeek.getSelectedItem().toString();
				Calendar cal = Calendar.getInstance();
				cal.set((int)year.getSelectedItem(), month.getSelectedIndex(), 
						(int)day.getSelectedItem(), (int)hour.getSelectedItem(), 
						(int)minute.getSelectedItem(), (int) second.getSelectedItem());
				Timestamp timestamp = new Timestamp(cal.getTimeInMillis());
				String a = audio.getSelectedItem().toString();
				String v = video.getSelectedItem().toString();
				Alarm alarm = new Alarm(name, type, true, dOW, timestamp, a, v);
				eventDispatcher.fireAlarmEvent(new AlarmEvent(EventType.CREATE_ALARM, alarm));
			}
		});
		buttonPanel.add(this.createAlarmButton, BorderLayout.CENTER);
		return buttonPanel;
	}
	
	private JPanel createFieldPanel() {
		JPanel panel = new JPanel(new GridLayout(1, 4));
		panel.add(createIdFields());
		panel.add(createDateFields());
		panel.add(createTimeFields());
		panel.add(createMediumFields());
		showAppropriateFields();
		return panel;
	}
	
	private JPanel createIdFields() {
		JPanel id = new JPanel();
		id.setLayout(new BoxLayout(id, BoxLayout.Y_AXIS));
		
		this.alarmName = new JTextField();
		this.alarmName.setBorder(BorderFactory.createTitledBorder("Alarm Name"));
		this.alarmName.setText("enter alarm name!");
		
		this.alarmType = new JComboBox<AlarmType>(AlarmType.values());
		this.alarmType.setBorder(BorderFactory.createTitledBorder("Alarm Type"));
		this.alarmType.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				showAppropriateFields();
			}
		});
		
		id.add(alarmName);
		id.add(alarmType);
		return id;
	}
	
	private JPanel createDateFields() {
		JPanel date = new JPanel();
		date.setLayout(new BoxLayout(date, BoxLayout.Y_AXIS));
		
		this.dayOfWeek = new JComboBox<>(AlarmAdderRoutine.getDaysOfWeek());
		this.dayOfWeek.setBorder(BorderFactory.createTitledBorder("Day Of Week"));
		
		this.year = new JComboBox<>(AlarmAdderRoutine.getYears());
		this.year.setBorder(BorderFactory.createTitledBorder("Year"));
		this.year.setSelectedIndex(0);
		
		this.month = new JComboBox<>(AlarmAdderRoutine.getMonths());
		this.month.setSelectedIndex(AlarmAdderRoutine.getCurrentMonth());
		this.month.setBorder(BorderFactory.createTitledBorder("Month"));
		this.month.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				updateComboBoxValues();
			}
		});
		
		this.day = new JComboBox<>(AlarmAdderRoutine.getDays((int)year.getSelectedItem(), month.getSelectedIndex()));
		this.day.setSelectedIndex(AlarmAdderRoutine.getCurrentDay());
		this.day.setBorder(BorderFactory.createTitledBorder("Day"));
		
		date.add(dayOfWeek);
		date.add(year);
		date.add(month);
		date.add(day);
		return date;
	}
	
	private JPanel createTimeFields() {
		JPanel time = new JPanel();
		time.setLayout(new BoxLayout(time, BoxLayout.Y_AXIS));
		
		this.hour = new JComboBox<>(AlarmAdderRoutine.getHours());
		this.hour.setSelectedIndex(AlarmAdderRoutine.getCurrentHour());
		this.hour.setBorder(BorderFactory.createTitledBorder("Hour"));
		
		this.minute = new JComboBox<>(AlarmAdderRoutine.getMinutes());
		this.minute.setSelectedIndex(AlarmAdderRoutine.getCurrentMinute());
		this.minute.setBorder(BorderFactory.createTitledBorder("Minute"));
		
		this.second = new JComboBox<>(AlarmAdderRoutine.getSeconds());
		this.second.setSelectedIndex(AlarmAdderRoutine.getCurrentSecond());
		this.second.setBorder(BorderFactory.createTitledBorder("Second"));
		
		time.add(hour);
		time.add(minute);
		time.add(second);
		return time;
	}
	
	private JPanel createMediumFields() {
		JPanel medium = new JPanel();
		medium .setLayout(new BoxLayout(medium , BoxLayout.Y_AXIS));
	
		this.audio = new JComboBox<>(AlarmAdderRoutine.getAudios());
		this.audio.setBorder(BorderFactory.createTitledBorder("Audio"));
		
		this.video = new JComboBox<>(AlarmAdderRoutine.getVideos());
		this.video.setBorder(BorderFactory.createTitledBorder("Video"));
		
		medium.add(audio);
		medium.add(video);
		return medium;
	}
	
	private void showAppropriateFields() {
		AlarmType type = (AlarmType) alarmType.getSelectedItem();
		switch (type) {
		case ATOMIC:
			month.setVisible(true);
			day.setVisible(true);
			year.setVisible(true);
			dayOfWeek.setVisible(false);
			break;
		case RECURRING:
			year.setVisible(false);
			month.setVisible(false);
			day.setVisible(false);
			dayOfWeek.setVisible(true);
			break;
		}
	}
	
	private void updateComboBoxValues() {
		Integer[] values = AlarmAdderRoutine.getDays((int)year.getSelectedItem(), month.getSelectedIndex());
		if(values.length > day.getItemCount()) {
			for(int x = day.getItemCount(); x < values.length; x++) {
				day.addItem(x + 1);
			}
		}
		else if(values.length < day.getItemCount()) {
			for(int x = day.getItemCount(); x > values.length; x--) {
				day.removeItem(x);
			}
		}
	}
}