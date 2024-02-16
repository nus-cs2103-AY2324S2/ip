package denify.core;

import denify.exception.DenifyException;
import denify.parser.Parser;
import denify.storage.Storage;
import denify.task.TaskList;
import denify.ui.Ui;

/**
 * The `Denify` class represents the main application that manages tasks.
 * It handles user interactions, parses commands, and performs operations on tasks.
 */
public class Denify {
    private static final String DATA_DIRECTORY = "./data/";
    private static final String FILENAME = "denify.txt";
    private final Ui ui;
    private final CommandHandler commandHandler;
    /**
     * Constructs a `Denify` instance with the specified file path for storage.
     */
    public Denify() {
        this.ui = new Ui();
        Storage storage = new Storage(DATA_DIRECTORY + FILENAME);
        TaskList tasks;
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (DenifyException e) {
            ui.displayError(e.getMessage());
            tasks = new TaskList();
        }
        this.commandHandler = new CommandHandler(tasks, ui, storage);
    }
    /**
     * Processes the user input and returns the response.
     *
     * @param msg The user input message.
     * @return The response to the user input.
     */
    public String getResponse(String msg) {
        StringBuilder response = new StringBuilder();
        Parser parser = new Parser(msg);
        try {
            Command command = parser.parseCommand();
            assert command != null : "Command must not be null";
            response.append(commandHandler.execute(command, parser));
        } catch (DenifyException e) {
            response.append(e.getMessage());
        }
        return response.toString();
    }
    /**
     * Initializes the Denify application, greets the user, and starts command processing.
     */
    public void run() {
        String msg = ui.getInput();
        this.getResponse(msg);
    }
    /**
     * Main method to launch the Denify application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        new Denify().run();
    }
}
