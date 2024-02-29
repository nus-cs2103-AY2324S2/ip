package goblin.command;

import goblin.command.Command;

import goblin.Storage;
import goblin.Ui;
import goblin.task.Task;
import goblin.TaskList;
import goblin.exception.OrkException;

public class UnmarkCommand extends Command {
    protected int index;

    /**
     * create a new UnmarkCommand
     * @param index of task to be unmarked
     */
    public UnmarkCommand(int index) {
        super();
        this.index = index;
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
                throw new OrkException("No such task");
            }
            task.undone();
            Ui.printDoneMessage(task);
            storage.writeToDisk(list);
        } catch (OrkException exception) {
            ui.printException(exception);
        }
    }
}
