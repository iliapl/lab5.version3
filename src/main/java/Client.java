import FileDo.Console;
import util.Creatore;

public class Client {
    public static void main(String[] args){
        System.out.println("гоу-гоу");
        Creatore creatore = new Creatore();
        creatore.create();
        Console console = new Console(creatore.comands, creatore.scanner);
        console.start();
    }
}
