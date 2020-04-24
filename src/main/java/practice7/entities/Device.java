package practice7.entities;

import javax.xml.bind.annotation.*;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Device", propOrder = {"name", "origin", "price", "type", "critical"})
public class Device {

    @XmlElement(name = "Name", required = true)
    protected String name;

    @XmlElement(name = "Origin", required = true)
    protected String origin;

    @XmlElement(name = "Price", required = true)
    protected Price price;

    @XmlElement(name = "Type", required = true)
    protected Type type;

    @XmlElement(name = "Critical", required = true)
    protected Boolean critical;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Boolean isCritical() {
        return critical;
    }

    public void setCritical(Boolean critical) {
        this.critical = critical;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Device{" +
                "name='" + name + '\'' +
                ", origin='" + origin + '\'' +
                ", price=" + price +
                ", type=" + type +
                ", critical='" + critical + '\'' +
                '}';
    }
}
