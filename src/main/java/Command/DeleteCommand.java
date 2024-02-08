package Command;

import Ping.PingException;
import Ping.Task;
import Ping.TaskList;
import Ping.UI;

/**
 * This class is used to delete a task from the list of tasks
 */
public class DeleteCommand extends Command {
    private int idx;

    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public String perform(TaskList tasks, UI ui) throws PingException {
        // handle the exception
        if (this.idx < 0 || this.idx >= tasks.taskSize()) {
            throw new PingException("Invalid number in the list");
        } else {
            Task deletedTask = tasks.deleteTask(this.idx);
            return ui.deleteTaskMessage(this.idx, deletedTask, tasks.taskSize());
        }
    }
}
