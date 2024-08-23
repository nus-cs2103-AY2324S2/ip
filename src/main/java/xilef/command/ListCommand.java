package xilef.command;

import xilef.Storage;
import xilef.Ui;
import xilef.task.Task;
import xilef.task.TaskList;

/**
 * Lists all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Constructs a new {@code ListCommand}.
     *
     * Lists all tasks in the task list.
     */
    public ListCommand() {
    }

    /**
     * {@inheritDoc}
     *
     * Lists all tasks in the task list.
     * Displays a message to the user if there are no tasks in the list.
     *
     * @return A string showing the current tasks in the task list and their status.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showList(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
