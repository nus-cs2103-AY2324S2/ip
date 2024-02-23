package podz.commands;

import podz.exceptions.PodzException;
import podz.task.Task;

/**
 * Represents a command to delete a task from the task manager.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a DeleteCommand object with the specified task index.
     *
     * @param index the index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command to remove a task from the task manager.
     *
     * @throws PodzException if the task index is invalid.
     */
    @Override
    public String execute() throws PodzException {
        if (this.index < 0 || this.index >= super.taskList.getSize()) {
            throw new PodzException("Oh no!!! Invalid task index!");
        }
        Task taskDeleted = super.taskList.getTask(this.index);
        super.taskList.deleteTask(this.index);
        super.response = "Noted. I've removed this task:\n"
                        + taskDeleted + "\n"
                        + super.taskList.getListCounter();
        return super.response;
    }    
}
