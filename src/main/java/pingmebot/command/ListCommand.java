package pingmebot.command;

import pingmebot.PingMeException;
import pingmebot.Storage;
import pingmebot.TaskList;
import pingmebot.UI;

/**
 * Represents a command for the user to list all tasks from the task list in the application.
 */
public class ListCommand extends Command {

    /**
     * Executes the command to allow the user to list out the total number of tasks in his/her task list currently.
     *
     * @param tasks The task list containing all the tasks which the user has added.
     * @param storage The local storage holding all the information about the entire task list.
     * @param ui The object responsible for displaying corresponding response to the user.
     * @throws PingMeException If an error occurs during the execution process.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, UI ui) throws PingMeException {
        ui.listText(tasks);
    }
}
