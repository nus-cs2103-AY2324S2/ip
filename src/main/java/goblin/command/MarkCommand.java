package goblin.command;

import goblin.Storage;
import goblin.Ui;
import goblin.task.Task;
import goblin.TaskList;
import goblin.exception.OrkException;

public class MarkCommand extends Command {
    protected int index;

    public MarkCommand(int index) {
        super();
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws OrkException {
        try {
            Task task = TaskList.list.get(index);
            if (index + 1 > TaskList.list.size()) {
                throw new OrkException("No such task");
            }
            task.done();
            ui.printDoneMessage(task);
            Storage.writeToDisk(tasks);
        } catch (OrkException exception) {
            ui.printException(exception);
        }
    }
}
