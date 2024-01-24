package controller;

import duke.DukeException;
import duke.Storage;

public class InvalidCommand extends Command {
    private final String exception;

    public InvalidCommand() {
        this.exception = new DukeException("I'm sorry, but I don't know what that means :-(").getMessage();
    }

    @Override
    public void execute(Storage storage) {
        System.out.println(exception);
    }
}
