package duke.Command;

import duke.Storage;
import duke.Tasks.TaskList;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the ExitCommand, showing a goodbye message.
     *
     * @param tasks   The list of tasks (not used).
     * @param storage The storage handler (not used).
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        String goodbyeMessage = "Bye. Hope to see you again soon!\n";

        return goodbyeMessage;
    }



    /**
     * Checks if the command is an exit command.
     *
     * @return Always returns true, as this is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
