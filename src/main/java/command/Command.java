package command;

import common.DukeException;

public abstract class Command {
    public abstract void execute() throws DukeException;
    public abstract boolean isExit();
}
