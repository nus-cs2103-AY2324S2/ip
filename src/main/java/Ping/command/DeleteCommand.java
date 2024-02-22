package ping.command;

import ping.TaskList;
import ping.exceptions.PingException;
import ping.gui.UI;
import ping.job.Task;


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
        if (isInvalidIndex(tasks)) {
            throw new PingException("Invalid number in the list");
        } else {
            Task deletedTask = tasks.deleteTask(this.idx);
            return ui.deleteTaskMessage(this.idx, deletedTask, tasks.taskSize());
        }
    }

    private boolean isInvalidIndex(TaskList tasks) {
        boolean isLessThanZero = this.idx < 0;
        boolean isMoreThanSize = this.idx >= tasks.taskSize();
        return isLessThanZero || isMoreThanSize;
    }
}

