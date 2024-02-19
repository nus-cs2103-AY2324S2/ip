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
    protected static final String name = "URSA";

    private Storage storage; // Component for loading and saving tasks
    private TaskList tasks; // Component managing the list of tasks
    protected Ui ui; // Component handling user interactions

    /**
     * Constructs a new Duke application instance.
     * Initializes the task list, storage, and UI components.
     */
    public Duke() {
        this.tasks = new TaskList();
        this.storage = new Storage(tasks);
        this.ui = new Ui(this.storage, this.tasks);
        this.storage.loadTasks();
    }
}
