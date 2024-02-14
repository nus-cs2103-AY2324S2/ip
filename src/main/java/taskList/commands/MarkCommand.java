package tasklist.commands;

import tasklist.Storage;
import tasklist.TaskList;
import tasklist.Ui;

/**
 * Represents a command to mark/unmark a task. Implements the Command interface.
 */
public class MarkCommand implements Command {
    protected int indexNo;
    protected boolean isMark;

    /**
     * construct for mark/unmark command.
     *
     * @param indexNo index number of the task to be marked/unmarked
     * @param isMark boolean to indicate whether a task should be marked/unmarked
     */
    public MarkCommand(int indexNo, boolean isMark) {
        this.indexNo = indexNo;
        this.isMark = isMark;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        if (isMark) {
            try {
                taskList.markTask(indexNo);
                return ui.showMarkMessage(taskList.getTask(indexNo - 1));
            } catch (IndexOutOfBoundsException e) {
                return (e.getMessage());
            }
        } else {
            try {
                taskList.unmarkTask(indexNo);
                return ui.showUnmarkMessage(taskList.getTask(indexNo - 1));
            } catch (IndexOutOfBoundsException e) {
                return(e.getMessage());
            }
        }
    }
}
