package duke;

import java.io.File;

import duke.storage.TaskList;
import duke.ui.Cli;
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
    public static final File SAVE_FILE = new File("data/tasks.json");

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
        this.ui = new Cli();

        // Create data directory (if required)
        file.getParentFile().mkdirs();

        this.taskList = new TaskList(file);
    }

    /**
     * Run the Duke instance
     */
    public void run() {
        this.ui.startUI(this.taskList);
    }

    public static void main(String[] args) {
        new Duke(SAVE_FILE).run();
    }
}
