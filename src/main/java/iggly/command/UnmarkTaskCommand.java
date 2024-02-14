package iggly.command;

import iggly.duke.Storage;
import iggly.model.Task;
import iggly.model.TaskList;
import iggly.view.UnmarkTaskView;

/**
 * Command to mark a {@link Task} as not done.
 */
public class UnmarkTaskCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    private final Task task;
    private final TaskList taskList;

    /**
     * Constructs a {@link UnmarkTaskCommand} object with the specified {@link Task} index and {@link TaskList}.
     * This command is used to mark a task as not done from the given task list.
     *
     * @param task The task to be marked as not done.
     * @param taskList The task list to which the task will be marked.
     */
    public UnmarkTaskCommand(Task task, TaskList taskList) {
        this.task = task;
        this.taskList = taskList;
    }

    /**
     * Executes the {@link UnmarkTaskCommand}, performing the necessary actions to carry out the specific functionality
     * related to marking a task.
     * <p>
     * This method marks the given task as not done from the task list, update the storage and display the unmark task
     * message by initialising the unmark task view.
     *
     * @param storage The storage object that manages the data persistence. It is used to update
     *                and save changes after executing the command.
     */
    @Override
    public String execute(Storage storage) {
        this.task.unmark();
        storage.updateFile(taskList.getTaskList());
        UnmarkTaskView unmarkTaskView = new UnmarkTaskView(this.task);
        return unmarkTaskView.display();
    }
}
