package alpa.commands;

import alpa.exceptions.AlpaException;
import alpa.tasks.Task;
import alpa.tasks.TaskList;
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
     * @param storage The storage to save the updated task list.
     *
     * @throws AlpaException If there is an error executing the command.
     */
    @Override
    public String executeCommand(TaskList taskList, Storage storage) throws AlpaException {
        if (index < 0 || index >= taskList.getSize()) {
            throw new AlpaException("Task number out of range.");
        }
        Task task = taskList.markTaskAsDone(index);
        storage.saveTasks(taskList.getTasks());
        return "Marked as done, human!\n" + "  " + task;
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
