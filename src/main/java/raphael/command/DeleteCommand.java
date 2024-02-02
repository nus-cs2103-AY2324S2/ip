package raphael.command;

import raphael.task.TaskList;
import raphael.ui.Ui;
import raphael.storage.Storage;

public class DeleteCommand extends Command {
    private final int idx;
    public DeleteCommand(int idx) {
        this.idx = idx;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws raphael.exception.RaphaelException {
        tasks.deleteTask(idx);
        storage.write(tasks.toFileFormat());
    }
}
