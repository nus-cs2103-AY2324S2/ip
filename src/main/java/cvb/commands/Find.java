package cvb.commands;

import java.util.ArrayList;

import cvb.exceptions.ConvoBotException;
import cvb.utils.ResponseConstructor;
import cvb.utils.TaskList;
import me.xdrop.fuzzywuzzy.FuzzySearch;

/**
 * Represents the Find command, which searches for tasks in a TaskList based on a given query.
 */
public class Find implements Command {

    private static final int FUZZY_RATIO_CUTOFF = 50;

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
     * @param rc       The response constructor to construct the string for the matching tasks.
     * @throws ConvoBotException If there is an issue executing the command.
     */
    @Override
    public void execute(TaskList taskList, ResponseConstructor rc) throws ConvoBotException {
        ArrayList<String> matchingTaskStrings = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            String desc = taskList.getTaskDescription(i).toLowerCase(); // query is case-insensitive
            int fuzzyRatio = FuzzySearch.ratio(query, desc);
            if (fuzzyRatio >= FUZZY_RATIO_CUTOFF || desc.contains(query)) {
                matchingTaskStrings.add(taskList.getTaskString(i));
            }
        }
        rc.addMatchingTasks(matchingTaskStrings);
    }

    /**
     * Indicates whether the Find command is an exit command.
     *
     * @return Always false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
