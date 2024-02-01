package mike.command;

import mike.TaskList;
import mike.Ui;
import mike.task.Task;

/**
 * Base class for all commands to add tasks.
 */
abstract class AddCommand extends Command {
    protected final String description;

    /**
     * Constructor.
     * @param description The add command description.
     */
    public AddCommand(String description) {
        this.description = description;
    }

    protected void respond(TaskList taskList, Task newTask) {
        String message =
                "Got it, I've added this task:\n  "
                + newTask + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.";
        Ui.display(message);
    }
}