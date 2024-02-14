package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;

/**
 * Marks the task done or undone.
 */
public class MarkCommand extends Command {
    private int num;
    private boolean isMark;

    public MarkCommand(int num, boolean isMark) {
        this.num = num;
        this.isMark = isMark;
    }

    @Override
    public String execute(TaskList taskList, Ui ui) throws DukeException {
        assert taskList != null : "Task list is empty!!!";
        try {
            if (isMark) {
                taskList.get(num).markAsDone();
                return ui.showMark(taskList, num);
            } else {
                taskList.get(num).markAsNotDone();
                return ui.showUnmark(taskList, num);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!! I haven't record this task!");
        }
    }
}
