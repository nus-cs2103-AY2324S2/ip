package pyrite.command;

import pyrite.StateFile;
import pyrite.TaskList;
import pyrite.task.Task;

/**
 * Command to add a task to the list.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructs an AddCommand.
     *
     * @param task Task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * {inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, StateFile file) {
        tasks.add(this.task);
        String saveResult = file.saveState(tasks);
        return "Task inscribed:"
                + "\n\t" + this.task.toString()
                + "\n" + "Now you have " + tasks.size() + " tasks in the list."
                + saveResult;
    }
}
