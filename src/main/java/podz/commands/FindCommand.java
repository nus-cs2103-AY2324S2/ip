package podz.commands;

/**
 * Represents a command to find tasks based on a keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand object with the specified keyword.
     *
     * @param keyword the keyword to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command to search for tasks with the keyword.
     */
    @Override
    public String execute() {
        super.response = super.taskList.findTasks(this.keyword);
        return super.response;
    }
}
