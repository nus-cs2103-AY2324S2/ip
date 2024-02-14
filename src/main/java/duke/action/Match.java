package duke.action;

import duke.task.Task;

import java.util.ArrayList;
/**
 * Represents an action to find and display tasks matching a specific keyword.
 * Implements the Action interface.
 */
public class Match implements Action {
    private TaskList taskList;
    private String keyword;

    /**
     * Constructs a Match object with the specified TaskList.
     *
     * @param taskList The TaskList to search for matching tasks.
     */
    public Match(String keyword, TaskList taskList) {
        this.keyword = keyword;
        this.taskList = taskList;
    }

    /**
     * Executes the action to find and display tasks matching a keyword.
     *
     * @return An empty string, as the actual matching logic is handled in the TaskList class.
     */
    @Override
    public String response() {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getDescription().contains(keyword)) {
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

