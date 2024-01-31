public class UnknownCommand extends Command {
    public UnknownCommand() {}

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws HenryException {
        throw new HenryException("I don't understand this command...");
    }
}
