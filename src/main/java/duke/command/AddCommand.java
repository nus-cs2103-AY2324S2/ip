package duke.command;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.exception.DukeException;

public class AddCommand extends Command {
    private final Task toAdd;
    public AddCommand(Task toAdd) {
        this.toAdd = toAdd;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(this.toAdd);
        ui.showAddOutput(tasks, this.toAdd);
        storage.write(tasks.toFileFormat());
    }
}