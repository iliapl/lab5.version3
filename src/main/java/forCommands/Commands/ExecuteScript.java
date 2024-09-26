package forCommands.Commands;

import forCommands.Command;
import forCommands.CommandProcessing.ExecuteCommands;

import java.io.*;

public class ExecuteScript implements Command {
    private final ExecuteCommands commands;
    private boolean isExecutingScript = false;  // Флаг выполнения скрипта
    public ExecuteScript(ExecuteCommands commands) {
        this.commands = commands;
    }
    @Override
    public void execute(String argument) {
        File file = new File(argument);
        // Проверяем, выполняется ли уже этот скрипт
        if (isExecutingScript) {
            System.out.println("Скрипт " + file.getName() + " уже выполняется. Пропуск, чтобы избежать рекурсии.");
            return;
        }
        // Устанавливаем флаг выполнения скрипта
        isExecutingScript = true;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            if (line == null) {
                System.out.println("Файл пуст, считывать с файла нечего.");
                return;
            }
            while (line != null) {
                System.out.println("Выполняем команду: " + line);
                commands.executeCommand(line);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден.");
        } catch (IOException e) {
            System.out.println("Ошибка при чтении из файла: " + e.getMessage());
        } finally {
            isExecutingScript = false; // Сбрасываем флаг после выполнения скрипта
        }
    }
}