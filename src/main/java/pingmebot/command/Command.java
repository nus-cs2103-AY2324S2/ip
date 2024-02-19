package pingmebot.command;

import pingmebot.PingMeException;
import pingmebot.Storage;
import pingmebot.TaskList;
import pingmebot.UI;

/**
 * Represents an abstract base class for all command objects in the application.
 */
public abstract class Command {

    /**
     * Executes the command to conduct specific actions given by the user, updating the task list, local storage.
     * This command will also generate the corresponding response to the user via the ui.
     *
     * @param tasks The task list containing all the tasks which the user has added.
     * @param storage The local storage holding all the information about the entire task list.
     * @param ui The object responsible for displaying corresponding response to the user.
     * @throws PingMeException
     */
    public abstract void execute(TaskList tasks, Storage storage, UI ui) throws PingMeException;
}
