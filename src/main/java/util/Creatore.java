package util;

import ReadFromConsole.ConsoleReader;
import forCommands.CommandProcessing.CommandManager;
import forCommands.CommandProcessing.ExecuteCommands;
import forFile.FileRead;
import forVehicles.VehiclesCollecton;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Creatore {
    public Scanner scanner;
    public ExecuteCommands executeCommands;
    public VehiclesCollecton vehiclesCollecton;

    public void create() {
        scanner = new Scanner(System.in);

        // получаем путь
        EnvDoing envDoing = new EnvDoing();
        File file = new File(envDoing.getPATHcollection());

        // Используем try-with-resources для автоматического закрытия потоков
        try (BufferedInputStream bufferedReader = new BufferedInputStream(new FileInputStream(file))) {

            // Инициализация всех необходимых объектов
            ConsoleReader consoleReader = new ConsoleReader(scanner);
            FileRead fileRead = new FileRead(bufferedReader, scanner, file);
            vehiclesCollecton = new VehiclesCollecton(fileRead.parserXML());
            CommandManager commands = new CommandManager(vehiclesCollecton, consoleReader);
            executeCommands = new ExecuteCommands(commands);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при работе с файлами", e);
        }
    }
}