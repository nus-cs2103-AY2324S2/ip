package emisCommand;

import emis.Storage;
import emis.TaskList;

/**
 * Command class to find tasks containing a specific keyword in their descriptions.
 */
public class FindCommand extends Command {
    private String keywordToFind;

    /**
     * Constructs a FindCommand object with the specified keyword to find.
     * 
     * @param taskToFind The keyword to search for in task descriptions.
     */
    public FindCommand(String keywordToFind) {
        this.keywordToFind = keywordToFind;
    }

    /**
     * Executes the find command by searching for tasks containing the specified keyword.
     * @param tasklist The task list to search for matching tasks.
     * @param ui The user interface for displaying messages.
     * @param storage The storage for saving task data.
     */
    @Override
    public String execute(TaskList tasklist, Storage storage) {
        return tasklist.findTasks(this.keywordToFind);
    }
}