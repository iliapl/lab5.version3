package Utilities;

import ReadFromConsole.ConsoleReader;
import forCommands.CommandProcessing.CommandManager;
import forCommands.CommandProcessing.ExecuteCommands;
import forFile.FileRead;
import forFile.WriteFileToXML;
import forVehicles.VehiclesCollection;

import java.io.*;
import java.util.Scanner;

import static Utilities.EnvDoing.getPATHcollection;

public class Creator {
    public Scanner scanner;
    public ExecuteCommands executeCommands;
    public VehiclesCollection vehiclesCollection;

    public void create() {
        scanner = new Scanner(System.in);
        File file = new File(getPATHcollection());
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
        vehiclesCollection = new VehiclesCollection(fileRead.parserXML());
        WriteFileToXML writeFileToXML = new WriteFileToXML(printWriter, vehiclesCollection);
        CommandManager commandManager = new CommandManager(vehiclesCollection, consoleReader, writeFileToXML);
        executeCommands = new ExecuteCommands(commandManager);
    }
}