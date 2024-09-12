package tommy.command;

import tommy.Storage;
import tommy.Ui;
import tommy.task.TaskList;

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
    public String execute(Storage storage, TaskList taskList, Ui ui) {
        return ui.displayMatchingTasks(taskList, this.keyword);
    }
}
