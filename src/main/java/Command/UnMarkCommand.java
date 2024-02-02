package command;

import ping.Task;
import ping.TaskList;
import ping.UI;
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

