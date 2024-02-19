package lindi.commands;

import lindi.storage.Storage;
import lindi.task.Task;
import lindi.task.TaskList;

/**
 * Represents a command that marks a task as done upon execution
 */
public class MarkCommand extends Command {
    public static final String COMMAND_MARK = "mark";
    private final int listIndex;

    /**
     * Creates command that marks a task in the task List
     *
     * @param listIndex index of task to mark
     */
    public MarkCommand(int listIndex) {
        this.listIndex = listIndex;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Marks the task at listIndex in tasks as done.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        try {
            Task markedTask = tasks.mark(this.listIndex);
            this.statusMsg = "Nice! I've marked this task as done:\n\t" + markedTask;
            storage.saveToFile(tasks);
        } catch (IndexOutOfBoundsException e) {
            this.statusMsg = "Sorry, I can't find that task. Please enter a valid index\n"
                    + "You can see the tasks list by inputting \"list\"";
        }
    }
}
