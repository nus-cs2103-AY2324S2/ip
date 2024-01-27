/**
 * Represents different commands from users.
 */
public abstract class Command {
    /**
     * Executes commands.
     *
     * @param ui Ui used to reply users.
     * @return TaskList after executing command.
     */
    public abstract TaskList execute(Ui ui);

    /**
     * Returns true if this is a Bye Command.
     *
     * @return True if this is a Bye Command.
     */
    public abstract boolean isBye();
}
