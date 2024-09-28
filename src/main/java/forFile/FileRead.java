package forFile;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import forVehicles.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class FileRead {
    public BufferedInputStream bufferedInputStream;
    public File file;
    public FileRead(BufferedInputStream bufferedInputStream, File file) {
        this.bufferedInputStream = bufferedInputStream;
        this.file = file;
    }
    public HashSet<Vehicle> parserXML() {
        if (canReadElements()) {
            HashSet<Vehicle> vehicles = new HashSet<>(); //наш исходный хашс он здесь объявл впервые
            long id = 0;
            String name = null;
            Coordinates coordinates = null;
            java.time.LocalDate creationDate = null;
            int enginePower = 0;
            VehicleType type = null;
            FuelType fuelType = null;
            long maxId = 0;  // Новый счётчик для поиска максимального ID
            NodeList nodeList = getNodes();
            for (int i = 0; i < getNodes().getLength(); i++) {
                if (getNodes().item(i).getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }
                if (getNodes().item(i).equals("Vehicle")) {
                    continue;
                }
                NodeList elements = nodeList.item(i).getChildNodes();
                for (int co = 0; co < elements.getLength(); co++) {
                    if (elements.item(co).getNodeType() == Node.ELEMENT_NODE) {
                        switch (elements.item(co).getNodeName()) {
                            case "id": {
                                id = Long.parseLong(elements.item(co).getTextContent());
                                maxId = Math.max(maxId, id);  // Обновляем maxId
                                break;
                            }
                            case "name": {
                                name = elements.item(co).getTextContent();
                                break;
                            }
                            case "creationDate": {
                                creationDate = LocalDate.parse(elements.item(co).getTextContent());
                                break;
                            }
                            case "enginePower": {
                                enginePower = Integer.parseInt(elements.item(co).getTextContent());
                                break;
                            }
                            case "type": {
                                type = VehicleType.valueOf(elements.item(co).getTextContent());
                                break;
                            }
                            case "fuelType": {
                                fuelType = FuelType.valueOf(elements.item(co).getTextContent());
                                break;
                            }
                            case "coordinates":{
                                NodeList nodeCoordinates = elements.item(co).getChildNodes();
                                long x = 0;
                                float y = 0;
                                for (int h = 0; h < nodeCoordinates.getLength(); h++) {
                                    if (nodeCoordinates.item(h).getNodeType() == Node.ELEMENT_NODE) {
                                        switch (nodeCoordinates.item(h).getNodeName()) {
                                            case "x": {
                                                x = Long.parseLong(nodeCoordinates.item(h).getTextContent());
                                                break;
                                            }
                                            case "y": {
                                                y = Float.parseFloat(nodeCoordinates.item(h).getTextContent());
                                                break;
                                            }
                                        }
                                    }
                                }
                                coordinates = new Coordinates(x,y);
                                break;
                            }
                        }
                    }
                }
                Vehicle vehicle = new Vehicle(name, coordinates, enginePower, type, fuelType);
                vehicle.setId((int) id);
                vehicle.setCreationDate(creationDate);
                vehicles.add(vehicle);
            }
            Vehicle.setNextId((int) (maxId + 1)); // Устанавливаем nextId как maxId + 1
            return vehicles;
        } else {
            HashSet<Vehicle> vehicles = new HashSet<>();
            return vehicles;
        }
    }
    private boolean canReadElements() {
        if (new FileExist().canReadFile(file)) {
            if (getFirstNode() != null) {
                return true;
            } else {
                System.out.println("Файл не содержит элементов");
                return false;
            }
        } else {
            System.out.println("Парсинг не может быть выполнен");
            return false;
        }
    }

    private Document buildDocument() throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        return docFactory.newDocumentBuilder().parse(file);
    }

    private Node getFirstNode() {
        try {
            Document doc = buildDocument();
            return doc.getFirstChild();
        } catch (SAXException | IOException | ParserConfigurationException e) {
            System.out.println("Ошибка парсинга");
            return null;
        }
    }

    private NodeList getNodes() {
        return getFirstNode().getChildNodes();
    }
}
