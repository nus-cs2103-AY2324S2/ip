package liv.processor;

import liv.exception.LivException;
import liv.container.TaskList;
import liv.ui.Ui;

/**
 * Represents the command to list the tasks.
 */
public class ListCommand extends Command {
    /**
     * Signals the Ui to respond to the user.
     * @param tasks The list of tasks to operate on.
     * @param ui The Ui to gives interaction with users.
     * @throws LivException
     */
    @Override
    public String execute(TaskList tasks, Ui ui) throws LivException {
        String message = Ui.getListMessage();
        return message;
    }
}
