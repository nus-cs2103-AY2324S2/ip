package duke.command;

import duke.Storage;
import duke.TaskList;

/**
 * A command that represents a no-operation.
 */
public class NothingCommand extends Command {

    /**
     * Execute nothing..
     *
     * @param tasks   The list of tasks.
     * @param storage Storage interface for persistence.
     * @return Nothing.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        assert tasks != null;
        return "";
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof NothingCommand;
    }
}
