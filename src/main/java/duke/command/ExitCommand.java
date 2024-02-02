package duke.command;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        return ;
    }
    @Override
    public boolean isExit() {
        System.out.println("Bye! It is an honor to serve you!");
        return true;
    }
}
