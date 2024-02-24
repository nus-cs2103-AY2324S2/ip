package command;

import emis.Storage;
import emis.TaskList;

/**
 * The FindCommand class represents a command to find tasks containing a specific keyword in their descriptions in the EMIS application.
 * When executed, it searches for tasks containing the specified keyword and displays matching tasks to the user.
 */
public class FindCommand extends Command {
    /** The keyword to search for in task descriptions. */
    private String keywordToFind;

    /**
     * Constructs a new FindCommand object with the specified keyword to find.
     *
     * @param keywordToFind The keyword to search for in task descriptions.
     */
    public FindCommand(String keywordToFind) {
        this.keywordToFind = keywordToFind;
    }

    /**
     * Executes the find command by searching for tasks containing the specified keyword in their descriptions.
     *
     * @param tasklist The TaskList object representing the list of tasks.
     * @param storage The Storage object handling loading and saving of tasks.
     * @return A string representation of matching tasks found in the task list.
     */
    @Override
    public String execute(TaskList tasklist, Storage storage) {
        return tasklist.findTasks(this.keywordToFind);
    }
}
