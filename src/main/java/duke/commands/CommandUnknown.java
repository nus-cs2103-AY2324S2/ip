package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;

/**
 * Represents the command for handling unknown or invalid commands in the Duke application.
 */
public class CommandUnknown extends Command {
    /**
     * Constructs a new CommandUnknown object.
     */
    public CommandUnknown() {}

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "OOPS!!! I don't understand that command, try again later.";
    }
}
