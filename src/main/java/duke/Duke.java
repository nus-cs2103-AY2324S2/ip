package duke;

import java.io.File;

import duke.storage.TaskList;
import duke.ui.Cli;
import duke.ui.Ui;

public class Duke {
    /**
     * File to save storage to
     */
    public static final File SAVE_FILE = new File("data/tasks.json");

    public static void main(String[] args) {
        // Create data directory (if required)
        SAVE_FILE.getParentFile().mkdirs();

        TaskList taskList;
        // Initialise stored tasks
        taskList = new TaskList(SAVE_FILE);

        // Print CLI UI
        Ui ui = new Cli();
        ui.startUI(taskList);
    }
}
