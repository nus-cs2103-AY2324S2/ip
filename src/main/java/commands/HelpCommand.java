package commands;

import utils.Response;
import utils.TaskList;

/**
 * Command class representing the "help" command.
 * This command provides users with a quick reference guide on the available commands,
 * their descriptions, and usage examples within the application.
 */
public class HelpCommand extends Command {

    /**
     * Constructs a HelpCommand object.
     */
    public HelpCommand() {
    }

    /**
     * Executes the help command.
     * Sets the response to the help message retrieved from Response utility class.
     */
    @Override
    public void execute(TaskList taskList) {
        setResponse(Response.getHelpMessage());
    }
}
