package sam.command;

import sam.SamException;
import sam.Storage;
import sam.TaskList;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    private int index;
    /**
     * Constructs a DeleteCommand with the specified task number to delete.
     *
     * Initializes a DeleteCommand with the provided task number. If the task number is blank,
     * throws a SamException with a message indicating the need to provide the task number to delete.
     * If the provided task number is not a valid integer, throws a SamException with a message
     * indicating the need to provide a valid number.
     *
     * @param taskInfo the task number to delete
     * @throws SamException if the provided task number is blank or not a valid integer
     */
    public DeleteCommand(String taskInfo) throws SamException {
        if (taskInfo.isBlank()) {
            throw new SamException("Please provide the task number to delete.");
        }

        try {
            this.index = Integer.parseInt(taskInfo) - 1;
        } catch (NumberFormatException e) {
            throw new SamException("Please provide a number");
        }
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws SamException {
        String deletedTask = tasks.deleteTask(index);
        storage.save(tasks);
        return String.format("This task has been deleted from the list: \n%s\nNow you have %d tasks left.\n",
                deletedTask, tasks.getNumOfTasks());
    }
}
