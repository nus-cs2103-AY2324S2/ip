package saopig.command;

import saopig.Storage;
import saopig.Ui;
import saopig.task.TaskList;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand extends Command {
    /**
     * Executes the command to exit the program.
     *
     * @param tasks   The task list.
     * @param ui      The user interface.
     * @param storage The storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

    }

    /**
     * Returns true if the command is an exit command.
     * Program should exit after return.
     *
     * @return true.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
