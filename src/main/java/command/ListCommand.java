package command;
import sky.Ui;
import task.TaskList;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Lists all tasks in the task list.
     * @param tasks Task list to list all tasks from.
     * @param ui Ui to display the list of tasks to the user.
     * @return List of tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        return ui.showList(tasks.getList());
    }
}
