package raphael.command;
import raphael.task.Task;
import raphael.task.TaskList;
import raphael.ui.Ui;
import raphael.storage.Storage;

public class AddCommand extends Command {
    private final Task toAdd;
    public AddCommand(Task toAdd) {
        this.toAdd = toAdd;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws raphael.exception.RaphaelException {
        tasks.addTask(this.toAdd);
        ui.showAddOutput(tasks, this.toAdd);
        storage.write(tasks.toFileFormat());
    }
}