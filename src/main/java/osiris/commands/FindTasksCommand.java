package osiris.commands;

import java.util.ArrayList;

import osiris.task.TaskManager;
import osiris.ui.Ui;

/**
 * Represents a command to find tasks based on a search string.
 */
public class FindTasksCommand extends Command {

    /** Keyword used to identify the command. */
    public static final String COMMAND = "find";

    /** The String to be searched. */
    private final String searchSting;

    /**
     * Constructs a FindTasksCommand object with the specified search string.
     *
     * @param searchString The search string to find relevant tasks.
     */
    public FindTasksCommand(String searchString) {
        this.searchSting = searchString;
    }

    /**
     * Executes the find tasks command, searching for relevant tasks based on the search string.
     * Find items even if the keyword matches the item only partially.
     *
     * @param taskManager   The TaskManager instance managing the tasks.
     * @param userInterface The Ui instance for interacting with the user.
     * @return String of all the found tasks.
     */
    @Override
    public String execute(TaskManager taskManager, Ui userInterface) {
        ArrayList<String> relevantTasks = taskManager.findTask(searchSting);
        return userInterface.printFoundUserTasks(relevantTasks);
    }
}
