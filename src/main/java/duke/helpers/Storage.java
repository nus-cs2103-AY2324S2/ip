package duke.helpers;

import duke.command.CommandType;
import duke.exception.DukeException;
import duke.task.TaskList;

import java.io.FileNotFoundException;

public class Storage {
    private String filePath;
    private Ui ui;

    public Storage(String filePath) {
        this.filePath = filePath;
        ui = new Ui();
    }

    public void getStorageFromHardDisk(TaskList tasks) {
        try {
            FileManaging.readFileContent(CommandType.FILEPATH.toString(), tasks);
        } catch (DukeException e) {
            ui.displayToScreen(e.getMessage());
        } catch (FileNotFoundException e) {
            ui.displayToScreen(e.getMessage());
        }
    }
}
