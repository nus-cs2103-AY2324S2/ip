package commands;

import utils.TaskList;

/**
 * Represents an error command that handles error responses.
 */
public class ErrorCommand extends Command {
    /**
     * Constructs an ErrorCommand object.
     *
     * @param response The error message.
     */
    public ErrorCommand(String response) {
        super(response);
    }

    @Override
    public void execute(TaskList taskList) {
        // No specific action required for error command.
    }
}
