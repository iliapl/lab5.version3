package forFile;

import lombok.AllArgsConstructor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import forVehicles.Vehicle;
import forVehicles.VehiclesCollection;
import Utilities.EnvDoing;

import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

@AllArgsConstructor
public class WriteFileToXML {
    public final PrintWriter printWriter;
    private final VehiclesCollection vehiclesCollection;

    public void toSaveToXML() throws ParserConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();
        Element orgElement = doc.createElement("Vehicles");
        for (Vehicle vehicle : vehiclesCollection.getVehicles()) {
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

        // Получаем путь к файлу
        File file = new File(EnvDoing.getPATHcollection());

        // Проверяем, существует ли файл
        if (!file.exists()) {
            System.out.println("Файл не существует. Сохранение не выполнено.");
            return;
        }

        // Открываем файл для записи (перезапись существующего файла)
        try (PrintWriter writer = new PrintWriter(file)) {
            StreamResult result = new StreamResult(writer);
            // Выполняем запись документа в файл (перезапись)
            transformer.transform(source, result);
        } catch (IOException e) {
            System.out.println("Ошибка при работе с файлом: " + e.getMessage());
        }
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
        VehicleElement.appendChild(doc.createElement("creationDate"));
        VehicleElement.getChildNodes().item(3).setTextContent(String.valueOf(vehicle.getCreationDate()));
        VehicleElement.appendChild(doc.createElement("enginePower"));
        VehicleElement.getChildNodes().item(4).setTextContent(String.valueOf(vehicle.getEnginePower()));
        VehicleElement.appendChild(doc.createElement("type"));
        VehicleElement.getChildNodes().item(5).setTextContent(String.valueOf(vehicle.getVehicleType()));
        VehicleElement.appendChild(doc.createElement("fuelType"));
        VehicleElement.getChildNodes().item(6).setTextContent(vehicle.getFuelType().name());
        return VehicleElement;
    }
}