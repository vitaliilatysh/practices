package practice7.entities;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"peripheral", "power", "cooler", "group", "ports"})
@XmlRootElement(name = "Devices")
public class Type {
    @XmlElement
    private Boolean peripheral;

    @XmlElement
    private Power power;

    @XmlElement
    private Boolean cooler;

    @XmlElement
    private String group;

    @XmlElement
    private Ports ports;

    @Override
    public String toString() {
        return "Type{" +
                "peripheral=" + peripheral +
                ", power=" + power +
                ", cooler=" + cooler +
                ", group='" + group + '\'' +
                ", ports=" + ports +
                '}';
    }

    public Boolean isPeripheral() {
        return peripheral;
    }

    public void setPeripheral(Boolean peripheral) {
        this.peripheral = peripheral;
    }

    public Power getPower() {
        return power;
    }

    public void setPower(Power power) {
        this.power = power;
    }

    public Boolean isCooler() {
        return cooler;
    }

    public void setCooler(Boolean cooler) {
        this.cooler = cooler;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setPorts(Ports ports) {
        this.ports = ports;
    }

    public List<Port> getPorts() {
        return ports.getPorts();
    }
}
