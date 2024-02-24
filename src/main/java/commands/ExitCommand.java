package commands;

import utils.Response;
import utils.TaskList;

/**
 * The ExitCommand class represents a command to exit the application.
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit command.
     *
     * @param taskList The task list object.
     */
    @Override
    public void execute(TaskList taskList) {
        setResponse(Response.getGoodByeResponse()); // Display farewell message to the user
        setIsExit(); // Set this command as an exit command
    }
}
