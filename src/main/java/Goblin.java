import java.util.Scanner;
import java.util.ArrayList;

public class Goblin {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public static ArrayList<Task> actions = new ArrayList<>();
    public Goblin(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readFile());
        } catch (OrkException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        Ui.sayHello();
        boolean working = true;
        while(working) {
            try {
            String command = ui.readCommand();
            Ui.line();;
            Command c = Parser.parse(command);
            c.execute(tasks, ui, storage);
            working = c.isWorking();
            } catch (OrkException e) {
                ui.printException(e);
            } finally {
                Ui.line();
            }
        }
    }

    public static void main(String[] args) {
        new Goblin("src/main/java/data.txt").run();
    }
}
