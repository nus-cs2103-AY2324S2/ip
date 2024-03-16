package ellie.command;

import ellie.TaskList;

/**
 * Represents a command to find tasks based on a keyword.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the FindCommand by searching for tasks containing the specified keyword
     * and displays the results.
     *
     * @param tasklist The TaskList to search for tasks.
     */
    public String runAndReturnResponse(TaskList tasklist) {
        String response = tasklist.searchTask(keyword);
        return response;
    };


}
