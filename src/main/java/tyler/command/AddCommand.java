package tyler.command;

public abstract class AddCommand extends Command {
    protected String taskName;

    public AddCommand(String taskName) {
        this.taskName = taskName;
    }
}
