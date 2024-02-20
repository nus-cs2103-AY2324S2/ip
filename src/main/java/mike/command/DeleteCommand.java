package mike.command;

import mike.MikeException;
import mike.Storage;
import mike.TaskList;
import mike.task.Task;

/**
 * Deletes a task.
 * @author ningc
 */
public class DeleteCommand extends Command {
    private final int taskNumber;

    /**
     * Constructor.
     * @param taskNumber The number referring to the task.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws MikeException {
        int taskIndex = taskNumber - 1;

        if (taskList.isEmpty()) {
            throw new MikeException("There are no tasks to remove.");
        } else if (taskIndex >= taskList.size() || taskIndex < 0) {
            throw new MikeException("That task doesn't exist. Please enter a number in the range 1-"
                    + taskList.size() + ".");
        }

        Task task = taskList.get(taskIndex);
        taskList.remove(taskIndex);

        return response(taskList, task);
    }

    private String response(TaskList taskList, Task deletedTask) {
        return "Noted! I've removed this task:\n  "
                + deletedTask + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.";
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return "DELETE TASK " + taskNumber;
    }

}
