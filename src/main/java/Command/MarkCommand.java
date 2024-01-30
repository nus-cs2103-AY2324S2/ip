package Command;

import duke.DukeException;
import duke.TaskList;
import duke.UI;
import duke.Task;

public class MarkCommand extends Command {
    private int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public TaskList execute(TaskList tasks, UI ui) throws DukeException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new DukeException("Invalid task number. Index out of bounds.");
        }

        Task task = tasks.get(taskIndex);
        task.markAsDone();
        ui.showTaskMarkedAsDone(task);
        return tasks;
    }
}
