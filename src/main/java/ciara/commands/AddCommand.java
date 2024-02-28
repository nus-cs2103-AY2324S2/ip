package ciara.commands;

import ciara.exceptions.CiaraException;
import ciara.storage.Task;
import ciara.storage.TaskList;
import ciara.ui.Ui;

/**
 * The AddCommand class defines a command to add tasks to be stored in the Ciara
 * application
 *
 * @author Ryan NgWH
 */
public class AddCommand extends Command {
    /**
     * Task to be added
     */
    private Task task;

    /**
     * Creates a add command
     *
     * @param task Task to be added
     */
    public AddCommand(Task task) {
        super(false);
        this.task = task;
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
        // Add task
        taskList.addTask(task, false);

        return "Got it. I've added this task:\n"
                + String.format("  %s\n", task.toString())
                + String.format("Now you have %d tasks in the list.\n", taskList.size());
    }

    /**
     * Executes the add command
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

        if (obj instanceof AddCommand) {
            AddCommand command = (AddCommand) obj;

            return super.equals(command) && this.task.equals(command.task);
        }

        return false;
    }
}
