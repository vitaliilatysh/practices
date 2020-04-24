package practice7;

import org.xml.sax.SAXException;
import practice7.analyzers.Dom;
import practice7.analyzers.Sax;
import practice7.analyzers.Stax;
import practice7.entities.Devices;
import practice7.util.Sorter;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main {

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, TransformerException, XMLStreamException {

        String inputXml = new File(args[0]).getAbsolutePath();
        String inputXsd = new File("input.xsd").getAbsolutePath();

        System.out.println(inputXml + " is valid: " + validate(inputXml, inputXsd));

        // //////////////////////////////////////////////////////
        // DOM
        // //////////////////////////////////////////////////////

        // get
        Dom dom = new Dom(inputXml);
        dom.parse(true);
        Devices devices = dom.getDevices();

        // sort
        Sorter.sortDevicesByName(devices);

        // save
        Dom.saveXML(devices, "output.dom.xml");

        // //////////////////////////////////////////////////////
        // SAX
        // //////////////////////////////////////////////////////

        // get
        Sax sax = new Sax(inputXml);
        sax.parse(true);
        devices = sax.getDevices();

        // sort
        Sorter.sortDevicesByPortSizes(devices);

        // save
        Dom.saveXML(devices, "output.sax.xml");

        // //////////////////////////////////////////////////////
        // StAX
        // //////////////////////////////////////////////////////

        // get
        Stax stax = new Stax(inputXml);
        stax.parse();
        devices = stax.getDevices();

        // sort
        Sorter.sortDevicesByPrice(devices);

        // save
        Dom.saveXML(devices, "output.stax.xml");
    }

    static boolean validate(String xml, String xsd) {
        try {
            InputStream inputXml = new FileInputStream(new File(xml));
            InputStream inputXsd = new FileInputStream(new File(xsd));
            SchemaFactory factory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new StreamSource(inputXsd));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(inputXml));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}

class Demo {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException, XMLStreamException {
        Main.main(new String[]{"input.xml"});
    }
}

