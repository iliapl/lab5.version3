package util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import toVehicle.Vehicle;
import toVehicle.VehiclesCollecton;
import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.PrintWriter;

public class WriteFileToXML {
    EnvDoing envDoing = new EnvDoing();
    VehiclesCollecton collecton;
    private final PrintWriter printWriter;

    public WriteFileToXML(PrintWriter printWriter, VehiclesCollecton collecton) {
        this.printWriter = printWriter;
        this.collecton = collecton;
    }
    public int toSaveToXML() throws ParserConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();
        Element orgElement = doc.createElement("Vehicles");
        for (Vehicle vehicle : collecton.getVehicles()) {
            orgElement.appendChild(WriteFileToXML.toXmlElement(vehicle, doc));
        }
        doc.appendChild(orgElement);
        try {
            if (saveDocumentToFile(doc) < 0) {
                return -1;
            }
        } catch (TransformerException e) {
            System.out.println("Ошибка трансформера" + '\n' + e.getLocalizedMessage());
        }
        return 1;
    }
    private int saveDocumentToFile(Document doc) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        File file = new File(envDoing.getPATHcollection());
        if (!file.exists()) {
            return -1;
        }
        StreamResult result = new StreamResult(file);
        result.setWriter(printWriter);
        transformer.transform(source, result);
        return 1;
    }

    private static Element toXmlElement(Vehicle vehicle, Document doc) {
        Element VehicleElement = doc.createElement("vehicle");
        VehicleElement.appendChild(doc.createElement("id"));
        VehicleElement.getChildNodes().item(0).setTextContent(String.valueOf(vehicle.getId()));
        VehicleElement.appendChild(doc.createElement("name"));
        VehicleElement.getChildNodes().item(1).setTextContent(String.valueOf(vehicle.getName()));
        VehicleElement.appendChild(doc.createElement("coordinates"));
        VehicleElement.getChildNodes().item(2).appendChild(doc.createElement("x"));
        VehicleElement.getChildNodes().item(2).getChildNodes().item(0).setTextContent(String.valueOf(vehicle.getCoordinates().getX()));
        VehicleElement.getChildNodes().item(2).appendChild(doc.createElement("y"));
        VehicleElement.getChildNodes().item(2).getChildNodes().item(1).setTextContent(String.valueOf(vehicle.getCoordinates().getY()));
        VehicleElement.appendChild(doc.createElement("time"));
        VehicleElement.getChildNodes().item(3).setTextContent(String.valueOf(vehicle.getCreationDate()));
        VehicleElement.appendChild(doc.createElement("enginePower"));
        VehicleElement.getChildNodes().item(4).setTextContent(String.valueOf(vehicle.getEnginePower()));
        VehicleElement.appendChild(doc.createElement("type"));
        VehicleElement.getChildNodes().item(5).setTextContent(String.valueOf(vehicle.getType()));
        VehicleElement.appendChild(doc.createElement("fuelType"));
        VehicleElement.getChildNodes().item(6).setTextContent(vehicle.getFuelType().name());
        return VehicleElement;
    }
}

