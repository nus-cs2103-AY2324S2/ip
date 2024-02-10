package eggy.command;

import eggy.exception.IncompleteCommandException;
import eggy.exception.SaveTasksException;
import eggy.exception.TaskListIndexOutOfBoundsException;
import eggy.exception.TaskNumberFormatException;
import eggy.response.Response;
import eggy.storage.Storage;
import eggy.task.Task;
import eggy.task.TaskList;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    /**
     * The index of the task to be marked as done.
     */
    private int index;

    /**
     * Constructs a MarkCommand.
     *
     * @param tasksSize The size of the task list.
     * @param commands The array of commands.
     * @throws IncompleteCommandException If the command is incomplete.
     * @throws TaskNumberFormatException If the task number is not a number.
     * @throws TaskListIndexOutOfBoundsException If the task number is out of bounds.
     */
    public MarkCommand(int tasksSize, String... commands)
            throws IncompleteCommandException, TaskNumberFormatException, TaskListIndexOutOfBoundsException {
        if (commands.length < 2) {
            throw new IncompleteCommandException("mark");
        }
        try {
            int taskNumber = Integer.parseInt(commands[1]);
            if (taskNumber < 1 || taskNumber > tasksSize) {
                throw new TaskListIndexOutOfBoundsException(taskNumber, tasksSize);
            }
            this.index = taskNumber - 1;
        } catch (NumberFormatException e) {
            throw new TaskNumberFormatException();
        }
    }

    /**
     * Marks a task as done from the task list and saves the task list to the storage.
     *
     * @param tasks The task list of the chatbot.
     * @param response The response of the chatbot.
     * @param storage The storage of the chatbot.
     * @throws SaveTasksException If there is an error saving the task list to the storage.
     */
    @Override
    public void execute(TaskList tasks, Response response, Storage storage) throws SaveTasksException {
        Task task = tasks.getTask(index);
        task.markDone();
        response.setTaskMarkedDoneResponse(task);
        storage.save(tasks);
    }
}
