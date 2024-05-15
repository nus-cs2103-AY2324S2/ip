package chimp.command;

import chimp.core.Storage;
import chimp.core.TaskList;
import chimp.core.Ui;
import chimp.exception.CommandExecuteException;
import chimp.task.Task;

/**
 * Represents a command to find tasks that match a given keyword.
 * Inherits from the Command class.
 */
public class FindCommand extends Command {
    private String arg;

    /**
     * Constructs a FindCommand object with the specified keyword.
     * @param arg The keyword to search for.
     */
    public FindCommand(String arg) {
        this.arg = arg;
    }

    /**
     * Executes the FindCommand by searching for tasks that match the keyword.
     * @param tasks The TaskList containing all the tasks.
     * @param ui The Ui object for user interaction.
     * @param storage The Storage object for data storage.
     * @return A string representation of the matching tasks.
     * @throws CommandExecuteException If there is an error executing the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws CommandExecuteException {
        Task[] matches = tasks.find(arg);
        return ui.say("find", matches, tasks);
    }

    /**
     * Checks if the FindCommand is an exit command.
     * @return Always returns false, as FindCommand is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
