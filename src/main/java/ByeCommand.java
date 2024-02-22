public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SamException {
        System.out.println("Hope you enjoy my help.");
    }

    public boolean isExit() {

        return true;
    }
}