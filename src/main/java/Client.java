import ReadFromConsole.Console;
import Utilities.Creator;

public class Client {
    public static void main(String[] args){
        System.out.println("гоу-гоу");
        Creator creator = new Creator();
        creator.create();
        Console console = new Console(creator.scanner, creator.executeCommands);
        console.start();
    }
}