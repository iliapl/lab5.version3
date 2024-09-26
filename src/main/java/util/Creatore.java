package util;

import ReadFromConsole.ConsoleReader;
import forCommands.CommandManager;
import forCommands.ExecuteCommands;
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

/*
scanner = new Scanner(System.in);
        EnvDoing envDoing = new EnvDoing();
        File file = new File(envDoing.getPATHcollection());
        String string = file.getAbsolutePath();
        FileInputStream fin;
        try {
            fin = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        BufferedInputStream bufferedReader = new BufferedInputStream(fin);
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(string, true);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        PrintWriter printWriter = new PrintWriter(fileOutputStream);
        ConsoleReader consoleReader = new ConsoleReader(scanner);
        FileRead fileRead = new FileRead(bufferedReader, scanner, file);
        vehiclesCollecton = new VehiclesCollecton(fileRead.parserXML());
        CommandManager commands = new CommandManager(vehiclesCollecton, consoleReader);
        WriteFileToXML writeFileToXML = new WriteFileToXML(printWriter, vehiclesCollecton);
        executeCommands = new ExecuteCommands(commands);
 */