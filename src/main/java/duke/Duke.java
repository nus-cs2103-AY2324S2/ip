package duke;

import java.io.File;

import duke.storage.TaskList;
import duke.ui.Gui;
import duke.ui.Ui;

/**
 * The Duke class provides the Duke chatbot application
 *
 * @author Ryan NgWH
 */
public class Duke {
    /**
     * File to save storage to
     */
    public static File saveFile;

    /**
     * Task list for the Duke instance
     */
    private TaskList taskList;

    /**
     * UI of the Duke instance
     */
    private Ui ui;

    /**
     * Create a Duke instance
     *
     * @param file File to save and load tasks
     */
    public Duke(File file) {
        this.ui = new Gui();
        Duke.saveFile = file;

        // Create data directory (if required)
        file.getParentFile().mkdirs();

        this.taskList = new TaskList(Duke.saveFile);
    }

    /**
     * Run the Duke instance
     *
     * @param args Arguments to pass to the application
     */
    public void run(String[] args) {
        assert this.ui != null; // UI should exist
        assert this.taskList != null; // Tasklist should exist

        this.ui.startUI(this.taskList, args);
    }

    public static void main(String[] args) {
        new Duke(new File("data/tasks.json")).run(args);
    }
}
