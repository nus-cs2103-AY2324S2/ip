package chimp.command;

import chimp.core.Storage;
import chimp.core.TaskList;
import chimp.core.Ui;
import chimp.exception.CommandExecuteException;

/**
 * Represents a command to unmark a task in the task list.
 * Inherits from the Command class.
 */
public class UnmarkCommand extends Command {
    private int num;

    /**
     * Constructs a UnmarkCommand object with the specified task number.
     *
     * @param num The number of the task to be unmarked.
     */
    public UnmarkCommand(int num) {
        this.num = num;
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return true if the command is an exit command, false otherwise.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the unmark command, which removes the mark from a task in the task list.
     *
     * @param tasks The task list containing the tasks.
     * @param ui The user interface for displaying messages.
     * @param storage The storage for saving the task list.
     * @return The message to be displayed after executing the command.
     * @throws CommandExecuteException If the number argument is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws CommandExecuteException {
        if (num < 1 || num > tasks.size()) {
            throw new CommandExecuteException("unmark must have number argument");
        }
        tasks.get(num - 1).unmark();
        return ui.say("unmark", tasks.get(num - 1), tasks);
    }
}
