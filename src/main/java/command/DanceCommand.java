package command;

import dook.DookException;
import dook.Storage;
import task.TaskList;

/**
 * Makes Dook dance.
 */
public class DanceCommand extends Command {

    /**
     * Returns the response after dancing, actual dancing handled by GUI.
     *
     * @param tasks The bot TaskList.
     * @param storage The storage interface.
     * @throws DookException If TaskList is empty or indexed out of bounds.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "Hope you liked my dance!!! meow";
    }
}
