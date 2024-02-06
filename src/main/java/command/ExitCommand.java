package command;
import duke.Ui;
import task.TaskList;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.sayBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
