package tofu.command;

import tofu.TofuException;
import tofu.task.TaskList;
import tofu.ui.Ui;
import tofu.task.Task;

public class MarkCommand implements Command {
    int index;
    boolean isDone;

    /**
     * Initializes a new MarkCommand to mark or un-mark a task.
     *
     * @param index the index of task in the TaskList that wants to be marked/un-marked
     * @param isDone true for mark and false for un-mark
     */
    public MarkCommand(int index, boolean isDone) {
        this.index = index;
        this.isDone = isDone;
    }

    /**
     * Marks or Un-marks the Task in the TaskList according to the index
     * and displays the UI message.
     *
     * @param tasks the TaskList for which the Task will be added to
     * @param ui the UI that will be used to display the message
     * @return a String of the UI message and the marked task
     * @throws TofuException if the index is greater than the number of Task in the TaskList
     */
    public String execute(TaskList tasks, Ui ui) throws TofuException {
        assert index > 0;
        if (index + 1 > tasks.size()) {
            throw new TofuException(Ui.indexTooBigError(tasks));
        }
        Task task = tasks.get(index);
        if (isDone) {
            task.mark();
            return ui.markMessage(task.toString());
        } else {
            task.unmark();
            return ui.unmarkMessage(task.toString());
        }
    }

    public boolean isExit() {
        return false;
    }
}
