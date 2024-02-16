package duke.command;

import duke.JamieException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;
/**
 * Represents a command to delete a task from the task list. Ensures that the deletion operation
 * is performed safely and updates the task list in storage accordingly.
 */
public class DeleteTaskCommand extends Command {
    private final int taskNum;

    /**
     * Constructs a DeleteTaskCommand with the specified task number.
     *
     * @param taskNum The number of the task to delete, 1-based index.
     */
    public DeleteTaskCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Executes the command by deleting the specified task from the task list,
     * saving the updated task list to storage, and displaying a message indicating
     * the task has been deleted.
     *
     * @param tasks   The task list.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving the task list.
     * @return A message indicating the task has been deleted.
     * @throws JamieException If the task number is invalid or there is an error while saving the task list.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JamieException {
        if (this.taskNum <= 0 || this.taskNum > tasks.getTasksSize()) {
            throw new JamieException("Task number is out of bounds. Please select a valid task number.");
        }
        Task toDelete = tasks.getTask(this.taskNum - 1); // Assuming getTask accounts for zero-based indexing.
        tasks.deleteTask(this.taskNum - 1);
        try {
            storage.save(tasks);
        } catch (Exception e) { // Assuming storage.save could throw an exception that needs to be caught.
            throw new JamieException("Failed to save the task list after deletion: " + e.getMessage());
        }
        return ui.showDeleteMessage(toDelete, tasks);
    }
}
