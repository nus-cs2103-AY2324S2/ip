package goblin;

import goblin.task.Task;
import goblin.command.Command;
import goblin.exception.OrkException;
import java.util.ArrayList;

public class Goblin {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Create a Goblin object.
     * Initiate ui, storage and taskList.
     *
     * @param filePath the local path to the storage file.
     */
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

    /**
     * Collect user input
     * parse them while the app is working
     */
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

    /**
     * the main method
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        new Goblin("src/main/java/goblin/data.txt").run();
    }
}
