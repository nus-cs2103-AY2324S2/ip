package duke.application;
import duke.io.Storage;
import duke.io.Ui;
import duke.io.MainWindow;

/**
 * The Duke class represents the main application that manages user interactions
 * for a task management system.
 */
public class Duke {
    private TaskList taskList;
    private Ui ui;
    private Parser parser;
    private MainWindow mainWindow;

    /**
     * Constructs a Duke instance, initializing the user interface, task list, and parser.
     * Creates the necessary storage folder and loads existing tasks from storage.
     */
    public Duke() {
        ui = new Ui();
        taskList = new TaskList();
        parser = new Parser();
        //Storage.createFolder();
        Storage.loadFile(taskList.getTaskList());
        //mainWindow.showLoadedTasks();
    }

}
