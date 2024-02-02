package command;

import ping.Task;
import ping.TaskList;
import ping.UI;

public class DeleteCommand extends Command {
    private int idx;

    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public TaskList perform(TaskList tasks, UI ui) {
        try {
            Task deletedTask = tasks.deleteTask(this.idx);
            ui.deleteTaskMessage(this.idx, deletedTask, tasks.taskSize());
        } catch (Exception e) {
            System.out.println("Incorrect number");
        }
        return tasks;
    }
}
