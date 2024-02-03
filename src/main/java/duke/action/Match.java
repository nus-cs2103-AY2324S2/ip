package duke.action;

/**
 * Represents an action to find and display tasks matching a specific keyword.
 * Implements the Action interface.
 */
public class Match implements Action {
    private TaskList taskList;

    /**
     * Constructs a Match object with the specified TaskList.
     *
     * @param taskList The TaskList to search for matching tasks.
     */
    public Match(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Executes the action to find and display tasks matching a keyword.
     *
     * @return An empty string, as the actual matching logic is handled in the TaskList class.
     */
    @Override
    public String response() {
        return "";
    }
}

