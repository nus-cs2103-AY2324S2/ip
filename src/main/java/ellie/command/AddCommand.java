package ellie.command;

import ellie.TaskList;
import ellie.task.Task;

public class AddCommand extends Command {

    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    public void run(TaskList tasklist) {
        tasklist.addTask(task);
        return;
    };


}
