package goblin.command;

import goblin.Storage;
import goblin.TaskList;
import goblin.Ui;
import goblin.task.Task;
import goblin.exception.OrkException;

public class DeleteCommand extends Command {
    protected int index;

    public DeleteCommand(int index) {
        super();
        this.index = index - 1;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws OrkException {
        try {
            Task task = TaskList.list.get(index);
            if (index + 1 > TaskList.list.size()) {
                throw new OrkException("There's no such task.");
            }
            TaskList.list.remove(index);
            int numberOfTasks = TaskList.list.size();
            Ui.printDeleteMessage(task, numberOfTasks);
            Storage.writeToDisk(tasks);
        } catch (OrkException exception) {
            ui.printException(exception);
        }
    }
}
