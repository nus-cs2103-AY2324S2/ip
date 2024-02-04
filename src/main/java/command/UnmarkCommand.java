package command;

import helpers.TaskList;
import helpers.Ui;
import task.Task;

public class UnmarkCommand extends Command {
    private final int i;

    public UnmarkCommand(int i) {
        this.i = i;
    }

    /**
     * Unmarks a task as completed based on the given index.
     */
    @Override
    public void execute(Ui ui, TaskList taskList) {
        Task updatedTask = taskList.unmarkTask(i - 1);
        ui.displayUnmarkedTask(updatedTask);
    }
}
