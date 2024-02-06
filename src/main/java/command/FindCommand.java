package command;

import duke.Ui;
import task.Task;
import task.TaskList;
import java.util.ArrayList;

/**
 * Represents a command to find tasks in the task list that match a keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructor for FindCommand.
     * @param keyword The keyword to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command to find tasks in the task list that match a keyword.
     * @param tasks The task list.
     * @param ui The user interface.
     */
    public void execute(TaskList tasks, Ui ui) {
        ArrayList<Task> matchingTasks = tasks.findTasks(keyword);
        ui.showFindTasks(matchingTasks);
    }
}
