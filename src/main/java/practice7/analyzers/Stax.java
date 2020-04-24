package practice7.analyzers;

import org.xml.sax.helpers.DefaultHandler;
import practice7.constants.Xml;
import practice7.entities.*;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import javax.xml.transform.stream.StreamSource;

public class Stax extends DefaultHandler {
    private String xmlFileName;

    private String currentElement;
    private Devices devices;
    private Device device;
    private Type type;
    private Power power;
    private Price price;
    private Port port;
    private Ports ports;

    public Stax(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    public Devices getDevices() {
        return devices;
    }

    public void parse() throws XMLStreamException {

        XMLInputFactory factory = XMLInputFactory.newInstance();

        factory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true);

        XMLEventReader reader = factory.createXMLEventReader(
                new StreamSource(xmlFileName));

        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();

            if (event.isCharacters() && event.asCharacters().isWhiteSpace())
                continue;

            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();
                currentElement = startElement.getName().getLocalPart();

                if (currentElement == Xml.DEVICES.value()) {
                    devices = new Devices();
                    continue;
                }

                if (currentElement == Xml.DEVICE.value()) {
                    device = new Device();
                    continue;
                }

                if (currentElement == Xml.PORTS.value()) {
                    ports = new Ports();
                    continue;
                }

                if (currentElement == Xml.PORT.value()) {
                    port = new Port();
                    continue;
                }

                if (currentElement == Xml.PRICE.value()) {
                    price = new Price();
                    Attribute attribute = startElement.getAttributeByName(
                            new QName(Xml.ATTR_CURRENCY.value()));
                    if (attribute != null) {
                        price.setCurrency(attribute.getValue());
                    }
                    continue;
                }

                if (currentElement == Xml.TYPE.value()) {
                    type = new Type();
                    continue;
                }

                if (currentElement == Xml.POWER.value()) {
                    power = new Power();
                    Attribute attribute = startElement.getAttributeByName(
                            new QName(Xml.ATTR_VALUE.value()));
                    if (attribute != null) {
                        power.setValue(attribute.getValue());
                    }
                    continue;
                }
            }

            if (event.isCharacters()) {
                Characters characters = event.asCharacters();

                if (currentElement == Xml.NAME.value()) {
                    device.setName(characters.getData());
                    continue;
                }

                if (currentElement == Xml.ORIGIN.value()) {
                    device.setOrigin(characters.getData());
                    continue;
                }

                if (currentElement == Xml.PRICE.value()) {
                    price.setContent(Integer.parseInt(characters.getData()));
                    device.setPrice(price);
                    continue;
                }

                if (currentElement == Xml.TYPE.value()) {
                    device.setType(type);
                    continue;
                }

                if (currentElement == Xml.POWER.value()) {
                    power.setContent(Integer.parseInt(characters.getData()));
                    type.setPower(power);
                    continue;
                }

                if (currentElement == Xml.COOLER.value()) {
                    type.setCooler(Boolean.parseBoolean(characters.getData()));
                    continue;
                }

                if (currentElement == Xml.CRITICAL.value()) {
                    device.setCritical(Boolean.parseBoolean(characters.getData()));
                    continue;
                }

                if (currentElement == Xml.PERIPHERAL.value()) {
                    type.setPeripheral(Boolean.parseBoolean(characters.getData()));
                    continue;
                }

                if (currentElement == Xml.GROUP.value()) {
                    type.setGroup(characters.getData());
                    continue;
                }

                if (currentElement == Xml.PORT.value()) {
                    port.setPort(characters.getData());
                    continue;
                }
            }

            if (event.isEndElement()) {
                EndElement endElement = event.asEndElement();
                String localName = endElement.getName().getLocalPart();

                if (localName == Xml.DEVICE.value()) {
                    devices.getDevices().add(device);
                    continue;
                }

                if (localName == Xml.TYPE.value()) {
                    type.setPorts(ports);
                    device.setType(type);
                    continue;
                }

                if (localName == Xml.PORT.value()) {
                    ports.getPorts().add(port);
                    continue;
                }
            }
        }
        reader.close();
    }
}
