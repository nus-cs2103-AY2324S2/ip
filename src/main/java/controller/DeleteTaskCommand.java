package controller;

import duke.Storage;
import model.Task;
import model.TaskList;
import view.DeleteTaskView;

/**
 * Command to delete a {@code Task} from the {@code TaskList} using the task index.
 */
public class DeleteTaskCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private final Task task;
    private final TaskList taskList;

    /**
     * Constructs a {@code DeleteTaskCommand} object with the specified {@code Task} index and {@code TaskList}.
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
     * Executes the {@code DeleteTaskCommand}, performing the necessary actions to carry out the specific functionality
     * related to deleting a task.
     *
     * @param storage The storage object that manages the data persistence. It is used to update
     *                and save changes after executing the command.
     */
    @Override
    public void execute(Storage storage) {
        taskList.remove(task);
        storage.update(taskList.getTaskList());
        DeleteTaskView deleteTaskView = new DeleteTaskView(this.task, this.taskList);
        deleteTaskView.display();
    }
}
