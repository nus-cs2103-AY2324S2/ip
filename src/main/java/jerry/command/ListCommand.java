package jerry.command;

import jerry.TaskList;
import jerry.Ui;

/**
 * Represents a command to list all current tasks in the task list.
 * <p>
 * This command displays all tasks, along with their completion status and relevant details, to the user.
 */
public class ListCommand extends Command {
    /**
     * Constructs a {@code ListCommand} with the necessary UI and task list context.
     *
     * @param ui    The UI component for interacting with the user.
     * @param tasks The task list to be displayed.
     */
    public ListCommand(Ui ui, TaskList tasks) {
        super(ui, tasks);
        assert tasks != null : "TaskList should not be null";
    }

    /**
     * Executes the process of displaying all tasks in the task list to the user.
     */
    @Override
    public String execute() {
        return ui.showList(tasks);
    }
}
