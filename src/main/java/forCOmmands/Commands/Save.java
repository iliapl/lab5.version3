package forCOmmands.Commands;

import forCOmmands.Command;
import forVehicles.VehiclesCollecton;
import util.WriteFileToXML;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.PrintWriter;

public class Save implements Command {
    private final VehiclesCollecton collection;
    private final WriteFileToXML writeFileToXML;

    public Save (VehiclesCollecton collection, WriteFileToXML writeFileToXML) {
        this.collection = collection;
        this.writeFileToXML = writeFileToXML;
    }

    @Override
    public void execute(String argument) {
        String filePath = System.getenv("LOL");  // Убедись, что переменная окружения "LOL" корректна
        if (filePath == null) {
            System.out.println("Переменная окружения LOL не найдена!");
            return;
        }

        try (PrintWriter printWriter = new PrintWriter(filePath)) {
            // Создаем объект WriteFileToXML, передавая PrintWriter и коллекцию
            WriteFileToXML writeFileToXML = new WriteFileToXML(printWriter, collection);
            writeFileToXML.toSaveToXML();  // Сохраняем коллекцию в XML
            System.out.println("Коллекция успешно сохранена в файл.");
        } catch (ParserConfigurationException e) {
            System.out.println("Ошибка при сохранении коллекции: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Ошибка при создании PrintWriter: " + e.getMessage());
        }
    }

}
