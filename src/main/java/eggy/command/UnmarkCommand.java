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
 * Represents a command to mark a task as not done.
 */
public class UnmarkCommand extends Command {
    /** The index of the task to be unmarked. */
    private int index;

    /**
     * Constructs an UnmarkCommand.
     *
     * @param commands The array of commands.
     * @param tasksSize The size of the task list.
     * @throws IncompleteCommandException If the command is incomplete.
     * @throws TaskNumberFormatException If the task number is not a number.
     * @throws TaskListIndexOutOfBoundsException If the task number is out of bounds.
     */
    public UnmarkCommand(String[] commands, int tasksSize)
            throws IncompleteCommandException, TaskNumberFormatException, TaskListIndexOutOfBoundsException {
        if (commands.length < 2) {
            throw new IncompleteCommandException("unmark");
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
     * Marks a task as not done from the task list and saves the task list to the storage.
     *
     * @param tasks The task list of the chatbot.
     * @param ui The user interface of the chatbot.
     * @param storage The storage of the chatbot.
     * @throws SaveTasksException If there is an error saving the task list to the storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SaveTasksException {
        Task task = tasks.getTask(index);
        task.unmarkDone();
        ui.printTaskUnmarkedDone(task);
        storage.save(tasks);
    }
}
