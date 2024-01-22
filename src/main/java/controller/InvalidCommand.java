package controller;

import model.DukeException;

public class InvalidCommand {
    private final String exception;

    public InvalidCommand() {
        this.exception = new DukeException("I'm sorry, but I don't know what that means :-(").getMessage();
    }
    public void execute() {
        System.out.println(exception);
    }
}
