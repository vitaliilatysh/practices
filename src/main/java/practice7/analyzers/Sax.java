package practice7.analyzers;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import practice7.constants.Constants;
import practice7.constants.Xml;
import practice7.entities.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class Sax extends DefaultHandler {
    private String xmlFileName;

    private String currentElement;
    private Devices devices;
    private Device device;
    private Type type;
    private Power power;
    private Price price;
    private Port port;
    private Ports ports;

    public Sax(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    public void parse(boolean validate) throws ParserConfigurationException,
            SAXException, IOException {

        SAXParserFactory factory = SAXParserFactory.newInstance(
                Constants.CLASS__SAX_PARSER_FACTORY_INTERNAL,
                this.getClass().getClassLoader());

        factory.setNamespaceAware(true);
        if (validate) {
            factory.setFeature(Constants.FEATURE__TURN_VALIDATION_ON, true);
            factory.setFeature(Constants.FEATURE__TURN_SCHEMA_VALIDATION_ON, true);
        }

        SAXParser parser = factory.newSAXParser();
        parser.parse(xmlFileName, this);
    }

    @Override
    public void error(org.xml.sax.SAXParseException e) throws SAXException {
        throw e;
    }

    public Devices getDevices() {
        return devices;
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) {

        currentElement = localName;

        if (currentElement == Xml.DEVICES.value()) {
            devices = new Devices();
            return;
        }

        if (currentElement == Xml.DEVICE.value()) {
            device = new Device();
            return;
        }

        if (currentElement == Xml.PORTS.value()) {
            ports = new Ports();
            return;
        }

        if (currentElement == Xml.PORT.value()) {
            port = new Port();
            return;
        }

        if (currentElement == Xml.PRICE.value()) {
            price = new Price();
            if (attributes.getLength() > 0) {
                price.setCurrency(attributes.getValue(Xml.ATTR_CURRENCY.value()));
            }
            return;
        }

        if (currentElement == Xml.TYPE.value()) {
            type = new Type();
            return;
        }

        if (currentElement == Xml.POWER.value()) {
            power = new Power();
            if (attributes.getLength() > 0) {
                power.setValue(attributes.getValue(Xml.ATTR_VALUE.value()));
            }
            return;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {

        String elementText = new String(ch, start, length).trim();

        if (elementText.isEmpty())
            return;

        if (currentElement == Xml.NAME.value()) {
            device.setName(elementText);
            return;
        }

        if (currentElement == Xml.ORIGIN.value()) {
            device.setOrigin(elementText);
            return;
        }

        if (currentElement == Xml.PRICE.value()) {
            price.setContent(Integer.parseInt(elementText));
            device.setPrice(price);
            return;
        }

        if (currentElement == Xml.TYPE.value()) {
            device.setType(type);
            return;
        }

        if (currentElement == Xml.POWER.value()) {
            power.setContent(Integer.parseInt(elementText));
            type.setPower(power);
            return;
        }

        if (currentElement == Xml.COOLER.value()) {
            type.setCooler(Boolean.parseBoolean(elementText));
            return;
        }

        if (currentElement == Xml.CRITICAL.value()) {
            device.setCritical(Boolean.parseBoolean(elementText));
            return;
        }

        if (currentElement == Xml.PERIPHERAL.value()) {
            type.setPeripheral(Boolean.parseBoolean(elementText));
            return;
        }

        if (currentElement == Xml.GROUP.value()) {
            type.setGroup(elementText);
            return;
        }

        if (currentElement == Xml.PORT.value()) {
            port.setPort(elementText);
            return;
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) {

        if (localName == Xml.DEVICE.value()) {
            devices.getDevices().add(device);
            return;
        }

        if (localName == Xml.TYPE.value()) {
            type.setPorts(ports);
            device.setType(type);
            return;
        }

        if (localName == Xml.PORT.value()) {
            ports.getPorts().add(port);
            return;
        }
    }
}

