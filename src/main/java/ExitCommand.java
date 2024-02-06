public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Storage storage) {
        return; // Do nothing
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
