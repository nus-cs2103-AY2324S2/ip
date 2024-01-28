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
     * @param tasklist Tasklist used for the command
     * @param ui       UI used for the command
     */
    @Override
    public void execute(TaskList tasklist, Ui ui) {
        // Goodbye message
        String goodbye = "Bye. Hope to see you again soon!";

        // Print exit message
        System.out.println(goodbye);
    }
}
