package nicky;

import java.io.IOException;

import nicky.command.Command;
import nicky.task.Parser;
import nicky.task.Storage;
import nicky.task.TaskList;

/**
 * Represents the main class of the Nicky application.
 * This class is responsible for initializing the application, handling user input,
 * and generating responses.
 */
public class Nicky {
    private Storage storage; // The storage handler for loading and saving tasks
    private TaskList tasks; // The list of tasks
    private final Ui ui; // The UI handler for interacting with users

    /**
     * Constructs a new instance of Nicky. Initializes the UI, storage,
     * and attempts to load tasks from the storage file.
     */
    public Nicky() {
        this.ui = new Ui();
        try {
            this.storage = new Storage("./data/nicky.txt");
            this.tasks = storage.loadTasks();
        } catch (IOException e) {
            // If tasks cannot be loaded, initializes an empty TaskList
            tasks = new TaskList();
        }
    }

    /**
     * Retrieves the welcome message to be displayed to the user at the start of the application.
     *
     * @return The welcome message string.
     */
    public String getWelcomeMessage() {
        return ui.showWelcome();
    }

    /**
     * Processes the user input and returns the response from Nicky.
     * This method parses the input, executes the corresponding command, and returns the result.
     * If an exception occurs during the process, it returns an error message.
     *
     * @param input The user input string.
     * @return The response from Nicky based on the user input.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (Exception e) {
            return ui.showError(e.getMessage());
        }
    }
}
