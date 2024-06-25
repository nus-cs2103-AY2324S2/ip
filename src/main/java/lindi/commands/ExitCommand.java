package lindi.commands;

import lindi.storage.Storage;
import lindi.task.TaskList;

/**
 * Represents a command that exits the program upon execution
 */
public class ExitCommand extends Command {
    public static final String COMMAND_EXIT = "bye";

    @Override
    public void execute(TaskList tasks, Storage storage) {
        this.statusMsg = "Bye. Hope to see you again soon!";
    }

    /**
     * Returns true. Will exit main program loop.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
