import java.io.FileNotFoundException;

import commands.Command;
import exception.UncleBobException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Main class representing the Duke application.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Duke object with the specified file path.
     *
     * @param filePath the file path for loading and saving tasks
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = storage.loadFile();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    /**
     * Runs the Duke application.
     */
    public void run() {
        ui.showGreetingMessage();
        boolean isExiting = false;
        while (!isExiting) {
            try {
                String inputs = ui.getUserCommand();
                Command c = Parser.parse(inputs);
                c.execute(tasks, storage, ui);
                isExiting = Command.isExit(c);
            } catch (UncleBobException e) {
                ui.showErrorMessage(e.getMessage());
            }
        }
        ui.showExitMessage();
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
