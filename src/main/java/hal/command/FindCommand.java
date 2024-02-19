package hal.command;

import hal.task.TaskList;

/**
 * The FindCommand class represents a command to find tasks containing a specific keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a new FindCommand object.
     *
     * @param keyword The keyword to search for in tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command.
     *
     * @param taskList The TaskList containing the tasks.
     * @return A message containing the list of tasks matching the keyword.
     */
    @Override
    public String execute(TaskList taskList) {
        return taskList.listMatchingTasks(keyword);
    }
}
