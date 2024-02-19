package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.task.Task;

/**
 * Class representing Add commands
 */
public class AddCommand extends Command {
    private Task task;
    public AddCommand(Task task) {
        this.task = task;
    }
    @Override
    public String execute(TaskList tasks, Storage storage) {
        tasks.add(task);
        return "Got it. I've added this task:\n" + task.toString() +
                "\nNow you have " + tasks.getSize() + " tasks in the list.";
    }
}
