import ReadFromConsole.Console;
import Utilities.Creatore;

public class Client {
    public static void main(String[] args){
        System.out.println("гоу-гоу");
        Creatore creatore = new Creatore();
        creatore.create();
        Console console = new Console(creatore.executeCommands, creatore.scanner);
        console.start();
    }
}