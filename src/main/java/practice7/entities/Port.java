package practice7.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Port", propOrder = {"port"})
public class Port {

    @XmlElement
    private String port;

    public void setPort(String port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "Port{" +
                "port='" + port + '\'' +
                '}';
    }

    public String getPort() {
        return port;
    }
}
