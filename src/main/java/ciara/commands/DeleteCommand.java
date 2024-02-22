package ciara.commands;

import ciara.exceptions.CiaraException;
import ciara.storage.Task;
import ciara.storage.TaskList;
import ciara.ui.Ui;

/**
 * The DeleteCommand class defines a command to delete tasks stored in the Ciara
 * application
 *
 * @author Ryan NgWH
 */
public class DeleteCommand extends Command {
    /**
     * Index of task to delete
     */
    private int index;

    /**
     * Visibility of the task to delete
     */
    private boolean isArchived;

    /**
     * Creates a delete command
     *
     * @param index      Index of task to delete
     * @param isArchived Visibility of the task to delete
     */
    public DeleteCommand(int index, boolean isArchived) {
        super(false);
        this.index = index;
        this.isArchived = isArchived;
    }

    /**
     * Executes the command
     *
     * @param taskList Tasklist used for the command
     *
     * @return String containing the output of the command
     */
    @Override
    public String execute(TaskList taskList) throws CiaraException {
        // Delete the task
        Task deletedTask = taskList.deleteTask(this.index, isArchived);

        return "Noted. I've removed this task:\n"
                + String.format("  %s\n", deletedTask.toString())
                + String.format("Now you have %d tasks in the list.\n", taskList.size());
    }

    /**
     * Executes the delete command
     *
     * @param taskList Tasklist used for the command
     * @param ui       UI used for the command
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws CiaraException {
        String successMessage = this.execute(taskList);

        // Print success message
        System.out.print(successMessage);
    }

    /**
     * Indicates whether some other object is "equal to" this command
     *
     * @param obj Object to be checked against
     *
     * @return True if equal, False otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof DeleteCommand) {
            DeleteCommand command = (DeleteCommand) obj;

            return super.equals(command)
                    && this.index == command.index
                    && this.isArchived == command.isArchived;
        }

        return false;
    }
}
