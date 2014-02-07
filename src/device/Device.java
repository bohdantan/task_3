package device;

public class Device {
    String origin;
    long price;
    Characteristics characteristics;
    boolean critical;
    String name;

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String value) {
        this.origin = value;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long value) {
        this.price = value;
    }

    public Characteristics getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(Characteristics value) {
        this.characteristics = value;
    }

    public boolean isCritical() {
        return critical;
    }

    public void setCritical(boolean value) {
        this.critical = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    @Override
    public String toString() {
        return "Device{" +
                "name='" + name + '\'' +
                ", origin='" + origin + '\'' +
                ", price=" + price +
                ", characteristics=" + characteristics +
                ", critical=" + critical +
                '}';
    }
}
