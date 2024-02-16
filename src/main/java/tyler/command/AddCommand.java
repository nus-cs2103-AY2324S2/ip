package tyler.command;

/**
 * Represents an add command. An add command object has a taskName which needed to
 * add.
 */
public abstract class AddCommand extends Command {
    protected String taskName;

    public AddCommand(String taskName) {
        this.taskName = taskName;
    }
}
