package chimp.command;
import chimp.core.Storage;
import chimp.core.TaskList;
import chimp.core.Ui;
import chimp.exception.CommandExecuteException;

public abstract class Command {
    /**
     * Executes the command.
     *
     * @param list the task list to be modified
     * @param ui the user interface for displaying messages
     * @param storage the storage for saving and loading tasks
     * @throws CommandExecuteException if there is an error executing the command
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws CommandExecuteException;

    /**
     * Checks if the command is an exit command.
     *
     * @return true if the command is an exit command, false otherwise.
     */
    public abstract boolean isExit();
}