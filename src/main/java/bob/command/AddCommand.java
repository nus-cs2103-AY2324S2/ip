package bob.command;

/**
 * Represents an action to add a task. An <code>AddCommand</code> object corresponds to
 * a command to add a task with a specified description.
 */
public abstract class AddCommand extends Command {
    protected String description;

    /**
     * Returns a command to add a task with a specified description.
     *
     * @param description The description for the task to be added.
     */
    public AddCommand(String description) {
        this.description = description;
    }
}
