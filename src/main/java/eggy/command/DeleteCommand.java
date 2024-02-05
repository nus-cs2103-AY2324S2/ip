package eggy.command;

import eggy.exception.EggyException;
import eggy.exception.IncompleteCommandException;
import eggy.exception.SaveTasksException;
import eggy.exception.TaskListIndexOutOfBoundsException;
import eggy.exception.TaskNumberFormatException;
import eggy.storage.Storage;
import eggy.task.TaskList;
import eggy.task.Task;
import eggy.ui.Ui;

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
     * @param commands The array of commands.
     * @param tasksSize The size of the task list.
     * @throws IncompleteCommandException If the command is incomplete.
     * @throws TaskNumberFormatException If the task number format is invalid.
     * @throws TaskListIndexOutOfBoundsException If the task number is out of bounds.
     */
    public DeleteCommand(String[] commands, int tasksSize) throws IncompleteCommandException, TaskNumberFormatException, TaskListIndexOutOfBoundsException {
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
     * @param tasks The task list of the chat bot.
     * @param ui The user interface of the chat bot.
     * @param storage The storage of the chat bot.
     * @throws SaveTasksException If there is an error saving the task list to the storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SaveTasksException {
        Task task = tasks.removeTask(index);
        ui.printTaskRemoved(task, tasks.getSize());
        storage.save(tasks);
    }
}
