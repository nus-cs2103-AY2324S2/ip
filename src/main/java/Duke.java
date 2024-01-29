import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
public class Duke {
    private Storage storage; //deals with loading and saving
    private TaskList tasks; //operations to add and delete
    private final Ui ui; //deals with interactions from user

    public Duke(String filePath) { //creating a Duke object
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public void run() {
        ui.greet();
        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser(scanner, tasks, storage);
        parser.read();
    }

    public static void main(String[] args) {
        Duke bearducky = new Duke("./data/tasks.txt");
        bearducky.run();
    }

}
