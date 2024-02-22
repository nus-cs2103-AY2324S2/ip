package alpa.commands;

import alpa.exceptions.AlpaException;
import alpa.tasks.Task;
import alpa.tasks.TaskList;
import alpa.utils.Storage;

/**
 * Represents a command to unmark a task as done.
 */
public class UnmarkCommand implements Command {
    private int index;

    /**
     * Constructs an UnmarkCommand object with the specified index.
     *
     * @param index The index of the task to be unmarked.
     */
    public UnmarkCommand(String index) {
        this.index = Integer.parseInt(index.trim()) - 1;
    }

    /**
     * Executes the unmark command, marking the specified task as not done.
     *
     * @param taskList The task list containing the tasks.
     * @param storage The storage to save the tasks.
     * @return A string representation of the task that was marked as not done.
     * @throws AlpaException If there is an error executing the command.
     */
    @Override
    public String executeCommand(TaskList taskList, Storage storage) throws AlpaException {
        if (index < 0 || index >= taskList.getSize()) {
            throw new AlpaException("Task number out of range.");
        }
        Task task = taskList.markTaskAsNotDone(index);
        storage.saveTasks(taskList.getTasks());
        return String.format("Not done with this yet, human?\n  %s", task);
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return true if the command is an exit command, false otherwise.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
