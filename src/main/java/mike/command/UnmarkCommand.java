package mike.command;

import mike.MikeException;
import mike.TaskList;
import mike.Ui;
import mike.command.Command;
import mike.task.Task;

/**
 * Command to unmark a task.
 */
public class UnmarkCommand extends Command {
    private final int taskNumber;

    /**
     * Constructor.
     * @param taskNumber The number indexing the task.
     */
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Unmark a task.
     * @param taskList The list of tasks.
     * @throws MikeException If the task number is not present.
     */
    @Override
    public void execute(TaskList taskList) throws MikeException {
        int taskIndex = taskNumber - 1;
        if (taskList.isEmpty()) {
            throw new MikeException("There are no tasks to mark. Please add a task first.");
        } else if (taskIndex >= taskList.size() || taskIndex < 0) {
            throw new MikeException("That's suspicious. Please enter a number in the range 1-" + taskList.size() + ".");
        }
        Task task = taskList.get(taskIndex);
        task.markAsNotDone();
        String message = "I've marked this task as not done:\n  " + task;
        Ui.display(message);
        /*
           TODO:
            1. Check that task is or is not done.
        */
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
