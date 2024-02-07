package duke;

import java.io.FileNotFoundException;

import duke.command.Command;
import duke.exception.CommandInvalidException;


/**
 * Main code to run program.
 *
 *
 */
public class Duke {

    private static final String CURRENT_WORKING_DIRECTORY = System.getProperty("user.dir");
    private static final String PATH = "/list.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke
     *
     *
     * @param filePath X path of the saved file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showMessage(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the duke program
     *
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
            } catch (CommandInvalidException e) {
                ui.showMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke(CURRENT_WORKING_DIRECTORY + PATH).run();
    }
}
