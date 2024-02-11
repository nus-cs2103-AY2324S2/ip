package duke.command;

import duke.Storage;
import duke.task.TaskList;

/**
 * Represents a command show list of tasks.
 */
public class ListCommand extends Command {
    /**
     * Executes the command, updating response.
     *
     * @param list TaskList object containing current tasks.
     * @param storage Not used
     */
    @Override
    public void execute(TaskList list, Storage storage) {
        super.setResponse(list.toString());
    }
}
