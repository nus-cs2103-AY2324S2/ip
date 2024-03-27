package goblin.command;

import goblin.Storage;
import goblin.Ui;
import goblin.task.Task;
import goblin.TaskList;
import goblin.exception.OrkException;

public class MarkCommand extends Command {
    protected int index;

    /**
     * create a new MarkCommand object
     * @param index of the task to be marked
     */
    public MarkCommand(int index) {
        super();
        this.index = index;
    }

    /**
     * mark a task as done
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
            task.done();
            storage.writeToDisk(list);
            message = Ui.printDoneMessage(task);
        } catch (OrkException exception) {
            message = exception.getMessage();
        }
        return message;
    }
}
