package duke;

import java.nio.file.Paths;

/**
 * The main class for the Duke application.
 * Initializes the application's UI, storage, and task list components.
 */
public class Duke {
    // Path to the directory where task data is stored
    protected static final String DATA_DIRECTORY = Paths.get("src", "main", "java", "data").toString();
    // Path to the file within DATA_DIRECTORY where tasks are saved
    protected static final String DATA_FILE = Paths.get(DATA_DIRECTORY, "tasks.txt").toString();
    
    private Storage storage; // Component for loading and saving tasks
    private TaskList tasks; // Component managing the list of tasks
    private Ui ui; // Component handling user interactions

    /**
     * Constructs a new Duke application instance.
     * Initializes the task list, storage, and UI components.
     */
    public Duke() {
        this.tasks = new TaskList();
        this.storage = new Storage(tasks);
        this.ui = new Ui("URSA", this.storage, this.tasks);
    }

    /**
     * Starts the Duke application.
     * Launches the UI to begin interaction with the user.
     */
    public void run() {
        this.ui.start();
    }

    /**
     * The entry point of the Duke application.
     * Creates an instance of the Duke application and starts it.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
