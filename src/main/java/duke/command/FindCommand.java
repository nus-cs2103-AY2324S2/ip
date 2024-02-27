package duke.command;

import java.util.List;

import duke.task.TaskList;
import duke.ui.Messages;
import duke.ui.Ui;

/**
 * Represents a command to find tasks containing a specific keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for.
     */
    public FindCommand(String keyword) {
        super("find", List.of(keyword));
        this.keyword = keyword;
    }

    @Override
    public TaskList execute(TaskList tasks, Ui ui) {
        TaskList matchingTasks = tasks.findTasksByKeyword(keyword);
        ui.appendResponse(Messages.FIND_SUCCESS.getMessage());
        ui.appendResponse(matchingTasks.toString());
        return tasks;
    }
}
