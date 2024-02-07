package duke.gui;

import duke.task.Storage;
import duke.task.TaskList;

import java.io.FileNotFoundException;

/**
 * The Main class initializes and runs the Duke application.
 */
public class Main {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private String filePath;

    /**
     * Constructs a Main object with the specified file path.
     *
     * @param filePath The file path to the list storage file.
     */
    public Main(String filePath) {
        System.out.println("main run");
        this.filePath = filePath;
        taskList = new TaskList();
        ui = new Ui(taskList);
        storage = new Storage(filePath, taskList);
        try {
            taskList.initialisePrevTaskList(storage.readFromFile());
        } catch (FileNotFoundException e) {
            // ui.showLoadingError();
            System.out.println(e.getMessage());
        }
    }

    /**
     * Starts the Duke application.
     */
    public void run() {
        // tasks added to tasklist already
        ui.start();

        // then we store it
        storage.writeToFile(filePath);
    }

    /**
     * The main method to run the Duke application.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        new Main("data/hal.txt").run();
    }
}
