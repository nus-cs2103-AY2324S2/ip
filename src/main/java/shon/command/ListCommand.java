package shon.command;

import shon.TaskList;
import shon.Ui;

/**
 * Represents a command to list the tasks in the <code>TaskList</code>.
 */
public class ListCommand extends Command {
    /**
     * List the tasks in the tasks <code>TaskList</code> in a user-friendly format.
     *
     * @param tasks The <code>TaskList</code> containing the tasks of the user.
     * @param ui The <code>Ui</code> used to output the result of the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.print(tasks.getList());
    }
}
