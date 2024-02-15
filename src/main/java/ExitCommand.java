public class ExitCommand extends Command{
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.salute();
        storage.saveData(tasks);
    }

    public boolean isExit() {
        return true;
    }
}
