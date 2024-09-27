package forFile;

import java.io.File;
public class FileExist {
    public boolean canReadFile(File file)  {
        if (file.exists()) {
            if (file.isDirectory() ) {
                System.out.println("Файл не может быть считан. Причина: файл является папкой");
                return false;
            } else {
                System.out.println("Файл считан");
                if (file.length() == 0) {
                    System.out.println("Файл пуст");
                    return false;
                } else {
                    return true;
                }
            }
        } else {
            System.out.println("Файл не может быть считан, ввиду его отрицательного существования");
            return false;
        }
    }
}