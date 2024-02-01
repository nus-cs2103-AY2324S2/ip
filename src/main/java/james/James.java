package james;

import james.commands.Command;
import james.exception.DukeException;
import james.parser.Parser;
import james.storage.Storage;
import james.tasklist.TaskList;
import james.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the main class of the Duke application.
 */
public class James {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private static final String FILE_PATH = "./data/hardDisk.txt";

    /**
     * Creates a new James with the given file path.
     *
     * @param filePath File path to store the tasks in.
     */
    public James(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Runs the James application.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new James(FILE_PATH).run();
    }
}
