package duke.command;

import duke.TaskList;

/**
 * A command that represents a no-operation.
 */
public class NothingCommand extends Command {

    /**
     * Execute nothing..
     *
     * @param tasks   The list of tasks.
     * @return Nothing.
     */
    @Override
    public String execute(TaskList tasks) {
        assert tasks != null;
        return "";
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof NothingCommand;
    }
}
