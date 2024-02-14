package iggly.command;

import iggly.duke.Storage;
import iggly.model.Task;
import iggly.model.TaskList;
import iggly.view.DeleteTaskView;

/**
 * Command to delete a {@link Task} from the {@link TaskList} using the task index.
 */
public class DeleteTaskCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private final Task task;
    private final TaskList taskList;

    /**
     * Constructs a {@link DeleteTaskCommand} object with the specified {@link Task} index and {@link TaskList}.
     * This command is used to delete a task from the given task list.
     *
     * @param index The index of the task to be deleted.
     * @param taskList The task list to which the task will be deleted.
     */
    public DeleteTaskCommand(int index, TaskList taskList) {
        this.task = taskList.get(index);
        this.taskList = taskList;
    }

    /**
     * Executes the {@link DeleteTaskCommand}, performing the necessary actions to carry out the specific functionality
     * related to deleting a task.
     * <p>
     * This method deletes the given task index from the task list, update the storage and display the delete task
     * message by initialising the delete task view.
     *
     * @param storage The storage object that manages the data persistence. It is used to update
     *                and save changes after executing the command.
     */
    @Override
    public String execute(Storage storage) {
        taskList.remove(task);
        storage.updateFile(taskList.getTaskList());
        DeleteTaskView deleteTaskView = new DeleteTaskView(this.task, this.taskList);
        return deleteTaskView.display();
    }
}
