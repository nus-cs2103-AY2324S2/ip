package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.task.Task;

/**
 * The type Update command.
 * To udate mark/unmark for tasks in task list
 */
public class UpdateCommand extends Command{
    private int taskSeqNo;
    private boolean isMarked;

    /**
     * Instantiates a new Update command.
     */
    public UpdateCommand(){
    }

    /**
     * Instantiates a new Update command.
     *
     * @param taskSeqNo the task seq no
     * @param isMarked  the is marked
     */
    public UpdateCommand(int taskSeqNo, boolean isMarked) {
        assert taskSeqNo > 0;
        //taskSeqNo is the item no entered by user
        //decrease by 1 to get index no
        this.taskSeqNo = taskSeqNo;
        this.isMarked = isMarked;
    }

    /**
     * Execute.
     *
     * @param taskList the task list
     * @param ui       the ui
     * @param storage  the storage
     * @throws DukeException the duke exception
     */
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        int taskIndexInList = this.taskSeqNo-1;
        Task taskToUpdate = taskList.getItemFromListByIndex(taskIndexInList);
        taskToUpdate.setMarked(this.isMarked);
        taskList.updateTaskInList(taskIndexInList, taskToUpdate);
        storage.Store(taskList.toString());
        StringBuilder sb = new StringBuilder();
        if (isMarked) {
            sb.append("Nice! I've marked this task as done:\n");
        } else {
            sb.append("OK, I've marked this task as note done yet:\n");
        }
        sb.append(taskToUpdate.printOutput());
        return sb.toString();
    }
}
