package command;
import duke.Ui;
import task.TaskList;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Lists all tasks in the task list.
     * @param tasks Task list to list the tasks from.
     * @param ui Ui to display the tasks to the user.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.showList(tasks.getList());
    }
}
