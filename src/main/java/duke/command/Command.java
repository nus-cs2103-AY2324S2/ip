package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Base class for all Command types.
 *
 * @author KohGuanZeh
 */
public abstract class Command {
    /**
     * Runs given command based on command type.
     *
     * @param taskList TaskList to execute command on.
     * @param ui UI to output feedback.
     * @param storage Storage that saves respective data.
     * @throws IOException Exception on failure in writing data to saved data.
     * @throws CommandException Exception if Command cannot run due to unexpected input.
     */
    public abstract void run(TaskList taskList, Ui ui, Storage storage) throws IOException, CommandException;

    public boolean isExit() {
        return false;
    }
}
