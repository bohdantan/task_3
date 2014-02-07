package parsers;


import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import device.Characteristics;
import device.Device;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Created by user on 07.02.14.
 */
public class DOMParser {

    public static ArrayList<Device> parse(String xmlName) {
        ArrayList<Device> devices = new ArrayList<Device>();
        try {
            File file = new File(xmlName);
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            NodeList list = doc.getChildNodes().item(0).getChildNodes();
            for (int i = 0; i < list.getLength(); ++i) {
                if (list.item(i).getNodeType() == Node.ELEMENT_NODE)
                devices.add(getDevice(list.item(i)));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return devices;
    }

    private static Device getDevice(Node root) {
        Device device = new Device();
        try {
            device.setName(root.getAttributes().item(0).getNodeValue());
            NodeList nodeList = root.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); ++i) {
                Node node = nodeList.item(i);
                if (node.getNodeName().equals("origin")) {
                    device.setOrigin(node.getTextContent());
                    continue;
                }
                if (node.getNodeName().equals("price")) {
                    device.setPrice(Integer.parseInt(node.getTextContent()));
                    continue;
                }
                if (node.getNodeName().equals("characteristics")) {
                    device.setCharacteristics(new Characteristics());
                    NodeList cNodeList = node.getChildNodes();
                    for (int j = 0; j < cNodeList.getLength(); ++j) {
                        Node cNode = cNodeList.item(j);
                        if (cNode.getNodeName().equals("peripheral")) {
                            device.getCharacteristics().setPeripheral(Boolean.parseBoolean(cNode.getTextContent()));
                            continue;
                        }
                        if (cNode.getNodeName().equals("energyUsage")) {
                            device.getCharacteristics().setEnergyUsage(Integer.parseInt(cNode.getTextContent()));
                            continue;
                        }
                        if (cNode.getNodeName().equals("hasCooler")) {
                            device.getCharacteristics().setHasCooler(Boolean.parseBoolean(cNode.getTextContent()));
                            continue;
                        }
                        if (cNode.getNodeName().equals("group")) {
                            device.getCharacteristics().setGroup(cNode.getTextContent());
                            continue;
                        }
                        if (cNode.getNodeName().equals("port")) {
                            device.getCharacteristics().getPort().add(cNode.getTextContent());
                            continue;
                        }
                    }
                    continue;
                }
                if (node.getNodeName() == "critical") {
                    device.setCritical(Boolean.parseBoolean(node.getTextContent()));
                    continue;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return device;
    }

}
