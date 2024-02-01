package duke.actions;

import java.io.IOException;
import java.lang.IndexOutOfBoundsException;

import duke.exceptions.InvalidCommandException;
import duke.exceptions.InvalidInputException;

public abstract class Command {

    public Command() {
    }

    abstract public String execute() throws IOException, IndexOutOfBoundsException, InvalidInputException, InvalidCommandException;
}
