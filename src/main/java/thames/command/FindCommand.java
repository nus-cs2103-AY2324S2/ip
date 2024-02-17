package thames.command;

import thames.Storage;
import thames.TaskList;
import thames.Ui;

/**
 * Subclass of Command that deals with finding tasks with given keyword.
 */
public class FindCommand extends Command {
    /** Keyword to search for in task list */
    String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds task in the given task list using keyword.
     *
     * @param tasks Task list to search keyword in.
     * @param ui User interface that notifies user of completion.
     * @param storage Not required.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList tasksFound = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            String taskName = tasks.get(i).getName().toLowerCase();
            if (taskName.contains(keyword.toLowerCase())) {
                tasksFound.add(tasks.get(i));
            }
        }
        return ui.showList(tasksFound);
    }
}
