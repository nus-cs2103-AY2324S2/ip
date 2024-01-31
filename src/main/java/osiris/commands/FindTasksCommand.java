package osiris.commands;

import java.util.ArrayList;

import osiris.task.TaskManager;
import osiris.ui.Ui;

/**
 * Represents a command to find tasks based on a search string.
 */
public class FindTasksCommand extends Command {

    public static final String COMMAND = "find";

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
     *
     * @param taskManager   The TaskManager instance managing the tasks.
     * @param userInterface The Ui instance for interacting with the user.
     * @return True if the command execution is successful, false otherwise.
     */
    @Override
    public boolean execute(TaskManager taskManager, Ui userInterface) {
        ArrayList<String> relevantTasks = taskManager.findTask(this.searchSting);
        userInterface.printFoundUserTasks(relevantTasks);
        return true;
    }
}
