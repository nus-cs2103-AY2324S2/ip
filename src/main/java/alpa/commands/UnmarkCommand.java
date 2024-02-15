package alpa.commands;

import alpa.exceptions.AlpaException;
import alpa.utils.Storage;
import alpa.tasks.Task;
import alpa.tasks.TaskList;
import alpa.ui.Ui;

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
     * @param ui The user interface to interact with the user.
     * @param storage The storage to save the tasks.
     * @throws AlpaException If there is an error executing the command.
     */
    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) throws AlpaException {
        Task task = taskList.markTaskAsNotDone(index);
        ui.showMarkedAsNotDone(task);
        storage.saveTasks(taskList.getTasks());
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
