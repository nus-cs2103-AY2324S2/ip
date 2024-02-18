package commands;

import storage.Storage;
import task.Task;
import task.TaskList;

/**
 * Represents the command used to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private static final String SUCCESS_MESSAGE = "Uncle deleted this item:\n\t %s"
            + "\n Now you have %s task(s) in the list.";
    private final String message;

    /**
     * Creates a new DeleteCommand object with the provided message.
     *
     * @param message Input message containing index to delete.
     */
    public DeleteCommand(String message) {
        this.message = message;
    }

    /**
     * Executes the DeleteCommand, deleting a task from the task list based on the input index.
     * If the input index is out of range of the task list, an IndexOutOfBoundsException is thrown.
     * @param tasks   The TaskList representing the collection of tasks.
     * @param storage The Storage object handling storage operations.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            int index = Integer.parseInt(message);
            Task removed = tasks.get(index - 1);
            tasks.remove(index - 1);
            return String.format(SUCCESS_MESSAGE, removed, tasks.numTasks());
        } catch (IndexOutOfBoundsException e) {
            return e.getMessage();
        }
    }
}
