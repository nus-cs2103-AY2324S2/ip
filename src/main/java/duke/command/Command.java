package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.TaskListException;

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
     * @param storage  Storage that saves respective data.
     * @return Response after command has finished running.
     * @throws IOException Exception on failure in writing data to saved data.
     * @throws CommandException Exception if Command cannot run due to unexpected input.
     */
    public abstract String run(TaskList taskList, Storage storage) throws IOException, CommandException,
            TaskListException;

    public boolean isExit() {
        return false;
    }
}
