package emisCommand;

import emis.Ui;
import emis.Storage;

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
    public void execute(emis.TaskList tasklist, Ui ui, Storage storage) {
        tasklist.findTasks(this.keywordToFind);
    }

    /**
     * Indicates whether the find command is an exit command.
     * 
     * @return Always returns false since find command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}