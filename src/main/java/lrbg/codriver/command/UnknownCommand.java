package lrbg.codriver.command;

import lrbg.codriver.data.exception.CoDriverException;
import lrbg.codriver.data.TaskList;
import lrbg.codriver.storage.Storage;
import lrbg.codriver.ui.Ui;

/**
 * Represents a command that is not recognised by the CoDriver.
 */
public class UnknownCommand extends Command {
    /** The unknown command that was entered. */
    private final String unknownCommand;

    /**
     * Creates a new UnknownCommand with the given unknown command.
     * @param unknownCommand The unknown command that was entered.
     */
    public UnknownCommand(String unknownCommand) {
        this.unknownCommand = unknownCommand;
    }

    /**
     * {@inheritDoc}
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws CoDriverException {
        throw new CoDriverException("I'm sorry, but I don't understand this command: " + unknownCommand);
    }

    /**
     * Returns true if the given object is equal to this command, only for testing purposes.
     * @param obj The object to compare to.
     * @return True if the given object contains the same unknown command, false otherwise.
     */
    public boolean testEquals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof UnknownCommand) {
            UnknownCommand other = (UnknownCommand) obj;
            return other.unknownCommand.equals(this.unknownCommand);
        } else {
            return false;
        }
    }
}
