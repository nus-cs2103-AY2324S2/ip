package action;

import exception.InvalidCommandException;

public interface Action {
    public abstract void execute() throws InvalidCommandException;
}
