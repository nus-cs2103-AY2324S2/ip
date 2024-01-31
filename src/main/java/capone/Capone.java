package capone;

import capone.commands.Command;
import capone.exceptions.CaponeException;
import capone.exceptions.TaskListCorruptedException;

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
     * Runs the instance, displaying a welcome message and reading tasks from storage.
     * Continuously processes user commands in a loop till user exits.
     */
    public void run() {
        this.ui.printWelcomeMsg();

        try {
            this.storage.readTasksFromJsonFile(this.tasks);
        } catch (TaskListCorruptedException e) {
            System.out.println(e.getMessage());
        }

        while (true) {
            Command command = Parser.processInputs();

            try {
                command.execute(tasks, ui, storage);
            } catch (CaponeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * The main method to start the Capone application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Capone("./data/", "tasks.json").run();
    }
}
