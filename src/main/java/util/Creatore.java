package util;

import FileDo.Comands;
import toVehicle.VehiclesCollecton;
import java.io.*;
import java.util.Scanner;

public class Creatore {
    public Scanner scanner;
    public Comands comands;

    public Creatore() {

    }
    /*
    делаю их публичными
    т.к. это будут те самые поля
    к которым мы будем обращаться в остальных классах
     */
    public void create() {
        scanner = new Scanner(System.in);
        EnvDoing envDoing = new EnvDoing();
        File file = new File(envDoing.getPATHcollection());
        String string = envDoing.getPATHcollection();
        FileInputStream fin;

        {
            try {
                fin = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

         BufferedInputStream bufferedReader = new BufferedInputStream(fin);
        FileOutputStream fileOutputStream;

        {
            try {
                fileOutputStream = new FileOutputStream(string, true);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        PrintWriter printWriter = new PrintWriter(fileOutputStream);
         VehiclesCollecton vehiclesCollecton = new VehiclesCollecton();
        FileRead fileRead = new FileRead(bufferedReader, scanner, file);
        vehiclesCollecton.setVehicles(fileRead.parserXML());

        WriteFileToXML writeFileToXML = new WriteFileToXML(printWriter, vehiclesCollecton);
        comands = new Comands(vehiclesCollecton.getVehicles(), fileRead, writeFileToXML);
    }
}
