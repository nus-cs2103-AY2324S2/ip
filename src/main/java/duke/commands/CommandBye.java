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
     * Indicates that executing this command should result in exiting the Duke application.
     *
     * @return true to indicate an exit command, false otherwise.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Executes the "bye" command, which displays a farewell message to the user.
     *
     * @param tasks   The task list (not used in this command).
     * @param storage The storage component (not used in this command).
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }
}
