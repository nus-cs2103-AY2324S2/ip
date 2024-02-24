package micromanager.commands;

import micromanager.exceptions.DukeException;
import micromanager.storage.Storage;
import micromanager.storage.TaskList;
import micromanager.tasks.Task;

/**
 * FindCommand class represents a command to search for tasks containing a specific keyword.
 * It extends the Command class and provides methods to execute the command.
 */
public class FindCommand extends Command {
    private String searchTerm;

    /**
     * Constructs a FindCommand object with the specified search term.
     *
     * @param searchTerm The search term to find in tasks.
     */
    public FindCommand(String searchTerm) {
        super();
        this.searchTerm = searchTerm.toLowerCase();
    }

    /**
     * Executes the find command by searching for tasks containing the search term.
     * Displays the matching tasks, if any, or a message if no matches are found.
     *
     * @param taskList The list of tasks to search.
     * @param storage  The storage handler (not used in this command).
     * @throws DukeException If an error occurs during command execution.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        TaskList searchResults = getSearchResults(taskList);

        if (searchResults.size() != 0) {
            return "Here are the matching tasks in your list:\n" + searchResults;
        } else {
            return "Sorry, there are no matching entries.";
        }
    }

    private TaskList getSearchResults(TaskList taskList) throws DukeException {
        TaskList searchResults = new TaskList();
        for (int i = 0; i < taskList.size(); i++) {
            Task currTask = taskList.get(i);
            if (currTask.match(searchTerm)) {
                searchResults.add(currTask);
            }
        }
        return searchResults;
    }
}
