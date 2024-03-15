package lrbg.codriver.command;

import lrbg.codriver.CoDriver;
import lrbg.codriver.data.TaskList;
import lrbg.codriver.storage.Storage;
import lrbg.codriver.ui.Ui;

/**
 * Represents a command to exit the program.
 */
public class ByeCommand extends Command {
    /**
     * Creates a new ByeCommand.
     */
    public ByeCommand() {
    }

    /**
     * {@inheritDoc}
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return CoDriver.GOODBYE_MESSAGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Returns true if the given object is equal to this command, only for testing purposes.
     * @param obj The object to compare to.
     * @return True if the given object is an instance of ByeCommand, false otherwise.
     */
    public boolean testEquals(Object obj) {
        return obj instanceof ByeCommand;
    }
}
