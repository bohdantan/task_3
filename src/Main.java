import device.Device;
import parsers.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println(Checker.check("src/d.xml", "src/d.xsd"));

        System.out.println("DOM Parser");
        ArrayList<Device> devices = DOMParser.parse("src/d.xml");
        for (Device d : devices) {
            System.out.println(d);
        }

        System.out.println("SAX Parser");
        devices = MySAXParser.parse("src/d.xml");
        for (Device d : devices) {
            System.out.println(d);
        }

        System.out.println("StAX Parser");
        devices = StAXParser.parse("d.xml");
        for (Device d : devices) {
            System.out.println(d);
        }
    }
} 
