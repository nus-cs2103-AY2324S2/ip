package emisCommand;

import emis.TaskList;
import emis.Storage;

/**
 * The ExitCommand class represents a command to exit the EMIS application.
 * When executed, it terminates the application.
 */
public class ExitCommand extends Command {

    /**
     * Constructs a new ExitCommand object.
     */
    public ExitCommand() {
    }

    /**
     * Executes the exit command by terminating the application.
     *
     * @param tasklist The TaskList object representing the list of tasks.
     * @param ui The Ui object handling interactions with the user.
     * @param storage The Storage object handling loading and saving of tasks.
     */
    @Override
    public String execute(TaskList tasklist, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }
}
