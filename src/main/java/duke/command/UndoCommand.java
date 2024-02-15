package duke.command;
import java.io.IOException;

import duke.helpers.FileManaging;
import duke.helpers.Storage;
import duke.helpers.Ui;
import duke.task.TaskList;


/**
 * Undo Command Class.
 */
public class UndoCommand extends Command {

    @Override
    public String getExecutionMessage(TaskList tasks, Ui ui, Storage storage, Storage secondaryStorage) {
        try {
            FileManaging.writeToFile(CommandType.SECONDARYFILEPATH.toString(),
                    CommandType.FILEPATH.toString(), tasks);
            storage.getStorageFromHardDisk(tasks);
            return "Undo successfully!";
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
