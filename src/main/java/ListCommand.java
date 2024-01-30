public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public CommandResult execute() {
        return new CommandResult(
                String.format(Messages.MESSAGE_LIST, super.taskList));
    }
}
