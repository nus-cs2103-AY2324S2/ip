package lindi.commands;

import lindi.storage.Storage;
import lindi.task.Task;
import lindi.task.TaskList;

/**
 * Represents a command that marks a task as not done upon execution
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_UNMARK = "unmark";
    private final int listIndex;

    /**
     * Creates command that unmarks a task in the task List
     *
     * @param listIndex index of task to unmark
     */
    public UnmarkCommand(int listIndex) {
        this.listIndex = listIndex;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Marks the task at listIndex in tasks as `not` done.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        try {
            Task unmarkedTask = tasks.unmark(this.listIndex);
            this.statusMsg = "Nice! I've marked this task as not done yet:\n\t" + unmarkedTask;
            storage.saveToFile(tasks);
        } catch (IndexOutOfBoundsException e) {
            this.statusMsg = "Sorry, I can't find that task. Please enter a valid index\n"
                    + "You can see the tasks list by inputting \"list\"";
        }
    }
}
