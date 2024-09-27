package Utilities;

import ReadFromConsole.ConsoleReader;
import forCommands.CommandProcessing.CommandManager;
import forCommands.CommandProcessing.ExecuteCommands;
import forFile.FileRead;
import forFile.WriteFileToXML;
import forVehicles.VehiclesCollecton;

import java.io.*;
import java.util.Scanner;

public class Creatore {
    public Scanner scanner;
    public ExecuteCommands executeCommands;
    public VehiclesCollecton vehiclesCollecton;

    public void create() {
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
        FileRead fileRead = new FileRead(bufferedReader, file);
        vehiclesCollecton = new VehiclesCollecton(fileRead.parserXML());
        WriteFileToXML writeFileToXML = new WriteFileToXML(printWriter, vehiclesCollecton);
        CommandManager commandManager = new CommandManager(vehiclesCollecton, consoleReader, writeFileToXML, envDoing);
        executeCommands = new ExecuteCommands(commandManager);
    }
}