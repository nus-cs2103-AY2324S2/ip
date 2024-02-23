package podz.commands;

import podz.exceptions.PodzException;

/**
 * Represents a command to mark a task as completed in the task manager.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructs a MarkCommand object with the specified task index.
     *
     * @param index the index of the task to be marked.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the mark command to mark a task as completed.
     *
     * @throws PodzException if the task index is invalid.
     */
    @Override
    public String execute() throws PodzException {
        if (index < 0 || index >= super.taskList.getSize()) {
            throw new PodzException("Oh no!!! Invalid task index!");
        }
        super.taskList.markTask(index);
        super.response = "Great job! I've marked the task as complete. Keep up the good work!\n"
                        + super.taskList.getTask(index);
        return super.response;
    }
}
