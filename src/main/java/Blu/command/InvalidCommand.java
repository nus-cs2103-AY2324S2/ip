package blu.command;

import blu.exception.BluException;
import blu.exception.InvalidCommandException;
import blu.storage.Storage;
import blu.task.TaskList;
import blu.ui.UI;

/**
 * Represents a command that is identified as invalid.
 * This command is used when a user input does not correspond to any valid command.
 */
public class InvalidCommand extends Command {
    private final String invalidCmd;

    /**
     * Constructs an InvalidCommand with the unrecognized command string.
     *
     * @param invalidCmd The string representing the invalid command input.
     */
    public InvalidCommand(String invalidCmd) {
        this.invalidCmd = invalidCmd;
    }

    /**
     * Executes the invalid command.
     * This method throws an InvalidCommandException to indicate that the user input
     * was not recognized as a valid command.
     *
     * @param taskList The TaskList, not used in this command but required by the method signature.
     * @param storage The Storage, not used in this command but required by the method signature.
     * @param ui The UI, not used in this command but required by the method signature.
     * @return The message to be displayed to the user. (Not applicable in InvalidCommand)
     * @throws BluException Throws an InvalidCommandException with the invalid command string.
     */
    @Override
    public String execute(TaskList taskList, Storage storage, UI ui) throws BluException {
        throw new InvalidCommandException(invalidCmd);
    }
}
