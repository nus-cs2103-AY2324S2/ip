package podz.commands;

import podz.exceptions.PodzException;

/**
 * Represents a command to unmark a task in the task manager.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs an UnmarkCommand object with the specified task index.
     *
     * @param index the index of the task to be unmarked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the unmark command to unmark a task.
     *
     * @throws PodzException if the task index is invalid.
     */
    @Override
    public String execute() throws PodzException {
        if (index < 0 || index >= super.taskList.getSize()) {
            throw new PodzException("Oh no!!! Invalid task index!");
        }
        super.taskList.unmarkTask(index);
        super.response = "OK, I've marked this task as not done yet: "
                        + super.taskList.getTask(index);
        return super.response;
    }
}
