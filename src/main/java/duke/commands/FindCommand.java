package duke.commands;

import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to search for certain tasks.
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(Ui ui, TaskList tasks) {
        if (keyword.isBlank()) {
            return "OOPS! You forget to provide the keyword to search.";
        }
        TaskList results = tasks.find(keyword);
        if (results.size() != 0) {
            return "Here are the matching tasks in your list:\n" + results.displayTasks();
        } else {
            return "There is no matching tasks in your list.";
        }
    }
}
