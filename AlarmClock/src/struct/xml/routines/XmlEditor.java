package struct.xml.routines;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import struct.alarm.Alarm;
import struct.core.constants.XmlConst;
import struct.core.constants.CoreConst.AlarmType;

public class XmlEditor {
	
	/**
	 * Based on the passed edited alarm the xml file content is also updated.
	 * 
	 * @param doc
	 * @param editedAlarms
	 * @return
	 */
	public static boolean editAlarm(Document doc, Alarm editedAlarm) {
		NodeList alarmList = doc.getElementsByTagName(XmlConst.ALARM);
		for (int a = 0; a < alarmList.getLength(); a++) {
			Node alarmNode = alarmList.item(a); 
			if(alarmNode.getNodeType() == Node.ELEMENT_NODE) {
				Element alarmElement = (Element) alarmNode;
				
				Node core = alarmElement.getElementsByTagName(XmlConst.CORE).item(0);
				Node fire = alarmElement.getElementsByTagName(XmlConst.FIRE).item(0);
				Node medium = alarmElement.getElementsByTagName(XmlConst.MEDIUM).item(0);
				
				Element coreElement = (Element) core;
				int id = Integer.valueOf(coreElement.getElementsByTagName(XmlConst.ID).item(0).getTextContent());
				if(editedAlarm.getId() == id) {
					//editing core
					coreElement.getElementsByTagName(XmlConst.NAME).item(0).setTextContent(editedAlarm.getName());
					coreElement.getElementsByTagName(XmlConst.ENABLED).item(0).setTextContent(String.valueOf(editedAlarm.isEnabled()));
					
					//editing fire
					Element fireElement = (Element) fire;
					if(editedAlarm.getAlarmType().equals(AlarmType.ATOMIC)) {
						fireElement.getElementsByTagName(XmlConst.TIME).item(0).setTextContent(editedAlarm.getTimeString());
						fireElement.getElementsByTagName(XmlConst.DATE).item(0).setTextContent(editedAlarm.getDateString());
					}
					else if(editedAlarm.getAlarmType().equals(AlarmType.RECURRING)) {
						fireElement.getElementsByTagName(XmlConst.DAY_OF_WEEK).item(0).setTextContent(editedAlarm.getDayOfWeek());
						fireElement.getElementsByTagName(XmlConst.TIME).item(0).setTextContent(editedAlarm.getTimeString());
					}
					
					//editing medium
					Element mediumElement = (Element) medium;
					mediumElement.getElementsByTagName(XmlConst.AUDIO).item(0).setTextContent(editedAlarm.getAudio());
					mediumElement.getElementsByTagName(XmlConst.VIDEO).item(0).setTextContent(editedAlarm.getVideo());
					break;
				}
			}
		}
		return true;
	}
}
