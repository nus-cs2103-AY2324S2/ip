package shon.command;

public abstract class AddTaskCommand extends Command {
    protected String description;

    public AddTaskCommand(String description) {
        this.description = description;
    }
}
