package goblin.command;

import goblin.Storage;
import goblin.TaskList;
import goblin.Ui;
import goblin.task.Task;
import goblin.exception.OrkException;

public class DeleteCommand extends Command {
    protected int index;

    /**
     * create a new DeleteCommand
     * @param index of task int the list to be deleted
     */
    public DeleteCommand(int index) {
        super();
        this.index = index - 1;
    }

    /**
     * remove the task from  the list
     * @param list the list of task
     * @param ui handle ui
     * @param storage handle storage
     * @throws OrkException when the description is not complete
     */
    public void execute(TaskList list, Ui ui, Storage storage) throws OrkException {
        try {
            Task task = TaskList.list.get(index);
            if (index + 1 > TaskList.list.size()) {
                throw new OrkException("There's no such task.");
            }
            TaskList.list.remove(index);
            int numberOfTasks = TaskList.list.size();
            Ui.printDeleteMessage(task, numberOfTasks);
            storage.writeToDisk(list);
        } catch (OrkException exception) {
            ui.printException(exception);
        }
    }
}
