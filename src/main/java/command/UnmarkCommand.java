package command;

import exception.BuddyException;
import storage.Storage;
import task.Task;
import task.TaskList;

/**
 * Represents Command that unmarks tasks.
 */
public class UnmarkCommand extends Command {
    private int taskIndex;

    /**
     * Creates UnmarkCommand with specified task to unmark.
     *
     * @param taskIndex Task to be unmarked.
     */
    public UnmarkCommand(int taskIndex) {
        assert taskIndex >= 0;
        this.taskIndex = taskIndex;
    }

    /**
     * Unmarks tasks.
     *
     * @param tasks TaskList consisting of Tasks.
     * @param storage Current Storage object used.
     * @throws BuddyException If index of task not found in TaskList.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws BuddyException {
        String response = "I've updated the following task:\n";
        try {
            Task task = tasks.getTask(taskIndex);
            task.setDone(false);
            response += task;
            return response;
        } catch (IndexOutOfBoundsException ioobe) {
            throw new BuddyException("Not a valid task number!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
