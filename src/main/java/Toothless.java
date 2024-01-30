import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Toothless {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Toothless(String filepath){
        ui = new Ui();
        storage = new Storage();
        try {
            Command.loadTasks(filepath);
        } catch (FileNotFoundException e){
            System.out.println("Can't Find Task File!");
        } catch (ToothlessException e) {
            System.out.println(e.getMessage());
        }
    }

    public void start() {
        boolean isDone = false;
        ui.showWelcome();
        while(!isDone){
            String input = ui.readCommand();
            ui.showLine();
            Command command = Parser.parseCommand(input);
            String detail = Parser.parseDetail(input);
            isDone = Command.handleCommand(command, detail);
        }
    }

    public static void main(String[] args){
        new Toothless("./data/toothless.txt").start();
    }
}
