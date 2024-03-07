package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.task.Task;

/**
 * The Delete command.
 * To delete tasks from task list
 */
public class DeleteCommand extends Command{
    private int itemNoToDelete;

    /**
     * Instantiates a new Delete command.
     */
    public DeleteCommand(){
    }

    /**
     * Instantiates a new Delete command.
     *
     * @param itemNoToDelete the item no to delete
     */
    public DeleteCommand(int itemNoToDelete) {
        assert itemNoToDelete > 0;
        //this itemNo is what user sees
        //have to decrease by 1 to derive index no in taskList
        this.itemNoToDelete = itemNoToDelete;
    }

    /**
     * Execute deletion of task from list.
     *
     * @param taskList the task list
     * @param storage  the storage
     * @throws DukeException the duke exception
     */
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        int taskIndex = this.itemNoToDelete-1;
        Task taskToDelete = taskList.getItemFromListByIndex(taskIndex);
        taskList.removeItemAtIndex(taskIndex);
        storage.Store(taskList.toString());
        int count = taskList.getCountByType (taskToDelete.getTypeOfTask());
        String sb = "Noted. I've removed this task:\n" +
                taskToDelete.printOutput() +
                "Now you have " + count + " tasks in the list";
        return sb;
    }
}
