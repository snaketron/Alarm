package io;
 
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import constants.ConstantVariables;
import constants.XmlConstants;

import struct.alarm.AtomicAlarm;
import struct.alarm.RecurringAlarm;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;
 
public class XmlParser {
	private Document doc;
	private List<AtomicAlarm> atomicAlarms;
	private List<RecurringAlarm> recurringAlarms;
	private String currentDayOfWeek;
	private String currentDate;
	
	public XmlParser() {
		this.atomicAlarms = new ArrayList<>();
		this.recurringAlarms = new ArrayList<>();		
		File xmlFile = new File(XmlConstants.XML_FILE);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			try {
				this.doc = dBuilder.parse(xmlFile);
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		this.doc.getDocumentElement().normalize();
		setCurrentData();
		parseXml();
	}
	
	private void parseXml() {
		NodeList alarmList = this.doc.getElementsByTagName(XmlConstants.ALARM_NODE);
		for (int a = 0; a < alarmList.getLength(); a++) {
			Node alarmNode = alarmList.item(a); 
			if(alarmNode.getNodeType() == Node.ELEMENT_NODE) {
				Element alarmElement = (Element) alarmNode;
				if(alarmElement.getAttribute(XmlConstants.ALARM_TYPE).equals(XmlConstants.ATOMIC_TYPE)) {
					Node fire = alarmElement.getElementsByTagName(XmlConstants.ALARM_FIRE).item(0);
					Element fireElement = (Element) fire;
					String date = fireElement.getElementsByTagName(XmlConstants.FIRE_DATE).item(0).getTextContent();
					if(this.currentDate.equals(date)) {
						String time = fireElement.getElementsByTagName(XmlConstants.FIRE_TIME).item(0).getTextContent();
						String id = alarmElement.getAttribute(XmlConstants.ALARM_ID);
						String alive = alarmElement.getElementsByTagName(XmlConstants.ALARM_ALIVE).item(0).getTextContent();
						String music = alarmElement.getElementsByTagName(XmlConstants.ALARM_MUSIC).item(0).getTextContent();
						String video = alarmElement.getElementsByTagName(XmlConstants.ALARM_VIDEO).item(0).getTextContent();
						this.atomicAlarms.add(new AtomicAlarm(id, timeToInt(time), music, video, Boolean.parseBoolean(alive)));
					}
				}
				else if(alarmElement.getAttribute(XmlConstants.ALARM_TYPE).equals(XmlConstants.RECURRING_TYPE)) {
					Node fire = alarmElement.getElementsByTagName(XmlConstants.ALARM_FIRE).item(0);
					Element fireElement = (Element) fire;
					String dayOfWeek = fireElement.getElementsByTagName(XmlConstants.FIRE_DAYOFWEEK).item(0).getTextContent();
					if(this.currentDayOfWeek.equals(dayOfWeek)) {
						String time = fireElement.getElementsByTagName(XmlConstants.FIRE_TIME).item(0).getTextContent();
						String id = alarmElement.getAttribute(XmlConstants.ALARM_ID);
						String alive = alarmElement.getElementsByTagName(XmlConstants.ALARM_ALIVE).item(0).getTextContent();
						String music = alarmElement.getElementsByTagName(XmlConstants.ALARM_MUSIC).item(0).getTextContent();
						String video = alarmElement.getElementsByTagName(XmlConstants.ALARM_VIDEO).item(0).getTextContent();
						this.recurringAlarms.add(new RecurringAlarm(id, timeToInt(time), music, video, Boolean.parseBoolean(alive), dayOfWeek));
					}
				}
			}
		}
	}
	
	private void setCurrentData() {
		Calendar cal = Calendar.getInstance();
		this.currentDate = cal.get(Calendar.DAY_OF_MONTH) + 
				"-" + (cal.get(Calendar.MONTH) + 1) + 
				"-" + cal.get(Calendar.YEAR);
		this.currentDayOfWeek = ConstantVariables.WEEKDAYS[cal.get(Calendar.DAY_OF_WEEK)];
	}
	
	private int timeToInt(String time) {
		StringTokenizer st = new StringTokenizer(time, ":", false);
		return Integer.parseInt(st.nextToken()) * 60 * 60 
				+ Integer.parseInt(st.nextToken()) * 60
				+ Integer.parseInt(st.nextToken());
	}

	public List<AtomicAlarm> getAtomicAlarms() {
		return atomicAlarms;
	}

	public List<RecurringAlarm> getRecurringAlarms() {
		return recurringAlarms;
	}
	
	public void addAtomicAlarm(AtomicAlarm aa) {

	}
	
	public void addRecurringAlarm(RecurringAlarm ra) {
		
	}
	
	public void removeAtomicAlarm(AtomicAlarm aa) {
		
	}
	
	public void removeRecurringAlarm(RecurringAlarm ra) {
		
	}
	
	public void modifyAtomicAlarm(AtomicAlarm aa) {
		
	}
	
	public void modifyRecurringAlarm(RecurringAlarm ra) {
		
	}
	
	public void cloneAtomicAlarm(AtomicAlarm aa) {
		
	}
	
	public void cloneRecurringAlarm(RecurringAlarm ra) {
		
	}
}