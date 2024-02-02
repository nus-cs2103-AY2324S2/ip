package Command;

import Ping.Task;
import Ping.TaskList;
import Ping.UI;


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
