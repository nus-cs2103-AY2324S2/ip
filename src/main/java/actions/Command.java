package actions;

import java.io.IOException;
import java.lang.IndexOutOfBoundsException;

import exceptions.InvalidCommandException;
import exceptions.InvalidInputException;

public abstract class Command {

    public Command() {
    }

    abstract public String execute() throws IOException, IndexOutOfBoundsException, InvalidInputException, InvalidCommandException;
}
