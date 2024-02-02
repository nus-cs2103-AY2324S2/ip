public abstract class AddCommand extends Command {

    public String taskName;

    public AddCommand(String commandType, String taskName) {
        super(commandType);
        this.taskName = taskName;
    }
}
