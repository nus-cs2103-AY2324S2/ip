package jade.commands;

import jade.data.TaskList;
import jade.storage.Storage;
/**
 * The <code>InvalidCommand</code> object represents an invalid command.
 */
public class InvalidCommand extends Command {
    private String invalidMessage = "Input is invalid, please retry.";
    /**
     * The empty class constructor.
     */
    public InvalidCommand() {}
    /**
     * Class constructor specifying the JadeException.
     */
    public InvalidCommand(String exceptionMessage) {
        this.invalidMessage = exceptionMessage;
    }
    /**
     * {@inheritDoc}
     * Prints an error message to user
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return invalidMessage;
    }

    /**
     * {@inheritDoc}
     * Indicates that the program is not exiting.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
