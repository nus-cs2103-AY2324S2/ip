package duchess;

import duchess.task.Task;
import java.util.ArrayList;

/**
 * Duchess class represents the main class of the Duchess program.
 * It initializes the necessary components and runs the program.
 */
public class Duchess {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;


    private static final String FILE_PATH = "./data/duchess.txt";

    /**
     * Constructs a Duchess object.
     * Initializes storage, task list, and user interface components.
     *
     * @throws DuchessException if an error occurs during initialization
     */
    public Duchess() throws DuchessException {
        storage = new Storage(FILE_PATH);
        taskList = new TaskList();
        ArrayList<Task> tasksStored = storage.loadData();
        if (!tasksStored.isEmpty()) {
            taskList = new TaskList(storage.loadData());
        }
        ui = new Ui();
    }

    /**
     * Main method to start the Duchess program.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        try {
            new Duchess().run();
        } catch (DuchessException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Runs the Duchess program.
     * Displays opening greeting, interacts with user, and handles exceptions.
     */
    public void run() {
        ui.printOpeningGreeting();
        try {
            ui.printEcho(taskList, storage);
        } catch (DuchessException e) {
            System.out.println(e.getMessage());
        } finally {
            //Close scanner
            ui.closeScanner();
        }
    }


}
