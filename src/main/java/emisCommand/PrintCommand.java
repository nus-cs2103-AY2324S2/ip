package emisCommand;

import emis.TaskList;
import emis.Storage;

/**
 * The PrintCommand class represents a command to print the list of tasks in the EMIS application.
 * When executed, it displays the list of tasks to the user.
 */
public class PrintCommand extends Command {

    /**
     * Constructs a new PrintCommand object.
     */
    public PrintCommand() {
    }

    /**
     * Executes the print command by displaying the list of tasks to the user.
     *
     * @param tasklist The TaskList object representing the list of tasks.
     * @param storage The Storage object handling loading and saving of tasks.
     * @return A string representing the list of tasks.
     */
    @Override
    public String execute(TaskList tasklist, Storage storage) {
        return tasklist.printList();
    }
}
