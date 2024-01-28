/**
 * Represents a command.
 * Subclasses of this class must register itself with Parser.
 * They must have either a constructor with no parameters
 * or a constructor with a single String parameter.
 */
public abstract class Command {
    public abstract void execute(Storage storage, boolean silent) throws DukeException;

    public void execute(Storage storage) throws DukeException {
        execute(storage, false);
    }

    public void executeSilently(Storage storage) throws DukeException {
        execute(storage, true);
    }
}
