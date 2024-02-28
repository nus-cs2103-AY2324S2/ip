package chimp.command;

import chimp.core.Storage;
import chimp.core.TaskList;
import chimp.core.Ui;
import chimp.exception.CommandExecuteException;

/**
 * Represents a command to mark a task as completed.
 */
public class MarkCommand extends Command {
    private int num;

    /**
     * Constructs a MarkCommand object with the specified task number.
     *
     * @param num The task number to mark as completed.
     */
    public MarkCommand(int num) {
        this.num = num;
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return False, as MarkCommand is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the MarkCommand, marking the specified task as completed.
     *
     * @param tasks   The task list.
     * @param ui      The user interface.
     * @param storage The storage.
     * @return A message indicating the successful marking of the task.
     * @throws CommandExecuteException If the task number is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws CommandExecuteException {
        if (num < 1 || num > tasks.size()) {
            throw new CommandExecuteException("mark must have number argument");
        }
        tasks.get(num - 1).mark();
        return ui.say("mark", tasks.get(num - 1), tasks);
    }
}
