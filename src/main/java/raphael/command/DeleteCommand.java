package raphael.command;

import raphael.exception.RaphaelException;
import raphael.task.TaskList;
import raphael.ui.Ui;
import raphael.storage.Storage;

public class DeleteCommand extends Command {
    private final int idx;
    public DeleteCommand(int idx) {
        this.idx = idx;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws RaphaelException {
        ui.showDeleteOutput(tasks.deleteTask(idx));
        storage.write(tasks.toFileFormat());
    }
}