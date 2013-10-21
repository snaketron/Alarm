package struct.xml.routines;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import struct.alarm.Alarm;
import struct.core.constants.XmlConst;

public class XmlDeleter {

	/**
	 * Deletes the specified alarm from the xml file.
	 * 
	 * @param doc
	 * @param deletedAlarm
	 * @return
	 */
	public static boolean deleteAlarm(Document doc, Alarm deletedAlarm) {
		NodeList alarmList = doc.getElementsByTagName(XmlConst.ALARM);
		for (int a = 0; a < alarmList.getLength(); a++) {
			Node alarmNode = alarmList.item(a); 
			if(alarmNode.getNodeType() == Node.ELEMENT_NODE) {
				Element alarmElement = (Element) alarmNode;
				Node core = alarmElement.getElementsByTagName(XmlConst.CORE).item(0);
				Element coreElement = (Element) core;
				int id = Integer.valueOf(coreElement.getElementsByTagName(XmlConst.ID).item(0).getTextContent());
				if(deletedAlarm.getId() == id) {
					doc.getFirstChild().removeChild(alarmNode);
				}
			}
		}
		return true;
	}
}