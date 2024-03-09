package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.TaskList;
import duke.Ui;
import duke.task.HistoryTask;
import duke.task.Task;

/**
 * Deletes a task from the task list.
 */
public class DeleteCommand extends Command {
    private String input;
    private int num;

    public DeleteCommand(String input, int num) {
        this.input = input;
        this.num = num;
    }

    @Override
    public String execute(TaskList taskList, Ui ui) throws DukeException {
        Task needDelete;
        try {
            needDelete = taskList.get(num);
            taskList.remove(num);
            Parser.pushHistoryCmd(new HistoryTask(input, needDelete));
            return ui.showDeleteTask(taskList, needDelete);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!! I haven't record this detective.task!");
        }
    }
}
