package command;

import duke.Storage;
import duke.TaskList;
import duke.DukeException;
import duke.Task;

/**
 * Represents a command that marks a task as unfinished
 */
public class UnmarkCommand extends Command {
    private final int num;

    /**
     * Constructs a new unMarkCommand.
     *
     * @param num The index of the task that is to be marked unfinished
     */
    public UnmarkCommand(int num) {
        this.num = num;
    }

    /**
     * Judges if 2 commands are unmarking the same indexed command.
     *
     * @param other Another command
     * @return True if 2 commands are unmarking the same indexed task
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof UnmarkCommand) {
            return ((UnmarkCommand) other).num == num;
        }
        return false;
    }

    /**
     * Marks one task as unfinished. Rewrite the corresponding part in the file.
     *
     * @param storage  Involved in file management
     * @param taskList Active during the execution of the program
     * @throws DukeException
     */
    @Override
    public String execute(Storage storage, TaskList taskList) throws DukeException {
        Task task = taskList.retrieve(num);
        if (!task.isDone()) {
            return "Not done in the first place.";
        } else {
            task.unmark();
            String result = "OK, I've marked this task as not done yet:";
            result += "  " + task + '\n';
            storage.editLine(num, task);
            return result;
        }
    }
}
