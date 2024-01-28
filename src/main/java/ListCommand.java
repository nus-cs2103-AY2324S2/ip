import java.io.IOException;

public class ListCommand extends Command {
    @Override
    public void run(TaskList taskList, Ui ui, Storage storage) throws IOException, CommandException {
        ui.showMessage(taskList.listTasks());
    }
}
