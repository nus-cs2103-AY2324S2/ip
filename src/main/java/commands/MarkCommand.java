package commands;

import helpers.TaskList;
import helpers.Ui;
import tasks.Task;

public class MarkCommand extends Command {
    private final int i;

    public MarkCommand(int i) {
        this.i = i;
    }

    /**
     * Marks a task as completed based on the given index.
     */
    @Override
    public void execute(Ui ui, TaskList taskList) {
        Task updatedTask = taskList.markTask(i - 1);
        ui.displayMarkedTask(updatedTask);
    }
}
