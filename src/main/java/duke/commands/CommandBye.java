package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;

/**
 * Represents the "bye" command in the Duke application. This command allows
 * the user to exit the application.
 */
public class CommandBye extends Command {

    /**
     * Constructs a new CommandBye object.
     * This constructor is empty as no specific initialization is needed for the "bye" command.
     */
    public CommandBye() {}

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }
}
