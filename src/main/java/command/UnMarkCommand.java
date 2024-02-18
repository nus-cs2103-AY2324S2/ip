package command;

import cleo.DukeException;
import cleo.TaskList;
import cleo.UI;
import cleo.Task;

public class UnMarkCommand extends Command {
    private int taskIndex;

    public UnMarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList tasks, UI ui) throws DukeException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new DukeException("Invalid task number. Index out of bounds.");
        }

        Task task = tasks.get(taskIndex);
        task.markAsNotDone();
        return ui.showTaskMarkedAsNotDone(task);
    }
}
