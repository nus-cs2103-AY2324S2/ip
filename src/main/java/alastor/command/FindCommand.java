package alastor.command;

import alastor.Storage;
import alastor.TaskList;
import alastor.Ui;

/**
 * Represents a command to find tasks in the task list.
 */
public class FindCommand extends Command {

    private final String keyword;

    /**
     * Constructs a new find command with the given keyword.
     *
     * @param keyword The keyword to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = tasks.find(keyword);
        ui.showFind(matchingTasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
