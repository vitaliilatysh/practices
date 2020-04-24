package practice7.entities;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"devices"})
@XmlRootElement(name = "Devices")
public class Devices {

    @XmlElement(name = "Device", required = true)
    protected List<Device> devices;

    public List<Device> getDevices() {
        if (devices == null)
            devices = new ArrayList<>();
        return devices;
    }

    @Override
    public String toString() {
        if (devices == null || devices.size() == 0)
            return "Devices contain no items";
        StringBuilder result = new StringBuilder();
        for (Device question : devices)
            result.append(question).append('\n');
        return result.toString();
    }
}
