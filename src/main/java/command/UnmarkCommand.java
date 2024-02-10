package command;

import dook.DookException;
import dook.Storage;
import task.Task;
import task.TaskList;

public class UnmarkCommand extends Command {
    private final int positionToUnmark;

    public UnmarkCommand(int positionToUnmark) {
        this.positionToUnmark = positionToUnmark;
    }

    /**
     * Marks a task as not done.
     *
     * @param tasks The bot TaskList.
     * @param storage The storage interface.
     * @throws DookException If TaskList is empty or indexed out of bounds.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DookException {
        Task toUnmark;
        try {
            toUnmark = tasks.get(positionToUnmark - 1);
        } catch (IndexOutOfBoundsException e) {
            DookException err;
            if (tasks.size() == 0) {
                err = new DookException("Nooo! You don't have any tasks to unmark :(");
            } else {
                err = new DookException(String.format("Nooo! "
                                + "You have %d tasks!"
                                + " Valid inputs for unmark is in the range [1 - %d]",
                        tasks.size(), tasks.size()));
            }
            throw err;
        }
        toUnmark.markAsNotDone();
        String toReturn = "Lazy bum. >:( I've marked this task as done:\n";
        toReturn += toUnmark + "\n";
        storage.write(tasks);
        return toReturn;
    }
}
