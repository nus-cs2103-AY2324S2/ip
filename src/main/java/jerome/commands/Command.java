package jerome.commands;

import jerome.common.DataStorage;

/**
 * Represents an executable command.
 * @@author se-edu
 * Reuse from https://github.com/se-edu/addressbook-level2
 */
public class Command {

    /**
     * Represents a data storage object that handles reading from and writing to a file.
     */
    protected DataStorage dataStorage;

    /**
     * Executes the command and returns the result.
     * Code referenced from: https://github.com/se-edu/addressbook-level2
     */
    public CommandResult execute() {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    };

    /**
     * Configures the data storage object used by the command.
     *
     * @param dataStorage the data storage object to be set
     */
    public void setData(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

}
