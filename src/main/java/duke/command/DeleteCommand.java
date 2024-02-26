package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.UI;
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
        //this itemNo is what user sees
        //have to decrease by 1 to derive index no in taskList
        this.itemNoToDelete = itemNoToDelete;
    }

    /**
     * Execute deletion of task from list.
     *
     * @param taskList the task list
     * @param ui       the ui
     * @param storage  the storage
     * @throws DukeException the duke exception
     */
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        int taskIndex = this.itemNoToDelete-1;
        Task taskToDelete = taskList.getItemFromListByIndex(taskIndex);
        taskList.removeItemAtIndex(taskIndex);
        storage.Store(taskList.toString());
        int count = taskList.getTaskList().size();
        StringBuilder sb = new StringBuilder();
        sb.append("Noted. I've removed this task:");
        sb.append("\n").append(taskToDelete.printOutput());
        sb.append("\n").append("Now you have "+count+" tasks in the list");
        ui.setCommandOutput(sb.toString());
    }
}
