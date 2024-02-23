package duke.command;

import duke.Storage;
import duke.TaskList;

public class ListCommand extends Command {
    /**
     * Executes the command and returns the result.
     * @param tasks the list of tasks
     * @param storage the storage object
     * @return A string that lists all the tasks
     */
    public String execute(TaskList tasks, Storage storage) {
        return tasks.list();
    }
}
