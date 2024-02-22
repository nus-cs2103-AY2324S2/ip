package command;

import duke.Storage;
import duke.TaskList;
import duke.DukeException;
import duke.Task;

/**
 * Represents a command that deletes a task
 */
public class DeleteCommand extends Command {
    private final int num;

    /**
     * Constructs a new DeleteCommand.
     *
     * @param num The index of the task that is to be deleted
     */
    public DeleteCommand(int num) {
        this.num = num;
    }

    /**
     * Judges if 2 commands are deleting the same index in the taskList.
     * @param other Another command
     * @return True if the indices are the same
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof DeleteCommand) {
            return ((DeleteCommand) other).num == num;
        }
        return false;
    }

    /**
     * Deletes a task. Deletes the task from the file and the task list.
     *
     * @param storage Involved in file management
     * @param taskList Active during the execution of the program
     * @return A string that tells that task removal is successful
     * @throws DukeException
     */
    @Override
    public String execute(Storage storage, TaskList taskList) throws DukeException {
        Task task = taskList.remove(num);
        String result = "Noted. I've removed this task:\n";
        result += "  " + task + "\n";
        result += taskList.countSize();
        storage.deleteLine(num);
        return result;
    }
}
