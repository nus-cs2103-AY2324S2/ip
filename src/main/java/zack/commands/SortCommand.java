package zack.commands;

import java.io.IOException;
import java.util.List;

import zack.ZackException;
import zack.tasks.Deadline;
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
public class SortCommand extends Command {
    private String description;

    public SortCommand(String description) {
        this.description = description;
    }

    /**
     * Enumeration representing different types of tasks.
     */
    public enum SortType {
        ALPHA, DEADLINES, ADDTIME
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ZackException, IOException {
        String[] parts = description.split(" ");
        if (parts.length != 2) {
            throw new ZackException("The sort command is incomplete or incorrectly formatted. "
                    + "Please include the task type, followed by asc or desc.");
        }
        String orderTaskType = parts[0].toLowerCase();
        String ascOrDesc = parts[1];

        try {
            SortType sortType = SortType.valueOf(orderTaskType.toUpperCase());
            switch (sortType) {
            case ALPHA:
                tasks.sortByAlphabetical(ascOrDesc);
                break;
            case DEADLINES:
                // This only shows the sorted deadlines, but does not sort the actual task list
                List<Deadline> sortedDeadlines = tasks.sortDeadlines(ascOrDesc);
                return ui.showDeadlinesList(sortedDeadlines, ascOrDesc);
            case ADDTIME:
                tasks.sortByAddTime(ascOrDesc);
                break;
            default:
                throw new ZackException("Unknown task type to sort by.");
            }
        } catch (IllegalArgumentException e) {
            throw new ZackException("Unknown task type to sort by: " + orderTaskType);
        }
        storage.save(tasks.getAllTasks());
        return ui.showTaskList(tasks.getAllTasks(), ascOrDesc, orderTaskType);
    }
}
