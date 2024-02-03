package Command;

import Ping.Task;
import Ping.TaskList;
import Ping.UI;

/**
 * This class is used to unmark a task
 */
public class UnMarkCommand extends Command {
    private int idx;

    public UnMarkCommand(int idx) {
        this.idx = idx;
    }
    @Override
    public TaskList perform(TaskList tasks, UI ui) {
        try {
            Task task = tasks.getTask(this.idx);
            task.unMarkDone();
            ui.markTaskMessage(task);
        } catch (Exception e) {
            System.out.println("Invalid number");
        }
        return tasks;
    }
}

