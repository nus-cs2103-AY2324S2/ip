import java.io.IOException;

public class ExitCommand extends Command {
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.saveTask(tasks);
        ui.showBye();
    }
}
