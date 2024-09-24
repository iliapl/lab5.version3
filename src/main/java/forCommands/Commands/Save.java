package forCommands.Commands;

import forCommands.Command;
import forVehicles.VehiclesCollecton;
import util.EnvDoing;
import forFile.WriteFileToXML;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.PrintWriter;

public class Save implements Command {
    private final VehiclesCollecton collection;
    public Save (VehiclesCollecton collection) {
        this.collection = collection;
    }

    @Override
    public void execute(String argument) {
        String filePath = new EnvDoing().getPATHcollection();
        if (filePath == null) {
            System.out.println("Неверно указан путь к файлу");
            return;
        }
        try (PrintWriter printWriter = new PrintWriter(filePath)) {
            WriteFileToXML writeFileToXML = new WriteFileToXML(printWriter, collection);
            writeFileToXML.toSaveToXML();
            System.out.println("Коллекция успешно сохранена в файл.");
        } catch (ParserConfigurationException e) {
            System.out.println("Ошибка при сохранении коллекции: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Ошибка при создании PrintWriter: " + e.getMessage());
        }
    }

}
