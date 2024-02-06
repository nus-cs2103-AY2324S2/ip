package lindi.commands;

import lindi.storage.Storage;
import lindi.task.Task;
import lindi.task.TaskList;

/**
 * Represents a command that deletes a lindi.task.Task upon execution
 */
public class DeleteCommand extends Command {
    private final int listIndex;

    /**
     * Creates command to delete a lindi.task.Task from the lindi.task list
     *
     * @param listIndex index of lindi.task to delete
     */
    public DeleteCommand(int listIndex) {
        this.listIndex = listIndex;
    }

    /**
     * {@inheritDoc}
     *
     * Delete the lindi.task at listIndex from tasks.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        try {
            Task deletedTask = tasks.delete(this.listIndex);
            this.statusMsg = String.format("Okay. I've deleted this lindi.task:\n\t%s\nNow you have %d tasks in the list.",
                    deletedTask, tasks.size());
            storage.saveToFile(tasks);
        } catch (IndexOutOfBoundsException e) {
            this.statusMsg = "Sorry, I can't find that lindi.task. Please enter a valid index\n" +
                    "You can see the tasks list by inputting \"list\"";
        }
    }
}