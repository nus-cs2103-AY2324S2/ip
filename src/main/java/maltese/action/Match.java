package maltese.action;

import java.util.ArrayList;

import maltese.exception.MalteseException;
import maltese.exception.NoWordException;
import maltese.task.Task;

/**
 * Represents an action to find and display tasks matching a specific keyword.
 * Implements the Action interface.
 */
public class Match implements Action {
    private static final int MATCH_START_INDEX = 5;
    private TaskList taskList;
    private String keyword;

    /**
     * Constructs a Match object with the specified TaskList.
     *
     * @param taskList The TaskList to search for matching tasks.
     */
    public Match(String keyword, TaskList taskList) {
        this.keyword = keyword.toLowerCase(); // Convert keyword to lowercase
        this.taskList = taskList;
    }

    /**
     * Parses the command to find tasks matching a keyword and creates a Match action.
     *
     * @param command  The command to be parsed, containing the keyword.
     * @param taskList The TaskList containing the tasks to search.
     * @return A Match action representing the tasks matching the keyword.
     * @throws MalteseException If there is an error parsing the command or searching for tasks.
     */

    public static Match parse(String command, TaskList taskList) throws MalteseException {
        String[] words = command.split(" ");
        if (words.length > 1) {
            String keyword = command.substring(MATCH_START_INDEX).trim();
            taskList.matches(keyword);
            return new Match(keyword, taskList);
        } else {
            throw new NoWordException();
        }
    }

    /**
     * Executes the action to find and display tasks matching a keyword.
     *
     * @return A string with all the tasks with that matches keyword
     */
    @Override
    public String getResponse() {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : taskList) {
            assert task != null : "Task in TaskList cannot be null";
            if (task.getDescription().toLowerCase().contains(keyword)) { // Convert description to lowercase
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            return "No matching tasks found.";
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                stringBuilder.append("  ").append(i + 1).append(". ").append(matchingTasks.get(i)).append("\n");
            }
            return stringBuilder.toString();
        }
    }
}

