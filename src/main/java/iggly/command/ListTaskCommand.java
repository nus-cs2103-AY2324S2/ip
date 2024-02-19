package iggly.command;

import iggly.duke.Storage;
import iggly.model.TaskList;
import iggly.view.TaskListView;

/**
 * Command to list out all tasks in the {@code TaskList}.
 */
public class ListTaskCommand extends Command {
    public static final String COMMAND_WORD = "list";

    private final TaskListView taskListView;

    /**
     * Constructs a {@link ListTaskCommand} object using {@link TaskList}.
     * This command is used to list all tasks in the given task list.
     *
     * @param taskList The task list to be listed .
     */
    public ListTaskCommand(TaskList taskList) {
        this.taskListView = new TaskListView(taskList);
    }

    /**
     * Executes the {@link ListTaskCommand}, prints {@link TaskList}.
     * <p>
     * This method display the task list using the task list view.
     *
     * @param storage The storage object that manages the data persistence. It is not used in {@link ListTaskCommand}
     */
    @Override
    public String execute(Storage storage) {
        return taskListView.display();
    }
}
