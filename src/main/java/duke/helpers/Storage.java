package duke.helpers;

import java.io.FileNotFoundException;

import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Storage class
 */
public class Storage {
    private String filePath;
    private Ui ui;

    /**
     * Constructor of Storage.
     *
     * @param filePath path to the storage.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        ui = new Ui();
    }

    /**
     * Gets memory from hard disk.
     *
     */
    public void getStorageFromHardDisk(TaskList tasks) {
        try {
            FileManaging.readFileContent(filePath, tasks);
        } catch (DukeException e) {
            ui.displayToScreen(e.getMessage());
        } catch (FileNotFoundException e) {
            ui.displayToScreen(e.getMessage());
        }
    }
}
