package mike.command;

import mike.TaskList;
import mike.task.Task;

/**
 * Base class for all commands to add tasks.
 */
public abstract class AddCommand extends Command {
    protected final String description;

    /**
     * Constructor.
     * @param description The add command description.
     */
    public AddCommand(String description) {
        this.description = description;
    }

    protected String response(TaskList taskList, Task newTask) {
        return "Got it, I've added this task:\n  "
                + newTask + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.";
    }
}
