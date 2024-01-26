public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.storeToLocal(tasks);
        ui.goodbyePrinter();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
