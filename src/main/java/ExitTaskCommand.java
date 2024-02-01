public class ExitTaskCommand extends Command {
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        super.exit();
    }

    @Override
    public boolean isExited() {
        return true;
    }
}