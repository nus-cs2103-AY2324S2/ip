package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.TaskList;
import duke.Ui;
import duke.task.HistoryTask;
import duke.task.Task;

import java.util.Objects;

/**
 * Marks the task done or undone.
 */
public class MarkCommand extends Command {
    private String input;
    private int num;
    private boolean isMark;

    public MarkCommand(String input, int num, boolean isMark) {
        this.input = input;
        this.num = num;
        this.isMark = isMark;
    }

    @Override
    public String execute(TaskList taskList, Ui ui) throws DukeException {
        assert taskList != null : "Task list is empty!!!";
        Task task = taskList.get(num);
        try {
            if (isMark && Objects.equals(task.getStatusIcon(), "[N] ")) {
                taskList.get(num).markAsDone();
                Parser.pushHistoryCmd(new HistoryTask(input, null));
                return ui.showMark(taskList, num);
            } else if (!isMark && Objects.equals(task.getStatusIcon(), "[Y] ")) {
                taskList.get(num).markAsNotDone();
                Parser.pushHistoryCmd(new HistoryTask(input, null));
                return ui.showUnmark(taskList, num);
            } else {
                return ui.showNoChange(taskList, num);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!! I haven't record this task!");
        }
    }
}
