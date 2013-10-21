package struct.xml.routines;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import struct.alarm.Alarm;
import struct.core.constants.XmlConst;
import struct.core.constants.CoreConst.AlarmType;

public class XmlReader {
	
	/**
	 * Reads the xml file and extracts the alarms as a list. Based on their temporal
	 * attributes it sets their alive attribute.
	 * 
	 * @param doc
	 * @param currentDayOfWeek
	 * @param currentDateString
	 * @return
	 */
	public static Set<Alarm> readAlarms(Document doc, String currentDayOfWeek, String currentDateString) {
		Set<Alarm> alarms = new HashSet<Alarm>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		
		NodeList alarmList = doc.getElementsByTagName(XmlConst.ALARM);
		for (int a = 0; a < alarmList.getLength(); a++) {
			Node alarmNode = alarmList.item(a); 
			if(alarmNode.getNodeType() == Node.ELEMENT_NODE) {
				Alarm alarm = new Alarm();
				
				Element alarmElement = (Element) alarmNode;
				
				Node core = alarmElement.getElementsByTagName(XmlConst.CORE).item(0);
				Node fire = alarmElement.getElementsByTagName(XmlConst.FIRE).item(0);
				Node medium = alarmElement.getElementsByTagName(XmlConst.MEDIUM).item(0);
				
				//setting core attributess
				Element coreElement = (Element) core;
				int id = Integer.valueOf(coreElement.getElementsByTagName(XmlConst.ID).item(0).getTextContent());
				String name = coreElement.getElementsByTagName(XmlConst.NAME).item(0).getTextContent();
				AlarmType alarmType = AlarmType.find(coreElement.getElementsByTagName(XmlConst.ALARM_TYPE).item(0).getTextContent());
				boolean enabled = Boolean.valueOf(coreElement.getElementsByTagName(XmlConst.ENABLED).item(0).getTextContent());
				boolean alive = false;
				
				//setting fire attributes
				Element fireElement = (Element) fire;
				String dayOfWeek = fireElement.getElementsByTagName(XmlConst.DAY_OF_WEEK).item(0).getTextContent();
				String dateString = null;
				String timeString = null;
				Date date = null;
				
				switch (alarmType) {
				case ATOMIC:
					dateString = fireElement.getElementsByTagName(XmlConst.DATE).item(0).getTextContent();
					timeString = fireElement.getElementsByTagName(XmlConst.TIME).item(0).getTextContent();
					try {
						date = dateFormat.parse(dateString + " " + timeString);
					} 
					catch (ParseException e) {
						e.printStackTrace();
					}
					Date currentDate = new Date(System.currentTimeMillis());
					alive = date.after(currentDate);
					break;
				case RECURRING:
					alive = currentDayOfWeek.equals(dayOfWeek);
					timeString = fireElement.getElementsByTagName(XmlConst.TIME).item(0).getTextContent();
					
					if(alive) {
						try {
							date = dateFormat.parse(currentDateString + " " + timeString);
						} 
						catch (ParseException e) {
							e.printStackTrace();
						}
					}
					break;
				}

				//setting medium attributes
				Element mediumElement = (Element) medium;
				String audio = mediumElement.getElementsByTagName(XmlConst.AUDIO).item(0).getTextContent();
				String video = mediumElement.getElementsByTagName(XmlConst.VIDEO).item(0).getTextContent();
				
				alarm.setCore(id, name, alarmType, alive, enabled);
				alarm.setFire(dayOfWeek, date, dateString, timeString);
				alarm.setMedium(audio, video);
				alarms.add(alarm);
			}
		}
		return alarms;
	}
}