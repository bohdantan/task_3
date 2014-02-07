package parsers;

import device.Characteristics;
import device.Device;

import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * Created by user on 07.02.14.
 */
public class StAXParser {
    public static ArrayList<Device> parse(String xmlName) {
        ArrayList<Device> devices = new ArrayList<Device>();
        Device device = null;
        String content = null;
        try {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader =
                factory.createXMLStreamReader(ClassLoader.getSystemResourceAsStream(xmlName));

        while(reader.hasNext()){
            int event = reader.next();

            switch(event){
                case XMLStreamConstants.START_ELEMENT:
                    if ("device".equals(reader.getLocalName())){
                        device = new Device();
                        device.setName(reader.getAttributeValue(0));
                        device.setCharacteristics(new Characteristics());
                    }
                    break;

                case XMLStreamConstants.CHARACTERS:
                    content = reader.getText().trim();
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    if (reader.getLocalName().equals("device")) {
                        devices.add(device);
                    } else if (reader.getLocalName().equals("origin")) {
                        device.setOrigin(content);
                    } else if (reader.getLocalName().equals("price")) {
                        device.setPrice(Integer.parseInt(content));
                    } else if (reader.getLocalName().equals("critical")) {
                        device.setCritical(Boolean.parseBoolean(content));
                    } else if (reader.getLocalName().equals("peripheral")) {
                        device.getCharacteristics().setPeripheral(Boolean.parseBoolean(content));
                    } else if (reader.getLocalName().equals("energyUsage")) {
                        device.getCharacteristics().setEnergyUsage(Integer.parseInt(content));
                    } else if (reader.getLocalName().equals("hasCooler")) {
                        device.getCharacteristics().setHasCooler(Boolean.parseBoolean(content));
                    } else if (reader.getLocalName().equals("group")) {
                        device.getCharacteristics().setGroup(content);
                    } else if (reader.getLocalName().equals("port")) {
                        device.getCharacteristics().getPort().add(content);
                    }
                    break;

                case XMLStreamConstants.START_DOCUMENT:
                    break;
            }
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return devices;
    }
}
