package forCOmmands.Commands;

import forCOmmands.Command;
import util.FileRead;

public class Exit implements Command {
    @Override
    public void execute(String argument){
        System.out.println("Завершение программы...");
        System.exit(0);
    }
}