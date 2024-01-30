package commands;

import common.DataStorage;

/**
 * Represents an executable command.
 */
public class Command {

    protected DataStorage dataStorage;

    /**
     * Executes the command and returns the result.
     * TODO: Code referenced from: https://github.com/se-edu/addressbook-level2
     */
    public CommandResult execute() {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    };

    public void setData(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

}
