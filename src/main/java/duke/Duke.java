package duke;

import java.nio.file.Path;
import java.nio.file.Paths;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Duke is the main class for the Duke chat-bot that manages tasks for users.
 * It integrates components for UI, storage, and command execution.
 */
public class Duke {
    /**
     * The default file path used for storing tasks data.
     */
    public static final Path TASKS_FILE_PATH = Paths.get(".", "data", "duke.tasks");

    /**
     * The delimiter used for separating command arguments in the storage file.
     */
    public static final String ARG_DELIMITER = "\u241f";
    /**
     * The storage field used to load and save tasks data.
     */
    private final Storage storage;
    /**
     * The tasks field used to store tasks.
     */
    private TaskList tasks;
    /**
     * The ui field used to interact visually with the user.
     */
    private final Ui ui;

    /**
     * Constructs a new Duke object.
     * Initializes the user interface, storage, and task list components.
     * Attempts to load existing tasks from the storage; if unsuccessful, starts with an empty task list.
     *
     * @param filePath The path to the file where tasks data is loaded from and saved to.
     * @param argDelimiter The delimiter used in the tasks data file for parsing command arguments.
     */
    public Duke(Path filePath, String argDelimiter) {
        this.ui = new Ui();
        this.storage = new Storage(filePath, argDelimiter);

        try {
            this.tasks = new TaskList(this.storage.loadTasks());
        } catch (DukeException dukeException) {
            this.ui.showError(dukeException.getMessage());
            this.tasks = new TaskList();
        }
    }

    /**
     * Executes the main application loop.
     * Displays a welcome message, then continuously reads and processes user commands
     * until an exit command is received.
     */
    private void run() {
        ui.showWelcome();

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (DukeException dukeException) {
                ui.showError(dukeException.getMessage());
            }
        }
    }

    /**
     * The entry point of the Duke application.
     * Initializes the application and starts the interaction loop.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Duke duke = new Duke(TASKS_FILE_PATH, ARG_DELIMITER);
        duke.run();
    }
}
