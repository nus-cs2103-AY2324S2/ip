package pyrite.command;

import pyrite.StateFile;
import pyrite.TaskList;

public class FilteredListCommand extends Command {
    private String keyword;
    public FilteredListCommand(String keyword) {
        this.keyword = keyword;
    }
    @Override
    public String execute(TaskList tasks, StateFile file) {
        return "Here are the matching tasks in your list:\n" + tasks.filteredString(this.keyword);
    }
}
