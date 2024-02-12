package haro.command;

import haro.Storage;
import haro.TaskList;
import haro.Ui;

/**
 * The FindCommand class represents a command to search for tasks containing a specific keyword in the application.
 * It extends the Command class and provides functionality to execute the search command.
 */
public class FindCommand extends Command {
    private String searchString;
    private TaskList taskList;

    /**
     * Constructs a FindCommand instance with the specified search string.
     *
     * @param searchString Keyword to search for in tasks
     */
    public FindCommand(String searchString) {
        super(false);
        this.searchString = searchString;
    }

    /**
     * Executes the find command, searching for tasks containing the specified keyword and prints the search results.
     *
     * @param taskList TaskList instance containing the list of tasks
     * @param ui       Ui instance for user interface interaction
     * @param storage  Storage instance for handling data storage
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String searchTaskString = taskList.findTask(this.searchString);
        return ui.printSearch(searchTaskString);
    }
}
