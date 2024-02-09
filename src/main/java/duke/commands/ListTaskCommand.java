package duke.commands;

import duke.utils.Storage;
import duke.utils.TaskList;

/**
 * This class implements the commmand for listing tasks in a tasklist.
 */
public class ListTaskCommand extends Command {

    /**
     * Creates a ListTaskCommand, sets isExit to false.
     */
    public ListTaskCommand() {
        super(false);
    }

    /**
     * Executes list task command, uses ui to print out tasks in provided TaskList.
     *
     * @param tasks the current list of tasks.
     * @param storage Storage object with save file.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.toString();
    }
}
