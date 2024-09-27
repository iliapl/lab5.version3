package forFile;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import forVehicles.Vehicle;
import forVehicles.VehiclesCollecton;
import Utilities.EnvDoing;

import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.PrintWriter;

public class WriteFileToXML {
    private final VehiclesCollecton collecton;
    private final PrintWriter printWriter;
    public WriteFileToXML(PrintWriter printWriter, VehiclesCollecton collecton) {
        this.printWriter = printWriter;
        this.collecton = collecton;
    }
    public void toSaveToXML() throws ParserConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();
        Element orgElement = doc.createElement("Vehicles");
        for (Vehicle vehicle : collecton.getVehicles()) {
            orgElement.appendChild(WriteFileToXML.toXmlElement(vehicle, doc));
        }
        doc.appendChild(orgElement);
        try {
            saveDocumentToFile(doc);
        } catch (TransformerException e) {
            System.out.println("Ошибка трансформера" + '\n' + e.getLocalizedMessage());
        }
    }
    private void saveDocumentToFile(Document doc) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        File file = new File(new EnvDoing().getPATHcollection());
        if (!file.exists()) {
            return;
        }
        StreamResult result = new StreamResult(file);
        result.setWriter(printWriter);
        transformer.transform(source, result);
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
