package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.InvalidArgumentException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Represents the command to find task with matching keyword.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Constructor for a Find command,
     * which initialises the command with its keyword.
     *
     * @param keyword Keywords for the search.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        ui.displayMatchingTasks(taskList, this.keyword);
    }
}
