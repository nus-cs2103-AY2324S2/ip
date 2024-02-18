package duke;

/**
 * Represents a command.
 * <p>
 * Subclasses of this class must register itself with {@link Parser}
 * in {@link Commands#registerCommands}.
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
