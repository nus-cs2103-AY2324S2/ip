public abstract class AddTaskCommand extends Command {
    private String description;

    public AddTaskCommand(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
