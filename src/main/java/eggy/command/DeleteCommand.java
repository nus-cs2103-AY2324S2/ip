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
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    /**
     * The index of the task to be deleted.
     */
    private int index;

    /**
     * Constructs a DeleteCommand.
     *
     * @param tasksSize The size of the task list.
     * @param commands The array of commands.
     * @throws IncompleteCommandException If the command is incomplete.
     * @throws TaskNumberFormatException If the task number format is invalid.
     * @throws TaskListIndexOutOfBoundsException If the task number is out of bounds.
     */
    public DeleteCommand(int tasksSize, String... commands)
            throws IncompleteCommandException, TaskNumberFormatException, TaskListIndexOutOfBoundsException {
        if (commands.length < 2) {
            throw new IncompleteCommandException("delete");
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
     * Deletes a task from the task list and saves the task list to the storage.
     *
     * @param tasks The task list of the chatbot.
     * @param response The response of the chatbot.
     * @param storage The storage of the chatbot.
     * @throws SaveTasksException If there is an error saving the task list to the storage.
     */
    @Override
    public void execute(TaskList tasks, Response response, Storage storage) throws SaveTasksException {
        Task task = tasks.removeTask(index);
        response.setTaskRemovedResponse(task, tasks.getSize());
        storage.save(tasks);
    }
}
