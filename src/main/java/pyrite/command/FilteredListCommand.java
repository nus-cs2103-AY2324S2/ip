package pyrite.command;

import pyrite.StateFile;
import pyrite.TaskList;

/**
 * Command to filter the list of tasks.
 */
public class FilteredListCommand extends Command {
    private String keyword;
    /**
     * Constructs a FilteredListCommand.
     *
     * @param keyword Keyword to filter the list of tasks.
     */
    public FilteredListCommand(String keyword) {
        this.keyword = keyword;
    }
    /**
     * {inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, StateFile file) {
        return "Here are the matching tasks in your list:\n" + tasks.filteredString(this.keyword);
    }
}
