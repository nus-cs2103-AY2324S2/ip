package micromanager.commands;

import micromanager.exceptions.DukeException;
import micromanager.storage.Storage;
import micromanager.storage.TaskList;
import micromanager.tasks.Task;

/**
 * DeleteCommand class represents a command to delete a task from the task list.
 * It extends the Command class and provides methods to execute the command.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a DeleteCommand object with the specified index of the task to delete.
     *
     * @param index The index of the task to delete.
     */
    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    /**
     * Executes the delete command by removing the task at the specified index from the task list.
     *
     * @param taskList The list of tasks.
     * @param storage  The storage handler.
     * @throws DukeException If an error occurs during command execution.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        if (taskList.size() == 0) {
            throw new DukeException("OOPS!!! There are no tasks to delete.");
        }
        Task removed = taskList.remove(index);

        return "Noted. I've removed this task\n"
                + "  " + removed + "\n"
                + String.format("Now you have %d tasks in the list.%n", taskList.size());
    }
}
