package ping.command;

import ping.TaskList;
import ping.exceptions.PingException;
import ping.gui.UI;
import ping.job.Task;

/**
 * This class is used to mark a task as done
 */
public class MarkCommand extends Command {
    private int idx;

    public MarkCommand(int idx) {
        this.idx = idx;
    }
    @Override
    public String perform(TaskList tasks, UI ui) throws PingException {
        try {
            Task task = tasks.getTask(this.idx);
            task.markDone();
            return ui.markTaskMessage(task);
        } catch (Exception e) {
            throw new PingException("Invalid number");
        }
    }
}
