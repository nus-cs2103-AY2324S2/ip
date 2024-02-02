package duke.Command;

import duke.Storage;
import duke.Tasks.TaskList;
import duke.Ui;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {


    /**
     * Executes the ListCommand, displaying the list of tasks.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage handler.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }


    /**
     * Checks if the command is an exit command.
     *
     * @return Always returns false, as this is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

