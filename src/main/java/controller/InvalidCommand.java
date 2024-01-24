package controller;

import duke.DukeException;
import duke.Storage;

/**
 * The {@code InvalidCommand} class represents a command to handle invalid user input.
 */
public class InvalidCommand extends Command {
    private final String exception;

    /**
     * Constructs an {@code InvalidCommand} with a default exception message.
     */
    public InvalidCommand() {
        this.exception = new DukeException("I'm sorry, but I don't know what that means :-(").getMessage();
    }

    /**
     * Executes the {@code InvalidCommand}, print the error message when user input is invalid.
     *
     * @param storage The storage object that manages the data persistence. It is not used in {@code ExitCommand}
     */
    @Override
    public void execute(Storage storage) {
        System.out.println(exception);
    }
}
