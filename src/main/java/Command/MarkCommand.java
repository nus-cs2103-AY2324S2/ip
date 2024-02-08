package Command;

import Ping.PingException;
import Ping.Task;
import Ping.TaskList;
import Ping.UI;

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
