package duke.commands;

import duke.storage.TaskList;
import duke.ui.Ui;

/**
 * The ExitCommand class defines an exit command that can be used in the Duke
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
     * Executes the exit command
     *
     * @param taskList Tasklist used for the command
     * @param ui       UI used for the command
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        // Goodbye message
        String goodbye = "Bye. Hope to see you again soon!";

        // Print exit message
        System.out.println(goodbye);
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
