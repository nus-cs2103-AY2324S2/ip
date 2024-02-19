package iggly.command;

import iggly.duke.Storage;
import iggly.model.Task;
import iggly.model.TaskList;
import iggly.view.MarkTaskView;

/**
 * Command to mark a {@link Task} as done.
 */
public class MarkTaskCommand extends Command {
    public static final String COMMAND_WORD = "mark";

    private final Task task;
    private final TaskList taskList;

    /**
     * Constructs a {@link MarkTaskCommand} object with the specified {@link Task} index and {@link TaskList}.
     * This command is used to mark a task as done from the given task list.
     *
     * @param task The task to be marked as done.
     * @param taskList The task list to which the task will be marked.
     */
    public MarkTaskCommand(Task task, TaskList taskList) {
        this.task = task;
        this.taskList = taskList;
    }

    /**
     * Executes the {@link MarkTaskCommand}, performing the necessary actions to carry out the specific functionality
     * related to marking a task.
     * <p>
     * This method marks the given task as done from the task list, update the storage and display the mark task
     * message by initialising the mark task view.
     *
     * @param storage The storage object that manages the data persistence. It is used to update
     *                and save changes after executing the command.
     */
    @Override
    public String execute(Storage storage) {
        this.task.mark();
        storage.updateFile(taskList.getTaskList());
        MarkTaskView markTaskView = new MarkTaskView(this.task);
        return markTaskView.display();
    }
}
