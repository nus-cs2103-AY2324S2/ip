package iggly.command;

import iggly.duke.Storage;

/**
 * The {@link Command} class is an abstract base class representing a generic command in a
 * command-pattern-based design. Subclasses of this class provide concrete implementations
 * for the {@code execute} method to define specific behaviors.
 */
public abstract class Command {
    /**
     * Executes the {@link Command}, performing the necessary actions to carry out the specific functionality
     * related to the command implementation.
     *
     * @param storage The storage object that manages the data persistence. It is used to update
     *                and save changes after executing the command.
     */
    public abstract String execute(Storage storage);
}
