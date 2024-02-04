/**
 * Represents a command.
 * Subclasses of this class must register itself with Parser.
 * They must have either a constructor with no parameters
 * or a constructor with a single String parameter.
 */
public abstract class Command {
    public abstract void execute(TaskList tasks, boolean silent) throws DukeException;

    public void execute(TaskList tasks) throws DukeException {
        execute(tasks, false);
    }

    public void executeSilently(TaskList tasks) throws DukeException {
        execute(tasks, true);
    }
}
