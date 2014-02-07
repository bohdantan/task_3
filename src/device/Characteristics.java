package device;

import java.util.ArrayList;
import java.util.List;

public class Characteristics {

    boolean peripheral;
    long energyUsage;
    boolean hasCooler;
    String group;
    List<String> port;

    public boolean isPeripheral() {
        return peripheral;
    }

    public void setPeripheral(boolean value) {
        this.peripheral = value;
    }

    public long getEnergyUsage() {
        return energyUsage;
    }

    public void setEnergyUsage(long value) {
        this.energyUsage = value;
    }

    public boolean isHasCooler() {
        return hasCooler;
    }

    public void setHasCooler(boolean value) {
        this.hasCooler = value;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String value) {
        this.group = value;
    }

    public List<String> getPort() {
        if (port == null) {
            port = new ArrayList<String>();
        }
        return this.port;
    }

    @Override
    public String toString() {
        return "Characteristics{" +
                "peripheral=" + peripheral +
                ", energyUsage=" + energyUsage +
                ", hasCooler=" + hasCooler +
                ", group='" + group + '\'' +
                ", port=" + port +
                '}';
    }
}
