package command;

import duke.Storage;
import model.Task;
import model.TaskList;
import view.MarkTaskView;

/**
 * Command to mark a {@code Task} as done.
 */
public class MarkTaskCommand extends Command {
    public static final String COMMAND_WORD = "mark";

    private final Task task;
    private final TaskList taskList;

    /**
     * Constructs a {@code MarkTaskCommand} object with the specified {@code Task} index and {@code TaskList}.
     * This command is used to mark a task as done from the given task list.
     *
     * @param index The index of the task to be marked as done.
     * @param taskList The task list to which the task will be marked.
     */
    public MarkTaskCommand(int index, TaskList taskList) {
        this.task = taskList.get(index);
        this.taskList = taskList;
    }

    /**
     * Executes the {@code MarkTaskCommand}, performing the necessary actions to carry out the specific functionality
     * related to marking a task.
     *
     * @param storage The storage object that manages the data persistence. It is used to update
     *                and save changes after executing the command.
     */
    @Override
    public void execute(Storage storage) {
        this.task.mark();
        storage.update(taskList.getTaskList());
        MarkTaskView markTaskView = new MarkTaskView(this.task);
        markTaskView.display();
    }
}
