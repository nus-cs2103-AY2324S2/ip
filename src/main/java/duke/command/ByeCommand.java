package duke.command;

import duke.task.TaskList;
import duke.util.*;
import duke.exception.SaveStorageException;

/**
 * The class representing the bye command.
 * */
public class ByeCommand extends Command {
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            System.out.println("Bye. Hope to see you again soon!");
            storage.save(taskList);
        } catch (SaveStorageException e) {
            ui.showError(e.getMessage());
        }
    }

    public boolean isExit() {
        return true;
    }
}
