package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * A command that represents a no-operation.
 */
public class NothingCommand extends Command {

    /**
     * Execute nothing..
     * @param tasks The list of tasks.
     * @param ui UI interface with the user.
     * @param storage Storage interface for persistence.
     * @return Nothing.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "";
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof NothingCommand;
    }
}
