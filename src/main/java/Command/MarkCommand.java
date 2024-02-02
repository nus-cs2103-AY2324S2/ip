package command;

import ping.Task;
import ping.TaskList;
import ping.UI;
public class MarkCommand extends Command {
    private int idx;

    public MarkCommand(int idx) {
        this.idx = idx;
    }
    @Override
    public TaskList perform(TaskList tasks, UI ui) {
        try {
            Task task = tasks.getTask(this.idx);
            task.markDone();
            ui.markTaskMessage(task);
        } catch (Exception e) {
            System.out.println("Invalid number in the list");
        }
        return tasks;
    }
}
