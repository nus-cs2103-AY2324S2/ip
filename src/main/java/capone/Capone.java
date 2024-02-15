package capone;

import capone.commands.Command;
import capone.exceptions.CaponeException;
import capone.exceptions.TaskListCorruptedException;
import capone.ui.Ui;

/**
 * The main class responsible for running the application and handling user commands.
 *
 * @author Tay Rui-Jie
 */
public class Capone {
    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    /**
     * Constructs a Capone instance with the specified task list path and name.
     *
     * @param taskListPath The path where the task list is stored.
     * @param taskListName The name of the task list file.
     */
    public Capone(String taskListPath, String taskListName) {
        this.storage = new Storage(taskListPath, taskListName);
        this.tasks = new TaskList();
        this.ui = new Ui();
    }

    /**
     * Constructs a Capone instance with the default task list path and name.
     */
    public Capone() {
        this.storage = new Storage(Storage.STORAGE_PATH, Storage.STORAGE_FILE);
        this.tasks = new TaskList();
        this.ui = new Ui();
    }

    /**
     * Runs the instance, displaying a welcome message and reading tasks from storage.
     * Continuously processes user commands in a loop till user exits.
     */
    public void runCli() {
        this.ui.printWelcomeMsg();

        try {
            this.storage.readTasksFromJsonFile(this.tasks);
        } catch (TaskListCorruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Runs the instance, displaying a welcome message and reading tasks from storage.
     * Continuously processes user commands in a loop till user exits.
     */
    public void runGui() {
        try {
            this.storage.readTasksFromJsonFile(this.tasks);
        } catch (TaskListCorruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * The main method to start the Capone application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Capone().runCli();
    }

    /**
     * Gets the response from processing the user's input.
     *
     * @param input The user's input.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.processInputs(input);
            return command.execute(tasks, ui, storage);
        } catch (CaponeException e) {
            return e.getMessage();
        }
    }

    /**
     * Gets the ui object of this Capone instance.
     *
     * @return The ui instance associated with this Capone instance.
     */
    public Ui getUi() {
        return this.ui;
    }
}
