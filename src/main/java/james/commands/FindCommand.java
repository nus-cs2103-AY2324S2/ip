package james.commands;

import james.exception.DukeException;
import james.storage.Storage;
import james.tasklist.TaskList;
import james.ui.Ui;


import java.util.List;
import java.util.stream.Collectors;
import java.util.List;

/**
 * Represents a command to search for tasks by a keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Creates a FindCommand with the specified keyword for searching.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command which filters the tasks based on the search keyword.
     *
     * @param tasks The TaskList to search within.
     * @param ui    The Ui responsible for displaying the search results to the user.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<String> matchingTasks = tasks.getTasks().stream()
            .filter(task -> task.getDescription().contains(keyword))
            .map(task -> task.toString())
            .collect(Collectors.toList());
        ui.showTasksFound(matchingTasks);
    }

    /**
     * Indicates whether the application should terminate after the execution of
     * this command. For FindCommand, it always returns false.
     *
     * @return false as the application should not exit after searching.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
