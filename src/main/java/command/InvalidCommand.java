package command;

import duke.DukeException;
import duke.Storage;

/**
 * The {@link InvalidCommand} class represents a command to handle invalid user input.
 */
public class InvalidCommand extends Command {
    private final String exception;

    /**
     * Constructs an {@link InvalidCommand} with a default exception message.
     */
    public InvalidCommand() {
        this.exception = new DukeException("I'm sorry, but I don't know what that means :-(").getMessage();
    }

    /**
     * Executes the {@link InvalidCommand}, print the error message when user input is invalid.
     * <p>
     * This method display the error message by printing out the exception message.
     *
     * @param storage The storage object that manages the data persistence. It is not used in {@link ExitCommand}
     */
    @Override
    public void execute(Storage storage) {
        System.out.println(exception);
    }
}
