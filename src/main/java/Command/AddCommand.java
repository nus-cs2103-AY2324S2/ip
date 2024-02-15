package Command;

import Ping.PingException;
import Ping.Task;
import Ping.TaskList;
import Ping.UI;


/**
 * This class is used to add a task to the list of tasks
 */
public class AddCommand extends Command {
    private Task task;
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String perform(TaskList tasks, UI ui) throws PingException {
        boolean isTaskEmpty = task == null;
        if (isTaskEmpty) {
            throw new PingException("Task cannot be empty");
        } else {
            tasks.addTask(task);
            return ui.addMessage(task, tasks.taskSize());
        }
    }
}
