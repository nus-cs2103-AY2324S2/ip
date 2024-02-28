package ciara.commands;

import ciara.exceptions.CiaraException;
import ciara.storage.Task;
import ciara.storage.TaskList;
import ciara.ui.Ui;

/**
 * The MarkCommand class defines a command to mark tasks stored in the Ciara
 * application as completed/uncompleted
 *
 * @author Ryan NgWH
 */
public class MarkCommand extends Command {
    /**
     * Index of task to mark
     */
    private int index;

    /**
     * Status of task
     */
    private boolean isCompleted;

    /**
     * Creates a mark command
     *
     * @param index       Index of task to mark
     * @param isCompleted Status of the task
     */
    public MarkCommand(int index, boolean isCompleted) {
        super(false);
        this.index = index;
        this.isCompleted = isCompleted;
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
        // Mark task as completed/uncomplete
        Task markedTask = taskList.markTask(this.index, this.isCompleted);
        // Print success message
        String response;
        if (isCompleted) {
            response = "Nice! I've marked this task as done:\n";
        } else {
            response = "Nice! I've marked this task as not done:\n";
        }
        return response + String.format("  %s", markedTask.toString());
    }

    /**
     * Executes the mark command
     *
     * @param taskList Tasklist used for the command
     * @param ui       UI used for the command
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws CiaraException {
        String successMessage = this.execute(taskList);

        // Print tasks
        System.out.println(successMessage);
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

        if (obj instanceof MarkCommand) {
            MarkCommand command = (MarkCommand) obj;

            return super.equals(command)
                    && this.index == command.index
                    && this.isCompleted == command.isCompleted;
        }

        return false;
    }
}
