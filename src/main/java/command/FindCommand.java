package command;

import java.util.ArrayList;

import sky.Ui;
import task.Task;
import task.TaskList;

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
     * Finds tasks in the task list that match the keyword and shows the user the matching tasks.
     * @param tasks Task list to find the tasks from.
     * @param ui Ui to display the matching tasks to the user.
     * @return String to be displayed to the user.
     */
    public String execute(TaskList tasks, Ui ui) {
        ArrayList<Task> matchingTasks = tasks.findTasks(keyword);
        return ui.showFindTasks(matchingTasks);
    }
}
