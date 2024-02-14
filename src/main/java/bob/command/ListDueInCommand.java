package bob.command;

import bob.Storage;
import bob.TaskList;
import bob.Ui;

/**
 * Represents an action to list the tasks due in a specified number of days.
 * A <code>ListDueInCommand</code> object corresponds to a command to list all tasks currently in the task list
 * that is due in a specified number of days.
 */
public class ListDueInCommand extends ListCommand {
    private final int days;

    /**
     * Returns a command to list the tasks due in a specified number of days.
     *
     * @param days The number of days in which the listed tasks are due in.
     */
    public ListDueInCommand(int days) {
        this.days = days;
    }

    /**
     * Executes the command to list the tasks due in a specified number of days.
     *
     * @param ui The UI to display the list.
     * @param storage The storage to retrieve the tasks to list.
     * @param taskList The task list that stores the tasks that might be listed.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        ui.showList(taskList.listDueIn(days));
    }
}
