package commands;

import java.util.ArrayList;

import exceptions.ConvoBotException;
import utils.TaskList;
import utils.UI;

/**
 * Represents the Find command, which searches for tasks in a TaskList based on a given query.
 */
public class Find implements Command {

    /**
     * The query to be used for searching tasks. Case-insensitive.
     */
    private final String query;

    /**
     * Constructs a Find command with the specified query.
     *
     * @param query The query to be used for searching tasks. Case-insensitive.
     */
    public Find(String query) {
        this.query = query.toLowerCase(); // make query case-insensitive
    }

    /**
     * Executes the Find command by searching for tasks that match the specified query.
     *
     * @param taskList The TaskList to search for matching tasks.
     * @param ui       The UI to display the matching tasks.
     * @throws ConvoBotException If there is an issue executing the command.
     */
    public void execute(TaskList taskList, UI ui) throws ConvoBotException {
        ArrayList<String> matchingTaskStrings = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            String desc = taskList.getTaskDescription(i).toLowerCase(); // query is case-insensitive
            if (desc.contains(query)) {
                matchingTaskStrings.add(taskList.getTaskString(i));
            }
        }
        ui.showMatchingTasks(matchingTaskStrings);
    }

    /**
     * Indicates whether the Find command is an exit command.
     *
     * @return False, as Find is not an exit command.
     */
    public boolean isExit() {
        return false;
    }
}
