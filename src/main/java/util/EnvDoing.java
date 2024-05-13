package util;

import FileDo.Filewas;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class EnvDoing {
    Scanner scanner = new Scanner(System.in);
    public String getPATHcollection() {
        System.out.println("Введите переменную окружения");
        String name = scanner.nextLine();
        if (System.getenv(name) == null ){
            System.out.println("Нет такой переменной");
            System.out.println("Повторите попытку");
            return getPATHcollection();
        }
        else {
            Path path = Paths.get(System.getenv(name));
            try {
                if(String.valueOf(path.getFileName()).equals("cmd.exe") ||  Files.isSymbolicLink(path) || Files.isHidden(path)){
                    System.out.println("Так нельзя");
                    return getPATHcollection();
                }
            } catch (IOException e) {
                System.out.println("Не содержит путь");
                return getPATHcollection();
            }
            File file = new File(System.getenv(name));
            Filewas filewas = new Filewas();
            if(filewas.canFile(file)){
                return file.getAbsolutePath();
            }
            else {
                System.out.println("Автовозращение переменной");
                return System.getenv("VehicleCollection");
            }
        }
    }
}