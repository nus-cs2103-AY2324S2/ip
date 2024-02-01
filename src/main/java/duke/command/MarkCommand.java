package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;

public class MarkCommand extends Command {
    private int num;
    private boolean isMark;

    public MarkCommand(int num, boolean isMark) {
        this.num = num;
        this.isMark = isMark;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        try {
            if (isMark) {
                taskList.get(num).markAsDone();
                ui.showMark(taskList, num);
            } else {
                taskList.get(num).markAsNotDone();
                ui.showUnmark(taskList, num);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!! I haven't record this detective.task!");
        }
    }
}
