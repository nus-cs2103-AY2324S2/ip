abstract class AddCommand extends Command {
    protected final String description;
    AddCommand(String description) {
        super("");
        this.description = description;
    }
}
