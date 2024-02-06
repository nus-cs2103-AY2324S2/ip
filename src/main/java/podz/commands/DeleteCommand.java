package podz.commands;

import podz.exceptions.PodzException;
import podz.task.Task;
import podz.ui.Ui;

/**
 * Represents a command to delete a task from the task manager.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a DeleteCommand object with the specified task index.
     *
     * @param index the index of the task to be deleted
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command to remove a task from the task manager.
     *
     * @param ui the user interface that interacts with the user
     * @throws PodzException if the task index is invalid
     */
    @Override
    public void execute(Ui ui) throws PodzException {
        if (this.index < 0 || this.index >= super.taskList.getSize()) {
            throw new PodzException("Oh no!!! Invalid task index!");
        }
        Task taskDeleted = super.taskList.getTask(this.index);
        super.taskList.deleteTask(this.index);
        ui.printToUser("\tNoted. I've removed this task:",
                        "\t" + taskDeleted,
                        super.taskList.getListCounter());
        
    }    
}