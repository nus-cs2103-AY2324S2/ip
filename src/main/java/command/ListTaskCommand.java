package command;

import duke.Storage;
import model.TaskList;
import view.TaskListView;

/**
 * Command to list out all tasks in the {@code TaskList}.
 */
public class ListTaskCommand extends Command {
    public static final String COMMAND_WORD = "list";

    private final TaskListView taskListView;

    /**
     * Constructs a {@code ListTaskCommand} object using {@code TaskList}.
     * This command is used to list all tasks in the given task list.
     *
     * @param taskList The task list to be listed .
     */
    public ListTaskCommand(TaskList taskList) {
        this.taskListView = new TaskListView(taskList);
    }

    /**
     * Executes the {@code ListTaskCommand}, prints {@code TaskList}.
     *
     * @param storage The storage object that manages the data persistence. It is not used in {@code ListTaskCommand}
     */
    @Override
    public void execute(Storage storage) {
        taskListView.display();
    }
}
