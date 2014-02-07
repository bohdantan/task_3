package parsers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import device.Characteristics;
import device.Device;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by user on 07.02.14.
 */
public class MySAXParser {

    public static ArrayList<Device> parse(String xmlName) {
        try {
            SAXParserFactory parserFactor = SAXParserFactory.newInstance();
            SAXParser parser = parserFactor.newSAXParser();
            SAXHandler handler = new SAXHandler();
            parser.parse(new File(xmlName), handler);
            return handler.getDevices();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Device>();
        }
        //Printing the list of employees obtained from XML
        /*for ( Device emp : handler.empList){
            System.out.println(emp);
        }*/

    }
}
/**
 * The Handler for SAX Events.
 */
class SAXHandler extends DefaultHandler {

    private ArrayList<Device> devices = new ArrayList<Device>();
    private Device device = null;
    private String content = null;
    @Override
    //Triggered when the start of tag is found.
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes)
            throws SAXException {

        if (qName.equals("device")){
            device = new Device();
            device.setName(attributes.getValue("name"));
            device.setCharacteristics(new Characteristics());
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("device")) {
            devices.add(device);
        } else if (qName.equals("origin")) {
            device.setOrigin(content);
        } else if (qName.equals("price")) {
            device.setPrice(Integer.parseInt(content));
        } else if (qName.equals("critical")) {
            device.setCritical(Boolean.parseBoolean(content));
        } else if (qName.equals("peripheral")) {
            device.getCharacteristics().setPeripheral(Boolean.parseBoolean(content));
        } else if (qName.equals("energyUsage")) {
            device.getCharacteristics().setEnergyUsage(Integer.parseInt(content));
        } else if (qName.equals("hasCooler")) {
            device.getCharacteristics().setHasCooler(Boolean.parseBoolean(content));
        } else if (qName.equals("group")) {
            device.getCharacteristics().setGroup(content);
        } else if (qName.equals("port")) {
            device.getCharacteristics().getPort().add(content);
        }

    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        content = String.copyValueOf(ch, start, length).trim();
    }

    public ArrayList<Device> getDevices() {
        return devices;
    }
}