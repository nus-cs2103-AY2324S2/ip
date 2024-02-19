package arc.commands;

import arc.storage.Storage;
import arc.tasks.TaskList;

/**
 * Represents the command for handling unknown or invalid commands in the Arc application.
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
