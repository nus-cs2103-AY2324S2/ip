package alpa.commands;

import alpa.exceptions.AlpaException;
import alpa.tasks.Task;
import alpa.tasks.TaskList;
import alpa.ui.Ui;
import alpa.utils.Storage;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand implements Command {
    private int index;

    /**
     * Constructs a MarkCommand object with the specified index.
     *
     * @param index The index of the task to be marked as done.
     */
    public MarkCommand(String index) {
        this.index = Integer.parseInt(index.trim()) - 1;
    }

    /**
     * Executes the mark command by marking the task as done, displaying the task as marked,
     * and saving the updated task list to storage.
     *
     * @param taskList The task list containing the tasks.
     * @param ui The user interface to interact with the user.
     * @param storage The storage to save the updated task list.
     * @throws AlpaException If there is an error executing the command.
     */
    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) throws AlpaException {
        Task task = taskList.markTaskAsDone(index);
        ui.showMarkedAsDone(task);
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
