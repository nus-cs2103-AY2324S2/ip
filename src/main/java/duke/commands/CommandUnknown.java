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
     * Executes the command to handle unknown or invalid commands and displays an error message to the user.
     *
     * @param tasks   The task list (not used in this command).
     * @param storage The storage component (not used in this command).
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "OOPS!!! I don't understand that command, try again later.";
    }
}
