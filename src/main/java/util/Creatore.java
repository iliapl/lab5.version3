package util;

import FileDo.Comands;
import toVehicle.VehiclesCollecton;

import java.io.*;
import java.util.Scanner;

public class Creatore {
    public Scanner scanner;
    public Comands comands;
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
        FileRead fileRead = new FileRead(bufferedReader, scanner, file);
        vehiclesCollecton = new VehiclesCollecton(fileRead.parserXML());
        WriteFileToXML writeFileToXML = new WriteFileToXML(printWriter, vehiclesCollecton);
        comands = new Comands(vehiclesCollecton, fileRead, writeFileToXML, file);
    }
}
