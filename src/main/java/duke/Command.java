package duke;

/**
 * Represents a command.
 * <p>
 * Subclasses of this class must register itself with {@link Parser}
 * in {@link Commands#registerCommands}.
 */
public abstract class Command {
    public abstract String execute(TaskList tasks) throws DukeException;
}
