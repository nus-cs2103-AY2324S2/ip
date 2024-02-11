package duke;

/**
 * The FindCommand class represents a command to find tasks containing a specific keyword.
 * It implements the Command interface.
 */
public class FindCommand implements Command {
    private String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command, displaying tasks that contain the specified keyword.
     *
     * @param tasks   The TaskList containing all tasks.
     * @param ui      The Ui object for displaying user interface messages.
     * @param storage The Storage object (not used in find command).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ui.findTasksByKeyword(keyword, tasks.getTasks());
        return tasks.findTasksByKeyword(keyword);
    }
}

