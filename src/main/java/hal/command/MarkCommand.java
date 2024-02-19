package hal.command;

import hal.task.TaskList;

/**
 * The MarkCommand class represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private int taskIndex;

    /**
     * Constructs a new MarkCommand object.
     *
     * @param taskIndex The index of the task to be marked as done.
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the mark command.
     *
     * @param taskList The TaskList containing the tasks.
     * @return A message indicating the result of the mark operation.
     */
    @Override
    public String execute(TaskList taskList) {
        return taskList.markAsDone(taskIndex);
    }
}
