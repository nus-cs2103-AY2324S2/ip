package lindi.commands;

import lindi.storage.Storage;
import lindi.task.Task;
import lindi.task.TaskList;

/**
 * Represents a command that marks a lindi.task as done upon execution
 */
public class MarkCommand extends Command {
    private final int listIndex;

    /**
     * Creates command that marks a lindi.task.Task in the lindi.task.Task List
     *
     * @param listIndex index of lindi.task to mark
     */
    public MarkCommand(int listIndex) {
        this.listIndex = listIndex;
    }

    /**
     * {@inheritDoc}
     *
     * Marks the lindi.task at listIndex in tasks as done.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        try {
            Task markedTask = tasks.mark(this.listIndex);
            this.statusMsg = "Nice! I've marked this lindi.task as done:\n\t" + markedTask;
            storage.saveToFile(tasks);
        } catch (IndexOutOfBoundsException e) {
            this.statusMsg = "Sorry, I can't find that lindi.task. Please enter a valid index\n" +
                    "You can see the tasks list by inputting \"list\"";
        }
    }
}