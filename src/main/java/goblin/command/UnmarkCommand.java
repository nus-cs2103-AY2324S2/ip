package goblin.command;

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
    //Solution below inspired by https://github.com/nus-cs2103-AY1920S1/duke/pull/23/commits
    public String execute(TaskList list, Ui ui, Storage storage) throws OrkException {
        String message;
        try {
            Task task = TaskList.list.get(index);
            if (index + 1 > TaskList.list.size()) {
                throw new OrkException("No such task");
            }
            task.undone();
            storage.writeToDisk(list);
            message = Ui.printDoneMessage(task);
        } catch (OrkException exception) {
            message = exception.getMessage();
        }
        return message;
    }
}
