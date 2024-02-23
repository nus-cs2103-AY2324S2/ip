package tofu.command;

import tofu.TofuException;
import tofu.task.TaskList;
import tofu.ui.Ui;
import tofu.task.Task;

public class MarkCommand implements Command {
    int index;
    boolean isDone;

    /**
     * Constructs a new MarkCommand to update the status of a task.
     *
     * @param index The zero-based index of the task in the TaskList to be updated.
     * @param isDone The new status of the task. Pass 'true' to mark the task as done, 'false' to mark it as not done.
     */
    public MarkCommand(int index, boolean isDone) {
        this.index = index;
        this.isDone = isDone;
    }

    /**
     * Updates the status of a task based on the provided index and boolean.
     *
     * @param tasks The list of tasks.
     * @param ui The user interface to interact with the user.
     * @return A string message indicating the result of the operation.
     * @throws TofuException If the provided index is invalid.
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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof MarkCommand) {
            MarkCommand command = (MarkCommand) obj;
            return index == command.index && isDone == command.isDone;
        } else {
            return false;
        }
    }

    public boolean isExit() {
        return false;
    }
}
