package command;

import cleo.DukeException;
import cleo.TaskList;
import cleo.UI;
import cleo.Task;

public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList tasks, UI ui) throws DukeException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new DukeException("Task number out of bounds.");
        }
        Task removedTask = tasks.removeTask(taskIndex);
        return ui.showTaskRemoved(removedTask, tasks.size());
    }
}
