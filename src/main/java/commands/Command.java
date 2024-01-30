package commands;

import exception.InvalidCommandException;
import exception.InvalidDeadlineException;
import exception.InvalidEventException;
import exception.InvalidIndexException;

public interface Command {
    void execute() throws InvalidCommandException, InvalidDeadlineException, InvalidEventException, InvalidIndexException;
}
