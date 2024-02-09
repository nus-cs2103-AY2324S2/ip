package duke;

import duke.commands.Command;
import duke.dataprocessing.CommandParser;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.ui.Ui;

/**
 * Duke is a task management application that allows users to manage their tasks through a command-line interface.
 * Users can add, delete, mark as done, and list tasks. Tasks are saved to a file for persistence between sessions.
 * Duke also provides error handling and a user-friendly interface.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructs a Duke object with the specified file path for task storage.
     *
     * @param filePath The file path for storing and loading tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Starts the Duke application.
     * Displays a welcome message and processes user commands until the application exits.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = CommandParser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                storage.update(taskList);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Main method to start the Duke application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
