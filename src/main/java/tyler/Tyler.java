package tyler;

import tyler.task.TaskList;
import tyler.storage.Storage;
import tyler.ui.Ui;
import tyler.command.Command;
import tyler.parser.Parser;
import tyler.exception.TylerException;

import java.nio.file.Paths;
import java.io.IOException;


/**
 * The main class of Tyler that run Tyler.
 */
public class Tyler {
    private static final String FILE_PATH = Paths.get(".",  "data", "Tyler.txt").toString();
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Tyler(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Run Tyler.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (TylerException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
    public static void main(String[] args) {
        new Tyler(FILE_PATH).run();
    }
}