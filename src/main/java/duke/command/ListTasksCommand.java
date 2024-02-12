package duke.command;

import duke.TaskList;
import duke.Storage;
import duke.Ui;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListTasksCommand extends Command {

    /**
     * Executes the command by displaying the list of tasks using the user interface.
     *
     * @param tasks   The task list.
     * @param ui      The user interface for displaying the list of tasks.
     * @param storage The storage (not used in this command).
     * @return
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}
