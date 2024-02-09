package eggy.command;

import eggy.exception.EggyException;
import eggy.exception.IncompleteCommandException;
import eggy.exception.TaskListIndexOutOfBoundsException;
import eggy.exception.TaskNumberFormatException;
import eggy.storage.Storage;
import eggy.task.Task;
import eggy.task.TaskList;
import eggy.ui.Ui;

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
     * @param commands The array of commands.
     * @param tasksSize The size of the task list.
     * @throws IncompleteCommandException If the command is incomplete.
     * @throws TaskNumberFormatException If the task number is not a number.
     * @throws TaskListIndexOutOfBoundsException If the task number is out of bounds.
     */
    public MarkCommand(String[] commands, int tasksSize)
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

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EggyException {
        Task task = tasks.getTask(index);
        task.markDone();
        ui.printTaskMarkedDone(task);
        storage.save(tasks);
    }
}
