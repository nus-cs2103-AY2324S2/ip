package ciara.commands;

import ciara.storage.TaskList;
import ciara.ui.Ui;

/**
 * The ExitCommand class defines an exit command that can be used in the Ciara
 * application.
 *
 * @author Ryan NgWH
 */
public class ExitCommand extends Command {
    /**
     * Creates an exit command
     */
    public ExitCommand() {
        super(true);
    }

    /**
     * Executes the command
     *
     * @param taskList Tasklist used for the command
     *
     * @return String containing the output of the command
     */
    @Override
    public String execute(TaskList taskList) {
        // Goodbye message
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Executes the exit command
     *
     * @param taskList Tasklist used for the command
     * @param ui       UI used for the command
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        String successMessage = this.execute(taskList);

        // Print exit message
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

        if (obj instanceof ExitCommand) {
            ExitCommand command = (ExitCommand) obj;

            return super.equals(command);
        }

        return false;
    }
}
