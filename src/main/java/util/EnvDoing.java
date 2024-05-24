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

                return System.getenv("VehicleCollection");


    }
}