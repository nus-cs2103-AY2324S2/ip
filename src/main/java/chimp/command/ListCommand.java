package chimp.command;

import chimp.core.Storage;
import chimp.core.TaskList;
import chimp.core.Ui;

/**
 * Represents a command to list all tasks.
 * Extends the Command class.
 */
public class ListCommand extends Command {

    /**
     * Checks if the command is an exit command.
     *
     * @return false, as the list command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the list command.
     *
     * @param tasks The task list.
     * @param ui The user interface.
     * @param storage The storage.
     * @return The list of tasks as a string.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.printList(tasks);
    }

}
