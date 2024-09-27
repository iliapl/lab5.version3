package forCommands.Commands;

import Utilities.EnvDoing;
import forCommands.Command;
import forFile.WriteFileToXML;
import forVehicles.VehiclesCollecton;

import javax.xml.parsers.ParserConfigurationException;

public class Save implements Command {
    private final EnvDoing envDoing;
    private final WriteFileToXML writeFileToXML;
    public Save (WriteFileToXML writeFileToXML, EnvDoing envDoing) {
        this.writeFileToXML = writeFileToXML;
        this.envDoing = envDoing;
    }

    @Override
    public void execute(String argument) {
        String filePath = envDoing.getPATHcollection();
        if (filePath == null) {
            System.out.println("Неверно указан путь к файлу");
            return;
        }
        try {
            writeFileToXML.toSaveToXML();
            System.out.println("Коллекция успешно сохранена в файл.");
        } catch (ParserConfigurationException e) {
            System.out.println("Ошибка при сохранении коллекции: " + e.getMessage());
        }
    }
}
