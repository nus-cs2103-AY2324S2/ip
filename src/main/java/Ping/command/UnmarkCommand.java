package ping.command;

import ping.TaskList;
import ping.exceptions.PingException;
import ping.gui.UI;
import ping.job.Task;

/**
 * This class is used to unmark a task
 */
public class UnmarkCommand extends Command {
    private int idx;

    public UnmarkCommand(int idx) {
        this.idx = idx;
    }
    @Override
    public String perform(TaskList tasks, UI ui) throws PingException {
        try {
            Task task = tasks.getTask(this.idx);
            task.unMarkDone();
            return ui.unmarkTaskMessage(task);
        } catch (Exception e) {
            throw new PingException("Invalid number");
        }
    }
}

