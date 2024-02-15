package Luke;

/**
 * Represents the main class of the Luke program.
 * <p>
 * Luke is a task management application that allows users to manage their tasks
 * through a command-line interface.
 * </p>
 */
public class Luke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructs a Luke object with the given file path.
     * <p>
     * Initializes the storage, task list, and user interface components.
     * </p>
     *
     * @param filePath the file path for storing tasks
     */
    public Luke(String filePath) {
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadFile());
            ui = new Ui(taskList);
        } catch (LukeException e) {
            taskList = new TaskList();
            ui = new Ui();
            ui.showLoadingError();
        }
    }

    /**
     * Runs the Luke program.
     * <p>
     * Displays a welcome message, handles user input, saves tasks to file,
     * and displays a goodbye message.
     * </p>
     */
    public void run() {
        ui.welcome();
        ui.handleInput();
        storage.saveFile(taskList);
        ui.end();
    }

    /**
     * The main method of the Luke program.
     * <p>
     * Creates a new instance of Luke with the default file path and runs the program.
     * </p>
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        new Luke("data/tasks.txt").run();
    }

}
