package struct.xml;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import struct.alarm.Alarm;
import struct.core.constants.XmlConst;
import struct.xml.routines.XmlCreator;
import struct.xml.routines.XmlDeleter;
import struct.xml.routines.XmlEditor;
import struct.xml.routines.XmlReader;

public class AlarmManager {
	private Document doc;
	
	public AlarmManager() {	
		File xmlFile = new File(XmlConst.XML_FILE);
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
	}
	
	public Set<Alarm> readAlarms() {
		Calendar cal = Calendar.getInstance();
		String currentDayOfWeek = cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.US);
		String currentDate = getCurrentDateString(cal);
		return XmlReader.readAlarms(doc, currentDayOfWeek, currentDate);
	}
	
	public boolean editAlarm(Alarm editedAlarm) {
		XmlEditor.editAlarm(doc, editedAlarm);
		transformXmlFile();
		return true;
	}
	
	public boolean deleteAlarms(Alarm deletedAlarm) {
		XmlDeleter.deleteAlarm(doc, deletedAlarm);
		transformXmlFile();
		return true;
	}
	
	public boolean createAlarms(Alarm createdAlarm) {
		XmlCreator.createAlarm(doc, createdAlarm);
		transformXmlFile();
		return true;
	}
	
	public boolean cleanAlarms() {
		//TODO: Clean (garbage collect) the alarms.
		return true;
	}
	
	private void transformXmlFile() {
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(this.doc);
			StreamResult result = new StreamResult(new File(XmlConst.XML_FILE));
			transformer.transform(source, result);
		} 
		catch (TransformerException e) {
			e.printStackTrace();
		}
	}
	
	private String getCurrentDateString(Calendar cal) {
		String day;
		if(cal.get(Calendar.DAY_OF_MONTH) < 10) {
			day = "0" + cal.get(Calendar.DAY_OF_MONTH);
		}
		else {
			day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
		}
		String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
		String year = String.valueOf(cal.get(Calendar.YEAR));
		return day + "." + month + "." + year;
	}
}