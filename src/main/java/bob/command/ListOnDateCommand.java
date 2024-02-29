package bob.command;

import java.time.LocalDate;

import bob.Storage;
import bob.TaskList;
import bob.Ui;

/**
 * Represents an action to list the tasks on a specified day.
 * A <code>ListOnDateCommand</code> object corresponds to a command to list all tasks currently in the task list
 * that is occurring on a specified date.
 */
public class ListOnDateCommand extends ListCommand {
    private final LocalDate date;

    /**
     * Returns a command to list the tasks on a specified day.
     *
     * @param date The date on which the listed tasks are to occur.
     */
    public ListOnDateCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * Executes the command to list the tasks on a specified day.
     *
     * @param storage The storage to retrieve the tasks to list.
     * @param taskList The task list that stores the tasks that might be listed.
     * @return The response containing the list of tasks to be displayed.
     */
    @Override
    public String execute(Storage storage, TaskList taskList) {
        return Ui.getListResponse(taskList.listOnDate(date));
    }
}
