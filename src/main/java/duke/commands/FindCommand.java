package duke.commands;

import duke.TaskList;

/**
 *  The Find class handles search requests.
 */
public class FindCommand extends Command {
    private TaskList userTasks;

    /**
     * Constructs a Find that handles search requests.
     * @param cmd The user input split by command and description.
     * @param userTasks The current task list of user tasks.
     */
    public FindCommand(String[] cmd, TaskList userTasks) {
        super(cmd);
        this.userTasks = userTasks;
    }

    @Override
    public String execute() {
        super.validateArgs();
        TaskList searchResults = userTasks.searchTasks(cmd[1], userTasks);
        return searchResults.stringifyTasks(true);
    }
}
