package duke.command;

import duke.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

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
    public String executeAndReply(Ui ui, TaskList tasks, Storage storage) throws DukeException {
        Task[] tasksToPrint = tasks.searchKeyWord(keyword);
        if (tasksToPrint[0] != null) {
            return ui.showTasksContainingKeyword(tasksToPrint, keyword);
        } else {
            throw new DukeException("UH OH! No tasks containing this keyword!");
        }
    }
}
