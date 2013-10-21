package struct.xml.routines;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import struct.alarm.Alarm;
import struct.core.constants.XmlConst;

public class XmlCreator {
	
	/**
	 * Adds the newly created alarm to the xml file.
	 * 
	 * @param doc
	 * @param createdAlarm
	 */
	public static void createAlarm(Document doc, Alarm createdAlarm) {
		Node rootNode = doc.getFirstChild();
		
		//create top elements
		Node alarm = doc.createElement(XmlConst.ALARM);
		rootNode.appendChild(alarm);
		
		Node core = doc.createElement(XmlConst.CORE);
		Node fire = doc.createElement(XmlConst.FIRE);
		Node medium = doc.createElement(XmlConst.MEDIUM);
		
		alarm.appendChild(core);
		alarm.appendChild(fire);
		alarm.appendChild(medium);
		
		//create core attributes
		Node id = doc.createElement(XmlConst.ID);
		Node name = doc.createElement(XmlConst.NAME);
		Node type = doc.createElement(XmlConst.ALARM_TYPE);
		Node enabled = doc.createElement(XmlConst.ENABLED);
		
		id.setTextContent(String.valueOf(createdAlarm.getId()));
		name.setTextContent(createdAlarm.getName());
		type.setTextContent(createdAlarm.getAlarmType().getType());
		enabled.setTextContent(String.valueOf(createdAlarm.isEnabled()));
		
		core.appendChild(id);
		core.appendChild(name);
		core.appendChild(type);
		core.appendChild(enabled);
		
		//create fire attributes
		Node dayOfWeek = doc.createElement(XmlConst.DAY_OF_WEEK);
		Node date = doc.createElement(XmlConst.DATE);
		Node time = doc.createElement(XmlConst.TIME);

		dayOfWeek.setTextContent(createdAlarm.getDayOfWeek());
		date.setTextContent(createdAlarm.getDateString());
		time.setTextContent(createdAlarm.getTimeString());
		
		fire.appendChild(dayOfWeek);
		fire.appendChild(date);
		fire.appendChild(time);
		
		//create medium attributes
		Node audio = doc.createElement(XmlConst.AUDIO);
		Node video = doc.createElement(XmlConst.VIDEO);
		
		audio.setTextContent(createdAlarm.getAudio());
		video.setTextContent(createdAlarm.getVideo());
		
		medium.appendChild(audio);
		medium.appendChild(video);
	}
}