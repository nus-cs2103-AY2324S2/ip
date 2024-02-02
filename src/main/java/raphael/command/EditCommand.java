package raphael.command;
import raphael.task.TaskList;
import raphael.ui.Ui;
import raphael.storage.Storage;
import raphael.exception.DukeException;public class EditCommand extends Command {
    private final int idx;
    private final boolean isCheck;
    public EditCommand(int idx, boolean isCheck) {
        this.idx = idx;
        this.isCheck = isCheck;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.isCheck) {
            tasks.checkTask(this.idx);
        } else {
            tasks.uncheckTask(this.idx);
        }
        storage.write(tasks.toFileFormat());
    }
}
