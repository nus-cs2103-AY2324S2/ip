package zack.commands;

import zack.ZackException;
import zack.util.Storage;
import zack.util.TaskList;
import zack.util.Ui;

/**
 * The FindCommand class represents a command in the Zack task management application
 * that is used to search for tasks containing a specific keyword in their descriptions.
 *
 * When executed, this command searches for tasks within a given TaskList that have
 * descriptions containing the specified keyword. It then displays the list of matching
 * tasks to the user through the provided UI component.
 *
 * @see Command
 * @see TaskList
 * @see Ui
 * @see Storage
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ZackException {
        assert keyword != null : "keyword should not be null";
        TaskList foundTasks = tasks.findTasksByKeyword(keyword);
        return ui.showFoundTasks(foundTasks);
    }
}
