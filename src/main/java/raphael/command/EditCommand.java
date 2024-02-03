package raphael.command;

import raphael.exception.RaphaelException;
import raphael.task.TaskList;
import raphael.ui.Ui;
import raphael.storage.Storage;

public class EditCommand extends Command {
    private final int idx;
    private final boolean isMark;
    public EditCommand(int idx, boolean isMark) {
        this.idx = idx;
        this.isMark = isMark;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws RaphaelException {
        if (this.isMark) {
            ui.showMarkOutput(tasks.checkTask(this.idx));
        } else {
            ui.showUnmarkOutput(tasks.uncheckTask(this.idx));
        }
        storage.write(tasks.toFileFormat());
    }
}
