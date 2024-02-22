package command;

import duke.Storage;
import duke.TaskList;
import duke.DukeException;
import duke.Task;

/**
 * Represents a command that marks a task as finished
 */
public class MarkCommand extends Command {
    private final int num;

    /**
     * Constructs a new MarkCommand.
     *
     * @param num The index of the task that is to be marked finished
     */
    public MarkCommand(int num) {
        this.num = num;
    }

    /**
     * Judges if 2 commands are marking the same indexed command.
     *
     * @param other Another command
     * @return True if 2 commands are marking the same indexed task
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof MarkCommand) {
            return ((MarkCommand) other).num == num;
        }
        return false;
    }

    /**
     * Marks one task as done. Rewrite the corresponding part in the file.
     *
     * @param storage Involved in file management
     * @param taskList Active during the execution of the program
     * @return A string that tells that the task has been marked
     * @throws DukeException
     */
    @Override
    public String execute(Storage storage, TaskList taskList) throws DukeException {
        Task task = taskList.retrieve(num);
        if (task.isDone()) {
            return "Already done. No need to mark again.\n";
        } else {
            task.mark();
            String result = "Nice! I've marked this task as done:\n";
            result += "  " + task + '\n';
            storage.editLine(num, task);
            return result;
        }
    }
}
