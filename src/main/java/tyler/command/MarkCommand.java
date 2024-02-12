package tyler.command;

import tyler.task.TaskList;
import tyler.storage.Storage;
import tyler.ui.Ui;

public class MarkCommand extends Command {
    protected int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.getTask(this.index - 1).mark();
    }
}
