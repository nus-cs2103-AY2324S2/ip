package command;

import ping.Task;
import ping.TaskList;
import ping.UI;


public class AddCommand extends Command {
    private Task task;
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public TaskList perform(TaskList tasks, UI ui) {
        tasks.addTask(task);
        ui.addMessage(task, tasks.taskSize());
        return tasks;
    }
}
