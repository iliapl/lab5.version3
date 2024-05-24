package util;

import FileDo.Filewas;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import toVehicle.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class FileRead {
    Document doc;
    private final Filewas filewas;
    public Scanner scanner;
    public BufferedInputStream bufferedReaderin;
    public File file;
    public FileRead(BufferedInputStream bufferedReader, Scanner scanner, File file) {
        this.bufferedReaderin = bufferedReader;
        this.file = file;
        this.scanner = scanner;
        filewas = new Filewas();
    }
    public boolean canReadElements() {
        if (filewas.canReadFile(file)) {
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
            return vehicles;
        } else {
            HashSet<Vehicle> vehicles = new HashSet<>();
            return vehicles;
        }
    }

    public Document buildDocument() throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        return docFactory.newDocumentBuilder().parse(file);
    }

    public Node getFirstNode() {
        try {
            doc = buildDocument();
            return doc.getFirstChild();
        } catch (SAXException | IOException | ParserConfigurationException e) {
            System.out.println("Ошибка парсинга");
            return null;
        }
    }

    public NodeList getNodes() {
        return getFirstNode().getChildNodes();
    }

    public Vehicle readVehicleFromConsole() {
        return new Vehicle(readNamefromConsole(), readCordinatesFromConsole(), readEnginePowerFromConsole(), readVehicleTypeFromConsole(), readFuelTypeFromConsole());
    }

    public String readNamefromConsole() {
        System.out.println("Введите имя");
        String name = scanner.nextLine();
        if (name == null || name.isEmpty()) {
            System.out.println("Вы не ввели имя");
            return readNamefromConsole();
        } else {
            return name;
        }
    }

    public Coordinates readCordinatesFromConsole() {
        System.out.println("Введите координату х");
        long x;
        while (true)
            try {
                x = Long.parseLong(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Введите корректное x");
            }
        System.out.println("Введите координату y");
        float y;
        while (true)
            try {
                y = Float.parseFloat(scanner.nextLine());
                if (y == 0 || y > 597) {
                    System.out.println("Вы ввели неверное значение, повторите попытку, y должен быть < 597");
                    y = scanner.nextFloat();
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Введите корректное y");
            }
        return new Coordinates(x, y);
    }

    public int readEnginePowerFromConsole() {
        System.out.println("Введите значение enginePower");
        int enginePower;
        while (true)
            try {
                enginePower = Integer.parseInt(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Введите корректное enginePower");
            }
        if (enginePower <= 0) {
            System.out.println("значение enginePower должно быть польше нуля, повторите попытку");
            return readEnginePowerFromConsole();
        } else {
            return enginePower;
        }
    }

    public VehicleType readVehicleTypeFromConsole() {
        VehicleType[] types = VehicleType.values();
        System.out.println("Выберите тип транспорта:");

        for (int i = 0; i < types.length; i++) {
            System.out.println(i + " - " + types[i]);
        }

        while (true) {
            System.out.print("Введите номер варианта: ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Ввод не должен быть пустым. Попробуйте снова.");
                continue;
            }

            try {
                int choice = Integer.parseInt(input);
                if (choice >= 0 && choice < types.length) {
                    return types[choice];
                } else {
                    System.out.println("Неверный выбор. Попробуйте снова.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Некорректный формат. Введите число, соответствующее типу транспорта.");
            }
        }
    }

    public FuelType readFuelTypeFromConsole() {
        FuelType[] fuelTypes = FuelType.values();
        System.out.println("Выберите тип топлива:");

        for (int i = 0; i < fuelTypes.length; i++) {
            System.out.println(i + " - " + fuelTypes[i]);
        }

        while (true) {
            System.out.print("Введите номер варианта: ");
            String input = scanner.nextLine().trim();
            try {
                int choice = Integer.parseInt(input);
                if (choice >= 0 && choice < fuelTypes.length) {
                    return fuelTypes[choice];
                } else {
                    System.out.println("Неверный выбор. Попробуйте снова.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Некорректный формат ввода. Введите число, соответствующее типу топлива.");
            }
        }
    }
}
