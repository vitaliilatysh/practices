package practice7.entities;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"ports"})
@XmlRootElement(name = "Ports")
public class Ports {

    @XmlElement(name = "Port", required = true)
    protected List<Port> ports;

    public List<Port> getPorts() {
        if (ports == null)
            ports = new ArrayList<>();
        return ports;
    }

    @Override
    public String toString() {
        if (ports == null || ports.size() == 0)
            return "Ports contain no items";
        StringBuilder result = new StringBuilder();
        for (Port port : ports)
            result.append(port).append('\n');
        return result.toString();
    }
}
