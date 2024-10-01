package forCommands.Commands;

import forCommands.Command;
import forFile.WriteFileToXML;
import lombok.AllArgsConstructor;

import javax.xml.parsers.ParserConfigurationException;

import static Utilities.EnvDoing.getPATHcollection;

@AllArgsConstructor
public class Save implements Command {
    private final WriteFileToXML writeFileToXML;

    @Override
    public void execute(String argument) {
        try {
            writeFileToXML.toSaveToXML();
            System.out.println("Коллекция успешно сохранена в файл.");
        } catch (ParserConfigurationException e) {
            System.out.println("Ошибка при сохранении коллекции: " + e.getMessage());
        }
    }
}
