package ping.command;

import ping.TaskList;
import ping.exceptions.PingException;
import ping.gui.UI;
import ping.job.Task;


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
        boolean isDuplicate = tasks.detectDuplicate(task);
        if (isTaskEmpty) {
            throw new PingException("Task cannot be empty");
        } else if (isDuplicate) {
            throw new PingException("Task already exists");
        } else {
            tasks.addTask(task);
            return ui.addMessage(task, tasks.taskSize());
        }
    }
}
