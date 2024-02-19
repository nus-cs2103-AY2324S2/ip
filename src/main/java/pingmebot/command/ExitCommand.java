package pingmebot.command;

import pingmebot.PingMeException;
import pingmebot.Storage;
import pingmebot.TaskList;
import pingmebot.UI;

/**
 * Represents a command for the user to exit from the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the command to say goodbye to the user when he/she decides to end the conversation.
     *
     * @param tasks The task list containing all the tasks which the user has added.
     * @param storage The local storage holding all the information about the entire task list.
     * @param ui The object responsible for displaying corresponding response to the user.
     * @throws PingMeException If an error occurs during the execution process.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, UI ui) throws PingMeException {
        ui.sayGoodbye();
    }
}
