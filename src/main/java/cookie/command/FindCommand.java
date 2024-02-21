package cookie.command;

import cookie.CookieException;
import cookie.task.Task;
import cookie.task.TaskList;
import cookie.ui.Ui;

/**
 * Represents a command to find tasks containing a specific keyword in the task list.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Constructs a FindCommand with the specified input and keyword.
     *
     * @param input   The input string associated with the command.
     * @param keyword The keyword to search for in the task descriptions.
     */
    public FindCommand(String input, String keyword) {
        super(input);
        this.keyword = keyword;
    }

    @Override
    public String executeAndReply(Ui ui, TaskList tasks, Storage storage) throws CookieException {
        Task[] tasksToPrint = tasks.searchKeyWord(keyword);
        if (tasksToPrint[0] != null) {
            return ui.showTasksContainingKeyword(tasksToPrint, keyword);
        } else {
            throw new CookieException("UH OH! No tasks containing this keyword!");
        }
    }
}
