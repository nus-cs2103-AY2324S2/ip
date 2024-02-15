package duke;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The main class for the Duke application. Duke is a simple task management
 * application that allows users to add, delete, mark as done, and list tasks.
 * Tasks are stored in a file specified by the user upon starting the application.
 * Duke provides a command-line interface (CLI) for users to interact with.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    /**
     * Constructs a Duke object with the specified file path.
     *
     * @param filePath The file path where tasks will be stored.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }


    /**
     * Runs the Duke application.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String userInput = ui.readCommand();
                Parser.parse(userInput, tasks, ui);
                storage.save(tasks.getTasks());
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                ui.showError("Oops! Something went wrong: " + e.getMessage());
            }
        }
    }

    /**
     * Main method to start the Duke application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
