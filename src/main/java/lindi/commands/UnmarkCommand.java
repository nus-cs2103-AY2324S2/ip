package lindi.commands;

import lindi.storage.Storage;
import lindi.task.Task;
import lindi.task.TaskList;

/**
 * Represents a command that marks a lindi.task as not done upon execution
 */
public class UnmarkCommand extends Command {
    private final int listIndex;

    /**
     * Creates command that unmarks a lindi.task.Task in the lindi.task.Task List
     *
     * @param listIndex index of lindi.task to unmark
     */
    public UnmarkCommand(int listIndex) {
        this.listIndex = listIndex;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Marks the lindi.task at listIndex in tasks as `not` done.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        try {
            Task unmarkedTask = tasks.unmark(this.listIndex);
            this.statusMsg = "Nice! I've marked this lindi.task as not done yet:\n\t" + unmarkedTask;
            storage.saveToFile(tasks);
        } catch (IndexOutOfBoundsException e) {
            this.statusMsg = "Sorry, I can't find that lindi.task. Please enter a valid index\n" +
                    "You can see the tasks list by inputting \"list\"";
        }
    }
}