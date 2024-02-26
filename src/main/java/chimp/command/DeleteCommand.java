package chimp.command;

import chimp.task.Task;
import chimp.core.Storage;
import chimp.core.TaskList;
import chimp.core.Ui;
import chimp.exception.CommandExecuteException;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    int index;

    /**
     * Constructs a DeleteCommand object with the specified index.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return False, as this command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the delete command.
     *
     * @param tasks   The task list.
     * @param ui      The user interface.
     * @param storage The storage.
     * @return The message to be displayed after executing the command.
     * @throws CommandExecuteException If an error occurs while executing the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws CommandExecuteException {
        assert tasks != null : "tasks should not be null";

        if (index - 1 < 0 || index - 1 >= tasks.size()) {
            throw new CommandExecuteException("Invalid index argument provided to delete");
        }
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
        return ui.say("delete", task, tasks);
    }
}
