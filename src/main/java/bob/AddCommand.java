package bob;

public abstract class AddCommand extends Command {
    protected String description;

    public AddCommand(String description) {
        this.description = description;
    }
}
