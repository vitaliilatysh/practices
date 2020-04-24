package practice7.analyzers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import practice7.constants.Constants;
import practice7.constants.Xml;
import practice7.entities.*;
import practice7.util.Transformer;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class Dom {

    private String xmlFileName;

    private Devices devices;

    public Dom(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    public Devices getDevices() {
        return devices;
    }

    public void parse(boolean validate) throws ParserConfigurationException,
            SAXException, IOException {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(
                Constants.CLASS__DOCUMENT_BUILDER_FACTORY_INTERNAL,
                this.getClass().getClassLoader());

        dbf.setNamespaceAware(true);
        if (validate) {
            dbf.setFeature(Constants.FEATURE__TURN_VALIDATION_ON, true);
            dbf.setFeature(Constants.FEATURE__TURN_SCHEMA_VALIDATION_ON, true);
        }

        DocumentBuilder db = dbf.newDocumentBuilder();

        db.setErrorHandler(new DefaultHandler() {
            @Override
            public void error(SAXParseException e) throws SAXException {
                throw e;
            }
        });

        Document document = db.parse(xmlFileName);
        Element root = document.getDocumentElement();

        devices = new Devices();

        NodeList questionNodes = root
                .getElementsByTagName(Xml.DEVICE.value());

        for (int j = 0; j < questionNodes.getLength(); j++) {
            Device device = getDevice(questionNodes.item(j));
            devices.getDevices().add(device);
        }
    }

    private Device getDevice(Node qNode) {
        Device device = new Device();
        Element qElement = (Element) qNode;

        Node nameNode = qElement.getElementsByTagName(Xml.NAME.value())
                .item(0);
        device.setName(nameNode.getTextContent());

        Node originNode = qElement.getElementsByTagName(Xml.ORIGIN.value())
                .item(0);
        device.setOrigin(originNode.getTextContent());


        Node priceNode = qElement.getElementsByTagName(Xml.PRICE.value())
                .item(0);
        device.setPrice(getPrice(priceNode));

        Node typeNode = qElement.getElementsByTagName(Xml.TYPE.value())
                .item(0);
        device.setType(getType(typeNode));

        Node criticalNode = qElement.getElementsByTagName(Xml.CRITICAL.value())
                .item(0);
        device.setCritical(Boolean.parseBoolean(criticalNode.getTextContent()));

        return device;
    }

    private Price getPrice(Node aNode) {
        Price price = new Price();
        Element aElement = (Element) aNode;

        String currency = aElement.getAttribute(Xml.ATTR_CURRENCY.value());
        price.setCurrency(String.valueOf(currency));

        String content = aElement.getTextContent();
        price.setContent(Integer.parseInt(content));

        return price;
    }

    private Type getType(Node aNode) {
        Type type = new Type();
        Element aElement = (Element) aNode;

        Node perifNode = aElement.getElementsByTagName(Xml.PERIPHERAL.value()).item(0);
        type.setPeripheral(Boolean.parseBoolean(perifNode.getTextContent()));

        Node powerNode = aElement.getElementsByTagName(Xml.POWER.value()).item(0);
        type.setPower(getPower(powerNode));

        Node coolerNode = aElement.getElementsByTagName(Xml.COOLER.value()).item(0);
        type.setCooler(Boolean.parseBoolean(coolerNode.getTextContent()));

        Node groupNode = aElement.getElementsByTagName(Xml.GROUP.value()).item(0);
        type.setGroup((groupNode.getTextContent()));

        Node portsNode = aElement.getElementsByTagName(Xml.PORTS.value())
                .item(0);
        type.setPorts(getPorts(portsNode));

        return type;
    }

    private Power getPower(Node aNode) {
        Power power = new Power();
        Element aElement = (Element) aNode;

        String value = aElement.getAttribute(Xml.ATTR_VALUE.value());
        power.setValue(value);

        String content = aElement.getTextContent();
        power.setContent(Integer.parseInt(content));

        return power;
    }

    private Ports getPorts(Node aNode) {
        Ports ports = new Ports();
        Element aElement = (Element) aNode;

        NodeList portsNode = aElement.getElementsByTagName(Xml.PORT.value());
        for(int i = 0; i < portsNode.getLength(); i++){
            ports.getPorts().add(getPort(portsNode.item(i)));
        }

        return ports;
    }

    private Port getPort(Node aNode) {
        Port port = new Port();

        port.setPort(aNode.getTextContent());

        return port;
    }

    public static void saveXML(Devices devices, String xmlFileName)
            throws ParserConfigurationException, TransformerException {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(
                Constants.CLASS__DOCUMENT_BUILDER_FACTORY_INTERNAL,
                Dom.class.getClassLoader());

        dbf.setNamespaceAware(true);

        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.newDocument();

        Element devicesElement = document.createElementNS(
                Constants.MY_NS__URI, Xml.DEVICES.value());

        devicesElement.setAttributeNS(
                XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI,
                Constants.SCHEMA_LOCATION__ATTR_FQN,
                Constants.SCHEMA_LOCATION__URI);

        document.appendChild(devicesElement);

        for (Device device : devices.getDevices()) {
            Element deviceElement = document.createElement(Xml.DEVICE.value());
            devicesElement.appendChild(deviceElement);

            Element nameElement =
                    document.createElement(Xml.NAME.value());
            nameElement.setTextContent(device.getName());
            deviceElement.appendChild(nameElement);

            Element originElement =
                    document.createElement(Xml.ORIGIN.value());
            originElement.setTextContent(device.getOrigin());
            deviceElement.appendChild(originElement);

            Element priceElement =
                    document.createElement(Xml.PRICE.value());
            priceElement.setAttribute(Xml.ATTR_CURRENCY.value(), device.getPrice().getCurrency());
            priceElement.setTextContent(String.valueOf(device.getPrice().getContent()));
            deviceElement.appendChild(priceElement);

            Element typeElement =
                    document.createElement(Xml.TYPE.value());

            Element peripheralElement = document.createElement(Xml.PERIPHERAL.value());
            peripheralElement.setTextContent(String.valueOf(device.getType().isPeripheral()));
            typeElement.appendChild(peripheralElement);

            Element powerElement =
                    document.createElement(Xml.POWER.value());
            powerElement.setAttribute(Xml.ATTR_VALUE.value(), device.getType().getPower().getValue());
            powerElement.setTextContent(String.valueOf(device.getType().getPower().getContent()));
            typeElement.appendChild(powerElement);

            Element coolerElement = document.createElement(Xml.COOLER.value());
            coolerElement.setTextContent(String.valueOf(device.getType().isCooler()));
            typeElement.appendChild(coolerElement);

            Element groupElement = document.createElement(Xml.GROUP.value());
            groupElement.setTextContent(String.valueOf(device.getType().getGroup()));
            typeElement.appendChild(groupElement);

            Element portsElement =
                    document.createElement(Xml.PORTS.value());
            for (Port port : device.getType().getPorts()) {
                Element portElement = document.createElement(Xml.PORT.value());
                portElement.setTextContent(port.getPort());
                portsElement.appendChild(portElement);
            }

            typeElement.appendChild(portsElement);
            deviceElement.appendChild(typeElement);

            Element criticalElement = document.createElement(Xml.CRITICAL.value());
            criticalElement.setTextContent(String.valueOf(device.isCritical()));
            deviceElement.appendChild(criticalElement);

        }

        Transformer.saveToXML(document, xmlFileName); // DOM -> XML
    }
}
